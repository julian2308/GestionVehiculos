/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import gestores.GestorCarros;
import gestores.GestorClientes;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.entidades.Carro;
import negocio.entidades.Cliente;

/**
 * FXML Controller class
 *
 * @author julia_z20imv5
 */
public class TodosClientesController implements Initializable {

    
    private GestorClientes gestor = new GestorClientes();
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> cedula;
    @FXML
    private TableColumn<Cliente, String> nombre;
    @FXML
    private TableColumn<Cliente, String> apellido;
    @FXML
    private TableColumn<Cliente, String> telefono;
    @FXML
    private TableColumn<Cliente, String> email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           cedula.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cedula"));
           nombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
           apellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
           telefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
           email.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
           
           ArrayList<Cliente> misClientes = this.gestor.obtenerClientes();
           ArrayList<Cliente> prueba = new ArrayList();
           ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(misClientes);

           this.tablaClientes.setItems(listaClientes);
    }    
    
}
