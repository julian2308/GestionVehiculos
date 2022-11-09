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
import javax.swing.JOptionPane;
import negocio.entidades.Carro;
import negocio.entidades.Marca;

/**
 *
 * @author julia_z20imv5
 */
public class GestorCarros {

    private final String ruta = "./src/basesDeDatos/dbCarros.txt";

    public void anadirCarro(String placa, String marca, String modelo, String linea, String color, String valorAlquiler, String estaAlquilado, String imagenPath) {
        Carro carroNuevo = new Carro(placa, marca, modelo, linea, color, valorAlquiler, estaAlquilado, imagenPath);
        this.guardarCarro(carroNuevo);

    }

    private void guardarCarro(Carro carroNuevo) {

        try {
            File archivo = new File("./src/basesDeDatos/dbCarros.txt");
            FileWriter wr = new FileWriter(archivo, true);
            PrintWriter pw = new PrintWriter(wr);
            System.out.println(carroNuevo.toString());
            pw.println(carroNuevo.toString());
            pw.close();
        } catch (IOException var5) {
            System.out.println("No se pudo cargar el carro. Intente de nuevo");
        }

    }

    public ArrayList<Carro> obtenerCarros() {

        ArrayList<Carro> carritos = new ArrayList<>();
        Carro miCarrito;
        Carro miCarrito3;
        FileReader archivo;
        BufferedReader br;
        String lineas;

        /*miCarrito = new Carro("campos[0]", "campos[1]", "campos[2]", "ampos[3]", "campos[4]", "campos[5]", "campos[6]", "campos[7]");
        Carro miCarrito1 = new Carro("campos[0]", "campos[1]", "campos[2]", "ampos[3]", "campos[4]", "campos[5]", "campos[6]", "campos[7]");
        carritos.add(miCarrito);
        carritos.add(miCarrito1);*/
        try {
            archivo = new FileReader(this.ruta);
            br = new BufferedReader(archivo);
            while ((lineas = br.readLine()) != null) {
                String[] campos = lineas.split("/");

                for (int i = 0; i < campos.length; i++) {
                    System.out.println(campos[i] + " " + i);
                }
                System.out.println("aca muere");
                miCarrito3 = new Carro(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5], campos[6], campos[7]);
                System.out.println(miCarrito3.toString() + " MI CARRITO");
                carritos.add(miCarrito3);
            }
        } catch (IOException ex) {
            System.out.println("Fallo busqueda carro...");
        }

        return carritos;
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
                System.out.println(campos[0] + "aaaaaaaaa");
                //System.out.println(campos[7] + "bbbbbbb");
                if (campos[0].equals(code)) {
                    carroInstanciado = new Carro(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5], campos[6], campos[7]);
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

    public void alquilarCarro(String placa) {
        ArrayList<Carro> carros;
        Carro carrito = this.devolverCarro(placa);

        if (carrito != null) {
            carros = this.obtenerCarros();
            for (Carro miCarro : carros) {
                if (miCarro.getPlaca().equals(carrito.getPlaca())) {
                    miCarro.setEstaAlquilado("Si");
                    this.reemplazarArchivo(carros);
                    
                }
            }
        }

    }

}
