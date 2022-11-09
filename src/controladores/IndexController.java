/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author julia_z20imv5
 */
public class IndexController implements Initializable {

    private MenuItem carrosMenu;
    @FXML
    private Button btnGestionCarros;
    @FXML
    private Button btnGestionMotos;
    @FXML
    private Button btnGestionClientes;
    @FXML
    private Button btnAlquilerCarros;
    @FXML
    private Button btnAlquilerMotos;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gestionCarros(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/carros.fxml"));
            Parent root = loader.load();
            CarrosController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            Stage stage1 = (Stage) this.btnGestionCarros.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionMotos(ActionEvent event) {

        System.out.println("llegamos a motos");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/motos.fxml"));
            Parent root = loader.load();
            MotosController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            stage.setResizable(false);
            Stage stage1 = (Stage) this.btnGestionMotos.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void gestionClientes(ActionEvent event) {

        System.out.println("llegamos a clientes");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/clientes.fxml"));
            Parent root = loader.load();
            ClientesController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage stage1 = (Stage) this.btnGestionClientes.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void irTodosCarros(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/todosCarros.fxml"));
            Parent root = loader.load();
            TodosCarrosController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnAlquilerCarros.getScene().getWindow();
            stage1.close();
            stage.setResizable(false);

        } catch (IOException ex) {
            Logger.getLogger(TodosCarrosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void alquilerMotos(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/todosMotos.fxml"));
            Parent root = loader.load();
            TodosMotosController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnAlquilerMotos.getScene().getWindow();
            stage1.close();
            stage.setResizable(false);

        } catch (IOException ex) {
            Logger.getLogger(TodosCarrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exit(ActionEvent event) {

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
