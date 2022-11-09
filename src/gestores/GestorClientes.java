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
import negocio.entidades.Cliente;

/**
 *
 * @author julia_z20imv5
 */
public class GestorClientes {
    
    private final String ruta = "./src/basesDeDatos/dbClientes.txt";

    public void anadirCliente(String cedula, String nombre, String apellido, String telefono, String email, String foto) {
        Cliente clienteNuevo = new Cliente(cedula, nombre, apellido, telefono, email, foto);
        this.guardarCliente(clienteNuevo);

    }

    private void guardarCliente(Cliente clienteNuevo) {

        try {
            File archivo = new File(this.ruta);
            FileWriter wr = new FileWriter(archivo, true);
            PrintWriter pw = new PrintWriter(wr);
            System.out.println(clienteNuevo.toString());
            pw.println(clienteNuevo.toString());
            pw.close();
        } catch (IOException var5) {
            System.out.println("No se pudo cargar el cliente. Intente de nuevo");
        }

    }

    public ArrayList<Cliente> obtenerClientes() {

        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente miClientito;
        FileReader archivo;
        BufferedReader br;
        String lineas;

        try{
            archivo = new FileReader(this.ruta);
            br = new BufferedReader(archivo);
            while((lineas = br.readLine()) != null){
                String[] campos = lineas.split("/");
             
                miClientito = new Cliente(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]);
                
                clientes.add(miClientito);
            }
        }

        catch(IOException ex){
            System.out.println("Fallo busqueda cliente...");
        }
        
        return clientes;
    }

    public Cliente devolverCliente(String code) {
        Cliente clienteInstanciado = null;
        FileReader archivo;
        BufferedReader br;
        String registro;
        try {
            archivo = new FileReader(this.ruta);
            br = new BufferedReader(archivo);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split("/");
                if (campos[0].equals(code)) {
                    clienteInstanciado = new Cliente(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Fallo busqueda cliente...");
        }
        return clienteInstanciado;
    }

    public boolean existeCedula(String placa) {
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
            System.out.println("Fallo busqueda cliente...");
        }

        return existe;

    }

    private void reemplazarArchivo(ArrayList<Cliente> misClientes) {
        try {
            File archivo = new File(this.ruta);
            FileWriter writer = new FileWriter(archivo, false);
            PrintWriter pw = new PrintWriter(writer);
            for (Cliente clientito : misClientes) {
                pw.println(clientito.toString());
            }
            pw.close();
        } catch (IOException ex) {
            System.out.println("No se puede reemplazar el archivo...");
        }
    }

    public void eliminarCliente(String cedula) {

        Cliente miCliente = this.devolverCliente(cedula);
        ArrayList<Cliente> misClientes;
        misClientes = this.obtenerClientes();
        for (Cliente clientito : misClientes) {
            if (clientito.getCedula().equals(cedula)) {
                misClientes.remove(clientito);
                this.reemplazarArchivo(misClientes);
                Alert alertaExitosa = new Alert(Alert.AlertType.INFORMATION);
                alertaExitosa.setTitle("Registro Exitoso");
                alertaExitosa.setHeaderText(null);
                alertaExitosa.setContentText("El cliente fue ELIMINADO con ÉXITO");
                alertaExitosa.initStyle(StageStyle.UTILITY);
                alertaExitosa.showAndWait();
            }
        }
    }

    public void editarCliente(String cedula) {

        Cliente miCliente = this.devolverCliente(cedula);
        ArrayList<Cliente> clientes;
        clientes = this.obtenerClientes();
        for (Cliente clientito : clientes) {
            if (clientito.getCedula().equals(cedula)) {
                clientes.remove(clientito);
                this.reemplazarArchivo(clientes);
            }
        }

    }

    
}
