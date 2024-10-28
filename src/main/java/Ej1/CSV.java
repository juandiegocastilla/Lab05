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
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        comboLocalidad = new JComboBox<>();
        comboDesplazados = new JComboBox<>(new String[]{"Todos", "Si", "No"});
        comboMayorDeEdad = new JComboBox<>(new String[]{"Todos", "Si", "No"});
        jButtonFiltrar = new JButton("Filtrar");
        jButtonLimpiar = new JButton("Limpiar Selección");
        jButtonImprimir = new JButton("Imprimir CSV");

        
        labelLocalidad = new JLabel("Localidad: ");
        labelDesplazado = new JLabel("Desplazado: ");
        labelMayorDeEdad = new JLabel("Mayor de Edad: ");
        labelResultado = new JLabel("");

        
        jButtonFiltrar.addActionListener(evt -> filtrarDatos());
        jButtonLimpiar.addActionListener(evt -> limpiarSeleccion());
        jButtonImprimir.addActionListener(evt -> imprimirCSV());

       
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

        add(panel);

        cargarDatosDesdeCSV();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CSV().setVisible(true));
    }
}
