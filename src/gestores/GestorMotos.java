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
import negocio.entidades.Moto;

/**
 *
 * @author julia_z20imv5
 */
public class GestorMotos {

    private final String ruta = "./src/basesDeDatos/dbMotos.txt";

    public void anadirMoto(String placa, String marca, String modelo, String linea, String color, String valorAlquiler, String estaAlquilado, String imagenPath) {
        Moto motoNuevo = new Moto(placa, marca, modelo, linea, color, valorAlquiler, estaAlquilado, imagenPath);
        this.guardarMoto(motoNuevo);

    }

    private void guardarMoto(Moto motoNuevo) {

        try {
            File archivo = new File(this.ruta);
            FileWriter wr = new FileWriter(archivo, true);
            PrintWriter pw = new PrintWriter(wr);
            System.out.println(motoNuevo.toString());
            pw.println(motoNuevo.toString());
            pw.close();
        } catch (IOException var5) {
            System.out.println("No se pudo cargar la moto. Intente de nuevo");
        }

    }

    public ArrayList<Moto> obtenerMotos() {

        ArrayList<Moto> motitos = new ArrayList<>();
        Moto miMotito;
        FileReader archivo;
        BufferedReader br;
        String lineas;

        try {
            archivo = new FileReader(this.ruta);
            br = new BufferedReader(archivo);
            while ((lineas = br.readLine()) != null) {
                String[] campos = lineas.split("/");

                for (int i = 0; i < campos.length; i++) {
                    System.out.println(campos[i] + " " + i);
                }
                miMotito = new Moto(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5], campos[6], campos[7]);

                motitos.add(miMotito);
            }
        } catch (IOException ex) {
            System.out.println("Fallo busqueda moto...");
        }

        return motitos;
    }

    public Moto devolverMoto(String code) {
        Moto motoInstanciado = null;
        FileReader archivo;
        BufferedReader br;
        String registro;
        try {
            archivo = new FileReader(this.ruta);
            br = new BufferedReader(archivo);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split("/");
                if (campos[0].equals(code)) {
                    motoInstanciado = new Moto(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5], campos[6], campos[7]);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Fallo busqueda moto...");
        }
        return motoInstanciado;
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
            System.out.println("Fallo busqueda moto...");
        }

        return existe;

    }

    private void reemplazarArchivo(ArrayList<Moto> miMoto) {
        try {
            File archivo = new File(this.ruta);
            FileWriter writer = new FileWriter(archivo, false);
            PrintWriter pw = new PrintWriter(writer);
            for (Moto motito : miMoto) {
                pw.println(motito.toString());
            }
            pw.close();
        } catch (IOException ex) {
            System.out.println("No se puede reemplazar el archivo...");
        }
    }

    public void eliminarMoto(String placa) {

        Moto motoDevuelta = this.devolverMoto(placa);
        ArrayList<Moto> misMotos;
        misMotos = this.obtenerMotos();
        for (Moto motito : misMotos) {
            if (motito.getPlaca().equals(placa)) {
                misMotos.remove(motito);
                this.reemplazarArchivo(misMotos);
                Alert alertaExitosa = new Alert(Alert.AlertType.INFORMATION);
                alertaExitosa.setTitle("Registro Exitoso");
                alertaExitosa.setHeaderText(null);
                alertaExitosa.setContentText("El carro fue ELIMINADO con ÉXITO");
                alertaExitosa.initStyle(StageStyle.UTILITY);
                alertaExitosa.showAndWait();
            }
        }
    }

    public void editarMoto(String placa) {

        Moto moto = this.devolverMoto(placa);
        ArrayList<Moto> motos;
        motos = this.obtenerMotos();
        for (Moto motito : motos) {
            if (motito.getPlaca().equals(placa)) {
                motos.remove(motito);
                this.reemplazarArchivo(motos);
            }
        }

    }

    public void alquilarMoto(String placa) {
        ArrayList<Moto> motos;
        Moto motito = this.devolverMoto(placa);

        if (motito != null) {
            motos = this.obtenerMotos();
            for (Moto miMoto : motos) {
                if (miMoto.getPlaca().equals(motito.getPlaca())) {
                    miMoto.setEstaAlquilado("Si");
                    this.reemplazarArchivo(motos);

                }
            }
        }

    }

}
