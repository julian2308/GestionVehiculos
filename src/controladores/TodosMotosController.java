/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import gestores.GestorCarros;
import gestores.GestorMotos;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.entidades.Carro;

import negocio.entidades.Moto;

/**
 * FXML Controller class
 *
 * @author julia_z20imv5
 */
public class TodosMotosController implements Initializable {

    private GestorMotos gestor = new GestorMotos();

    @FXML
    private TableView<Moto> tablaCarros;
    @FXML
    private TableColumn<Moto, String> placa;
    @FXML
    private TableColumn<Moto, String> marca;
    @FXML
    private TableColumn<Moto, String> linea;
    @FXML
    private TableColumn<Moto, String> modelo;
    @FXML
    private TableColumn<Moto, String> color;
    @FXML
    private TableColumn<Moto, String> alquiler;
    @FXML
    private TableColumn<Moto, String> alquilado;
    @FXML
    private Button btnContinuar;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        placa.setCellValueFactory(new PropertyValueFactory<Moto, String>("placa"));
        marca.setCellValueFactory(new PropertyValueFactory<Moto, String>("marca"));
        linea.setCellValueFactory(new PropertyValueFactory<Moto, String>("linea"));
        modelo.setCellValueFactory(new PropertyValueFactory<Moto, String>("modelo"));
        color.setCellValueFactory(new PropertyValueFactory<Moto, String>("color"));
        alquiler.setCellValueFactory(new PropertyValueFactory<Moto, String>("valorAlquiler"));
        alquilado.setCellValueFactory(new PropertyValueFactory<Moto, String>("estaAlquilado"));

        ArrayList<Moto> misMotos = this.gestor.obtenerMotos();
        Moto miCarro = misMotos.get(0);
        System.out.println(miCarro);

        ObservableList<Moto> listaMotos = FXCollections.observableArrayList(misMotos);

        this.tablaCarros.setItems(listaMotos);

    }
    
    @FXML
    private void continuar(ActionEvent event) {
        /*Carro carrito = new Carro();
        
        List<List<String>> arrList = new ArrayList();
        
        for(int i =0; i<tablaCarros.getItems().size(); i++){
            carrito = tablaCarros.getItems().get(i);
            arrList.add(new ArrayList<>());
            arrList.get(i).add(carrito.getPlaca());
            arrList.get(i).add(carrito.isEstaAlquilado());
        }
         */

        ObservableList<Moto> motitos;
        motitos = tablaCarros.getSelectionModel().getSelectedItems();

        System.out.println(motitos.get(0).isEstaAlquilado());
        if (motitos.get(0).isEstaAlquilado().equals("No")) {
            System.out.println("alquilable");
            motitos.get(0).setEstaAlquilado("Si");
            gestor.alquilarMoto(motitos.get(0).getPlaca());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/clienteAlquila.fxml"));
                Parent root = loader.load();
                ClienteAlquilaController controller = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                //stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) this.btnContinuar.getScene().getWindow();
                stage1.close();

            } catch (IOException ex) {
                Logger.getLogger(ClienteAlquilaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alertaFallida = new Alert(Alert.AlertType.ERROR);
            alertaFallida.setTitle("Busqueda fallida");
            alertaFallida.setHeaderText(null);
            alertaFallida.setContentText("Ese carro se encuentra alquilado ahora mismo");
            alertaFallida.initStyle(StageStyle.UTILITY);
            alertaFallida.showAndWait();
        }
    }
}
