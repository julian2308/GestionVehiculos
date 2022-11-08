/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import gestores.GestorCarros;
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

/**
 * FXML Controller class
 *
 * @author julia_z20imv5
 */
public class TodosCarrosController implements Initializable {

    private GestorCarros gestor = new GestorCarros();
    
    @FXML
    private TableView<Carro> tablaCarros;
    @FXML
    private TableColumn<Carro, String> placa;
    @FXML
    private TableColumn<Carro, String> marca;
    @FXML
    private TableColumn<Carro, String> linea;
    @FXML
    private TableColumn<Carro, String> modelo;
    @FXML
    private TableColumn<Carro, String> color;
    @FXML
    private TableColumn<Carro, String> alquiler;
    @FXML
    private TableColumn<Carro, String> alquilado;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           placa.setCellValueFactory(new PropertyValueFactory<Carro, String>("placa"));
           marca.setCellValueFactory(new PropertyValueFactory<Carro, String>("marca"));
           linea.setCellValueFactory(new PropertyValueFactory<Carro, String>("linea"));
           modelo.setCellValueFactory(new PropertyValueFactory<Carro, String>("modelo"));
           color.setCellValueFactory(new PropertyValueFactory<Carro, String>("color"));
           alquiler.setCellValueFactory(new PropertyValueFactory<Carro, String>("valorAlquiler"));
           alquilado.setCellValueFactory(new PropertyValueFactory<Carro, String>("estaAlquilado"));
           
           ArrayList<Carro> misCarros = this.gestor.obtenerCarros();
           Carro miCarro = misCarros.get(0);
           System.out.println(misCarros);
           
           ObservableList<Carro> listaCarros = FXCollections.observableArrayList(misCarros);
           
           this.tablaCarros.setItems(listaCarros);
           
    }    
    
}
