/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Ej1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class CSV extends JFrame {

    String Localidad;
    String Patencion; 
    float nhombres;
    float nmujeres;
    boolean ndesplazados;
    boolean mayorDeEdad;

   
    private JComboBox<String> comboLocalidad;
    private JComboBox<String> comboDesplazados;
    private JComboBox<String> comboMayorDeEdad;
    private JButton jButtonFiltrar;
    private JButton jButtonLimpiar;
    private JButton jButtonImprimir;
   private JButton GraficaBarras;
    private  JButton GraficaPastel;;
    private JLabel labelLocalidad;
    private JLabel labelDesplazado;
    private JLabel labelMayorDeEdad;
    private JLabel labelResultado;
 
    public CSV(String Localidad, String Patencion, float nhombres, float nmujeres, boolean ndesplazados, boolean mayorDeEdad) {
        this.Localidad = Localidad;
        this.Patencion = Patencion;
        this.nhombres = nhombres;
        this.nmujeres = nmujeres;
        this.ndesplazados = ndesplazados;
        this.mayorDeEdad = mayorDeEdad;
    }

    public String getLocalidad() { return Localidad; }
    public String getPuntoAtencion() { return Patencion; }
    public float getHombres() { return nhombres; }
    public float getMujeres() { return nmujeres; }
    public boolean isDesplazado() { return ndesplazados; }
    public boolean isMayorDeEdad() { return mayorDeEdad; }

  public CSV() {
        
        setTitle("CSV Datos Cargados");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        comboLocalidad = new JComboBox<>();
        comboDesplazados = new JComboBox<>(new String[]{"Todos", "Si", "No"});
        comboMayorDeEdad = new JComboBox<>(new String[]{"Todos", "Si", "No"});
        jButtonFiltrar = new JButton("Filtrar");
        jButtonLimpiar = new JButton("Limpiar Selección");
        jButtonImprimir = new JButton("Imprimir CSV");
          GraficaBarras = new JButton("Gráfica de Barras");
       
       GraficaPastel= new JButton("Gráfica de Pastel");


        GraficaBarras.addActionListener(evt -> mostrarGraficaBarras());


   
    
        labelLocalidad = new JLabel("Localidad: ");
        labelDesplazado = new JLabel("Desplazado: ");
        labelMayorDeEdad = new JLabel("Mayor de Edad: ");
        labelResultado = new JLabel("");

        
        jButtonFiltrar.addActionListener(evt -> filtrarDatos());
        jButtonLimpiar.addActionListener(evt -> limpiarSeleccion());
        jButtonImprimir.addActionListener(evt -> imprimirCSV());

       GraficaPastel.addActionListener(evt -> mostrarGraficaPastel());
        JPanel panel = new JPanel();
        panel.add(comboLocalidad);
        panel.add(comboDesplazados);
        panel.add(comboMayorDeEdad);
        panel.add(jButtonFiltrar);
        panel.add(jButtonLimpiar);
        panel.add(jButtonImprimir);
        panel.add(labelLocalidad);
        panel.add(labelDesplazado);
        panel.add(labelMayorDeEdad);
        panel.add(labelResultado);
       panel.add(GraficaBarras);
      panel.add(GraficaPastel);
        add(panel);

        cargarDatosDesdeCSV();
    }
  public List<CSV> leerEstudiantesDesdeCSV(String archivoCSV) {
        List<CSV> estudiantes = new ArrayList<>();
        String linea = "";
        String divisor = ";"; 

        try (BufferedReader Leer = new BufferedReader(new FileReader(archivoCSV))) {
            Leer.readLine(); 

            while ((linea = Leer.readLine()) != null) {
                String[] datos = linea.split(divisor);

                if (datos.length != 6) {
                    System.out.println("Línea inválida: " + linea);
                    continue;
                }

                String localidad = datos[0];
                String puntoAtencion = datos[1];
                float hombres = Float.parseFloat(datos[2]);
                float mujeres = Float.parseFloat(datos[3]);

                
                boolean desplazado = datos[4].trim().equalsIgnoreCase("Si");
                boolean mayorDeEdad = datos[5].trim().equalsIgnoreCase("Si");

                CSV estudiante = new CSV(localidad, puntoAtencion, hombres, mujeres, desplazado, mayorDeEdad);
                estudiantes.add(estudiante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir datos: " + e.getMessage());
        }

        return estudiantes;
    }
  List<CSV> estudiantes = new ArrayList<>();

    private void cargarDatosDesdeCSV() {
        try {
            estudiantes = leerEstudiantesDesdeCSV("src/distribucion.csv");
            ComboLocalidades();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }

    public void ComboLocalidades() {
        Set<String> localidades = estudiantes.stream()
                .map(CSV::getLocalidad)
                .collect(Collectors.toSet());

        comboLocalidad.addItem("Todos");
        localidades.forEach(localidad -> comboLocalidad.addItem(localidad));
    }
List<CSV> filtrados = new ArrayList<>();
    private void filtrarDatos() {
        String localidadSeleccionada = (String) comboLocalidad.getSelectedItem();
        String desplazadoSeleccionado = (String) comboDesplazados.getSelectedItem();
        String mayorDeEdadSeleccionado = (String) comboMayorDeEdad.getSelectedItem();

        
        List<CSV> filtrados = estudiantes.stream()
                .filter(estudiante -> (localidadSeleccionada.equals("Todos") || estudiante.getLocalidad().equals(localidadSeleccionada)) &&
                        (desplazadoSeleccionado.equals("Todos") || 
                         (desplazadoSeleccionado.equals("Si") && estudiante.isDesplazado()) ||
                         (desplazadoSeleccionado.equals("No") && !estudiante.isDesplazado())) &&
                        (mayorDeEdadSeleccionado.equals("Todos") || 
                         (mayorDeEdadSeleccionado.equals("Si") && estudiante.isMayorDeEdad()) ||
                         (mayorDeEdadSeleccionado.equals("No") && !estudiante.isMayorDeEdad())))
                .collect(Collectors.toList());

       
        if (!filtrados.isEmpty()) {
            CSV estudianteEjemplo = filtrados.get(0);
            labelLocalidad.setText("Localidad: " + estudianteEjemplo.getLocalidad());
            labelDesplazado.setText("Desplazado: " + (estudianteEjemplo.isDesplazado() ? "Sí" : "No"));
            labelMayorDeEdad.setText("Mayor de Edad: " + (estudianteEjemplo.isMayorDeEdad() ? "Sí" : "No"));
            labelResultado.setText("Total filtrados: " + filtrados.size());
        } else {
            labelLocalidad.setText("Localidad: ");
            labelDesplazado.setText("Desplazado: ");
            labelMayorDeEdad.setText("Mayor de Edad: ");
            labelResultado.setText("No se encontraron resultados.");
        }
    }
 private void limpiarSeleccion() {
        comboLocalidad.setSelectedIndex(0); 
        comboDesplazados.setSelectedIndex(0); 
        comboMayorDeEdad.setSelectedIndex(0); 
        labelLocalidad.setText("Localidad: ");
        labelDesplazado.setText("Desplazado: ");
        labelMayorDeEdad.setText("Mayor de Edad: ");
        labelResultado.setText("");
    }

    private void imprimirCSV() {
        String archivoSalida = "src/datos_filtrados.csv"; 
        try (FileWriter escri = new FileWriter(archivoSalida)) {
            escri.append("Localidad;PuntoAtencion;Hombres;Mujeres;Desplazado;MayorDeEdad\n");
            for (CSV estudiante : filtrados) {
                escri.append(estudiante.getLocalidad()).append(";")
                      .append(estudiante.getPuntoAtencion()).append(";")
                      .append(String.valueOf(estudiante.getHombres())).append(";")
                      .append(String.valueOf(estudiante.getMujeres())).append(";")
                      .append(estudiante.isDesplazado() ? "Sí" : "No").append(";")
                      .append(estudiante.isMayorDeEdad() ? "Sí" : "No").append("\n");
            }
            JOptionPane.showMessageDialog(this, "Datos impresos en " + archivoSalida, "Imprimir CSV", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al imprimir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private List<CSV> obtenerDatosFiltrados() {
    String localidadSeleccionada = (String) comboLocalidad.getSelectedItem();
    String desplazadoSeleccionado = (String) comboDesplazados.getSelectedItem();
    String mayorDeEdadSeleccionado = (String) comboMayorDeEdad.getSelectedItem();

    return estudiantes.stream()
            .filter(estudiante -> (localidadSeleccionada.equals("Todos") || estudiante.getLocalidad().equals(localidadSeleccionada)) &&
                    (desplazadoSeleccionado.equals("Todos") || 
                     (desplazadoSeleccionado.equals("Si") && estudiante.isDesplazado()) ||
                     (desplazadoSeleccionado.equals("No") && !estudiante.isDesplazado())) &&
                    (mayorDeEdadSeleccionado.equals("Todos") || 
                     (mayorDeEdadSeleccionado.equals("Si") && estudiante.isMayorDeEdad()) ||
                     (mayorDeEdadSeleccionado.equals("No") && !estudiante.isMayorDeEdad())))
            .collect(Collectors.toList());
}
    private void mostrarGraficaBarras() {
    List<CSV> filtrados = obtenerDatosFiltrados();
    
    filtrados.forEach(estudiante -> {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(estudiante.getHombres(), "Hombres", estudiante.getPuntoAtencion());
        dataset.addValue(estudiante.getMujeres(), "Mujeres", estudiante.getPuntoAtencion());

        JFreeChart chart = ChartFactory.createBarChart(
                "Distribución de Hombres y Mujeres en " + estudiante.getLocalidad(),
                "Punto de Atención",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Gráfica de Barras - " + estudiante.getLocalidad());
        frame.setContentPane(chartPanel);
        frame.setSize(600, 400);
        frame.setVisible(true);
    });
    
    
}
    private void mostrarGraficaPastel() {
    List<CSV> filtrados = obtenerDatosFiltrados();

    filtrados.forEach(estudiante -> {
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Hombres", estudiante.getHombres());
        datos.setValue("Mujeres", estudiante.getMujeres());

        JFreeChart grafcir = ChartFactory.createPieChart(
                "Distribución de Hombres y Mujeres en " + estudiante.getLocalidad(),
                datos,
                true, true, false);

        ChartPanel Panel = new ChartPanel(grafcir);
        JFrame frame = new JFrame("Gráfica de Pastel - " + estudiante.getLocalidad());
        frame.setContentPane(Panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    });
}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CSV().setVisible(true));
    }
}
