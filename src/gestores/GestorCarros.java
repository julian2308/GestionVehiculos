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
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import negocio.entidades.Carro;
import negocio.entidades.Marca;

/**
 *
 * @author julia_z20imv5
 */
public class GestorCarros {

    private final String ruta = "./src/basesDeDatos/dbCarros.txt";

    public void anadirCarro(String placa, String marca, String modelo, String linea, String color, String valorAlquiler, String estaAlquilado) {

        Carro carroNuevo = new Carro(placa, marca, modelo, linea, color, valorAlquiler, estaAlquilado);
        this.guardarCarro(carroNuevo);

    }

    private void guardarCarro(Carro carroNuevo) {

        try {
            File archivo = new File("./src/basesDeDatos/dbCarros.txt");
            FileWriter wr = new FileWriter(archivo, true);
            PrintWriter pw = new PrintWriter(wr);
            pw.println(carroNuevo.toString());
            pw.close();
        } catch (IOException var5) {
            System.out.println("No se pudo cargar el carro. Intente de nuevo");
        }

    }

    public ArrayList<Carro> obtenerCarros() {

        ArrayList<Carro> carrosExistentes = new ArrayList<>();
        Carro carroInstanciado;
        FileReader archivo;
        BufferedReader br;
        String lineas;

        try {
            archivo = new FileReader(this.ruta);
            br = new BufferedReader(archivo);
            while ((lineas = br.readLine()) != null) {
                String[] campos = lineas.split("/");
                /*for(String lineaActual : campos){
                    System.out.println(lineaActual);
                }*/

                //System.out.println(lineasMarca + " estas son mis lineas");
                carroInstanciado = new Carro(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);

                carrosExistentes.add(carroInstanciado);
            }

        } catch (IOException ex) {
            System.out.println("Fallo busqueda estudiante...");
        }

        return carrosExistentes;

    }

    public Carro devolverCarro(String code) {
        Carro carroInstanciado = null;
        FileReader archivo;
        BufferedReader br;
        String registro;
        try {
            archivo = new FileReader(this.ruta);
            br = new BufferedReader(archivo);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split("/");
                if (campos[0].equals(code)) {
                    carroInstanciado = new Carro(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Fallo busqueda estudiante...");
        }
        return carroInstanciado;
    }

    public boolean existePlaca(String placa) {
        boolean existe = false;
        FileReader archivo;
        BufferedReader buffer;
        String registro;
        try {
            archivo = new FileReader(this.ruta);
            buffer = new BufferedReader(archivo);
            while ((registro = buffer.readLine()) != null) {
                String[] campos = registro.split("/");
                if (campos[0].equals(placa)) {
                    existe = true;
                }
            }
        } catch (IOException ex) {
            System.out.println("Fallo busqueda estudiante...");
        }

        return existe;

    }

    private void reemplazarArchivo(ArrayList<Carro> miCarro) {
        try {
            File archivo = new File(this.ruta);
            FileWriter writer = new FileWriter(archivo, false);
            PrintWriter pw = new PrintWriter(writer);
            for (Carro carrito : miCarro) {
                pw.println(carrito.toString());
            }
            pw.close();
        } catch (IOException ex) {
            System.out.println("No se puede reemplazar el archivo...");
        }
    }

    public void eliminarCarro(String placa) {

        Carro carro = this.devolverCarro(placa);
        ArrayList<Carro> carros;
        carros = this.obtenerCarros();
        for (Carro carrito : carros) {
            if (carrito.getPlaca().equals(placa)) {
                carros.remove(carrito);
                this.reemplazarArchivo(carros);
                Alert alertaExitosa = new Alert(Alert.AlertType.INFORMATION);
                alertaExitosa.setTitle("Registro Exitoso");
                alertaExitosa.setHeaderText(null);
                alertaExitosa.setContentText("El carro fue ELIMINADO con ÉXITO");
                alertaExitosa.initStyle(StageStyle.UTILITY);
                alertaExitosa.showAndWait();
            }
        }
    }

    public void editarCarro(String placa) {

        Carro carro = this.devolverCarro(placa);
        ArrayList<Carro> carros;
        carros = this.obtenerCarros();
        for (Carro carrito : carros) {
            if (carrito.getPlaca().equals(placa)) {
                carros.remove(carrito);
                this.reemplazarArchivo(carros);
            }
        }

    }

}
