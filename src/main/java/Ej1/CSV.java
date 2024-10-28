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

  

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CSV().setVisible(true));
    }
}
