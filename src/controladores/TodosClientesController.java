/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import gestores.GestorCarros;
import gestores.GestorClientes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    @FXML
    private Button btnCancelar;

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

    @FXML
    private void cancelar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/clientes.fxml"));
            Parent root = loader.load();
            ClientesController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnCancelar.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(ClienteAlquilaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
