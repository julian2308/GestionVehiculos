/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import gestores.GestorCarros;
import gestores.GestorMotos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import negocio.entidades.Carro;
import negocio.entidades.Moto;

/**
 * FXML Controller class
 *
 * @author julia_z20imv5
 */
public class MotosController implements Initializable {

    private GestorMotos gestor = new GestorMotos();
    
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void irCrear(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/motosNuevo.fxml"));
            Parent root = loader.load();
            MotosNuevoController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnCrear.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(MotosNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void irBuscarTodos(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/todosMotos.fxml"));
            Parent root = loader.load();
            TodosMotosController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(TodosMotosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void volverInicio(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/index.fxml"));
            Parent root = loader.load();
            IndexController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnVolver.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void buscar(ActionEvent event) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo");
        if (gestor.existePlaca(placa)) {

            Moto miMoto = gestor.devolverMoto(placa);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vista/motosNuevo.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Hola");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                // Pasamos la lista al controlador usando el método implementado
                MotosNuevoController controlador = (MotosNuevoController) fxmlLoader.getController();
                controlador.motoExistente(miMoto);
                
                stage.showAndWait();
                Stage stage1 = (Stage) this.btnBuscar.getScene().getWindow();
                stage1.close();

            } catch (IOException ex) {
                System.out.println("IO Exception: " + ex.getMessage());
            }
        } else {
            this.mostrarAlertaFallida();
        }

    }

    @FXML
    private void borrarMoto(ActionEvent event) {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo");
        
        if (gestor.existePlaca(placa)) {
            int confirmacion = JOptionPane.showConfirmDialog(null,"Está seguro que desea eliminar el auto con placa " + placa);
            if(confirmacion == JOptionPane.YES_OPTION){
                gestor.eliminarMoto(placa);
                System.out.println("Aca esta");
            }
            
            
        } else {
            System.out.println("Aca estany");
            this.mostrarAlertaFallida();
        }

    }

    private void mostrarAlertaFallida() {
        Alert alertaFallida = new Alert(Alert.AlertType.ERROR);
        alertaFallida.setTitle("Busqueda fallida");
        alertaFallida.setHeaderText(null);
        alertaFallida.setContentText("La placa ingresada no se encuentra en la base de datos");
        alertaFallida.initStyle(StageStyle.UTILITY);
        alertaFallida.showAndWait();

    }
    
    /*private void mostrarAlertaExitosa() {
        Alert alertaExitosa = new Alert(Alert.AlertType.INFORMATION);
        alertaExitosa.setTitle("Carro registado con ÉXITO");
        alertaExitosa.setHeaderText(null);
        alertaExitosa.setContentText("El carro fue registrado con ÉXITO");
        alertaExitosa.initStyle(StageStyle.UTILITY);
        alertaExitosa.showAndWait();

    }*/

    @FXML
    private void editarMoto(ActionEvent event) { 
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo");
        if (gestor.existePlaca(placa)) {

            Moto miMoto = gestor.devolverMoto(placa);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vista/motosNuevo.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Hola");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                // Pasamos la lista al controlador usando el método implementado
                MotosNuevoController controlador = (MotosNuevoController) fxmlLoader.getController();
                controlador.setInfo(miMoto);
                controlador.editarAtributos(miMoto);
                
                stage.showAndWait();
                
                Stage stage1 = (Stage) this.btnEditar.getScene().getWindow();
                stage1.close();

            } catch (IOException ex) {
                System.out.println("IO Exception: " + ex.getMessage());
            }
        } else {
            this.mostrarAlertaFallida();
        }
    }

    
}
