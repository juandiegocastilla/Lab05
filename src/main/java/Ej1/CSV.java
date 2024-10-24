/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ej1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author juand
 */
public class CSV extends javax.swing.JFrame {

    



/**
 *
 * @author juand
 */


    String Localidad;
    String Patencion;
    float nhombres;
    float nmujeres;
    boolean ndesplazados;
    
    public CSV (String Localidad, String Patencion,  float nhombres, float nmujeres,  boolean ndesplazados) {
        this.Localidad = Localidad;
        this.Patencion = Patencion;
        this.nhombres = nhombres;
        this.nmujeres = nmujeres;
        this.ndesplazados = ndesplazados;
        
        
    }
      public String getLocalidad() { return Localidad; }
    public String getPuntoAtencion() { return Patencion; }
    public float getHombres() { return nhombres; }
    public float getMujeres() { return nmujeres; }
    public boolean isDesplazado() { return ndesplazados; }

    
    public CSV() {
        initComponents();
        cargarDatosDesdeCSV();
    }

   public List<CSV> leerEstudiantesDesdeCSV(String archivoCSV) {
        List<CSV> estudiantes = new ArrayList<>();
        String linea = "";
        String divisor = ";"; 
        
        try(BufferedReader Leer = new BufferedReader(new FileReader(archivoCSV))){

          Leer.readLine();

            
            while ((linea = Leer.readLine()) != null) {
                String[] datos = linea.split(divisor);

              
                String localidad = datos[0];
                String puntoAtencion = datos[1];
                float hombres = Integer.parseInt(datos[2]);
                float mujeres = Integer.parseInt(datos[3]);
               boolean desplazado = datos[4].equalsIgnoreCase("Si");
              
               
                CSV estudiante = new CSV(localidad, puntoAtencion, hombres, mujeres, desplazado);

                
                estudiantes.add(estudiante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }
   
   List<CSV> estudiantes = new ArrayList<>();

private void cargarDatosDesdeCSV() {
    CSV lectorCSV = new CSV();
    estudiantes = lectorCSV.leerEstudiantesDesdeCSV("src/main/resources/distribucion.csv");

    
    ComboLocalidades();
    ComboPuntosAtencion();
}
   public void ComboLocalidades(){
        Set<String> localidades = estudiantes.stream()
        .map(CSV::getLocalidad)
        .collect(Collectors.toSet());

    comboLocalidad.addItem("Todos"); 
    localidades.forEach(localidad -> comboLocalidad.addItem(localidad));
   }
   
   public void ComboPuntosAtencion(){
        Set<String> puntos = estudiantes.stream()
        .map(CSV::getPuntoAtencion)
        .collect(Collectors.toSet());

    comboPunto.addItem("Todos");
    puntos.forEach(punto -> comboPunto.addItem(punto)); 
   } 
   /**
     * Creates new form CSV
     */
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboLocalidad = new javax.swing.JComboBox<>();
        comboPunto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboLocalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboPunto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboPunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(comboPunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboLocalidad;
    private javax.swing.JComboBox<String> comboPunto;
    // End of variables declaration//GEN-END:variables
}
