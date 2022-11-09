/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import negocio.entidades.Carro;
import negocio.entidades.Marca;

/**
 *
 * @author julia_z20imv5
 */
public class GestorMarcas {
    
    private final String rutaCarros = "./src/basesDeDatos/dbMarcasCarros.txt";
    private final String rutaMotos = "./src/basesDeDatos/dbMarcasMotos.txt";
    
    public void anadirMarca(String marca){
        
        ArrayList<String> lineas = new ArrayList();
        lineas.add("Chivor");
        lineas.add("Pepe");
        lineas.add("Andres");
    
       Marca marcaNueva = new Marca(marca, lineas);
       this.guardarMarcaCarro(marcaNueva);
       
    
    }
    
    private void guardarMarcaCarro(Marca marcaNueva){
    
        try {
            File archivo = new File(this.rutaCarros);
            FileWriter wr = new FileWriter(archivo, true);
            PrintWriter pw = new PrintWriter(wr);
            pw.println(marcaNueva.toString());
            pw.close();
        } catch (IOException var5) {
            System.out.println("No se pudo cargar el carro. Intente de nuevo");
        }
        
    }
    
    public ArrayList<Marca> obtenerMarcasCarros(){
    
        ArrayList<Marca> marcasExistentes = new ArrayList<>();
        Marca marcaInstanciada;
        FileReader archivo;
        BufferedReader br;
        String lineas;

        try{
            archivo = new FileReader(this.rutaCarros);
            br = new BufferedReader(archivo);
            while((lineas = br.readLine()) != null){
                ArrayList<String> lineasMarca = new ArrayList<>();
                String[] campos = lineas.split("/");
                for(int i=1; i<campos.length; i++){
                    lineasMarca.add(campos[i]);
                    //System.out.println(i);
                }
                /*for(String lineaActual : campos){
                    System.out.println(lineaActual);
                }*/

                //System.out.println(lineasMarca + " estas son mis lineas");
                marcaInstanciada = new Marca(campos[0], lineasMarca);

         
                marcasExistentes.add(marcaInstanciada);
            }
            
        }

        catch(IOException ex){
            System.out.println("Fallo busqueda estudiante...");
        }
    
   
        return marcasExistentes;
    
    
    
    }
    
    //Motos
    
    private void guardarMarcaMoto(Marca marcaNueva){
    
        try {
            File archivo = new File(this.rutaMotos);
            FileWriter wr = new FileWriter(archivo, true);
            PrintWriter pw = new PrintWriter(wr);
            pw.println(marcaNueva.toString());
            pw.close();
        } catch (IOException var5) {
            System.out.println("No se pudo cargar el carro. Intente de nuevo");
        }
        
    }
    
    public ArrayList<Marca> obtenerMarcasMotos(){
    
        ArrayList<Marca> marcasExistentes = new ArrayList<>();
        Marca marcaInstanciada;
        FileReader archivo;
        BufferedReader br;
        String lineas;

        try{
            archivo = new FileReader(this.rutaMotos);
            br = new BufferedReader(archivo);
            while((lineas = br.readLine()) != null){
                ArrayList<String> lineasMarca = new ArrayList<>();
                String[] campos = lineas.split("/");
                for(int i=1; i<campos.length; i++){
                    lineasMarca.add(campos[i]);
                    //System.out.println(i);
                }
                /*for(String lineaActual : campos){
                    System.out.println(lineaActual);
                }*/

                //System.out.println(lineasMarca + " estas son mis lineas");
                marcaInstanciada = new Marca(campos[0], lineasMarca);

         
                marcasExistentes.add(marcaInstanciada);
            }
            
        }

        catch(IOException ex){
            System.out.println("Fallo busqueda estudiante...");
        }
    
   
        return marcasExistentes;
    
    
    
    }
}