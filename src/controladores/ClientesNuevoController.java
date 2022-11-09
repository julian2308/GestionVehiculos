/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import gestores.GestorClientes;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.entidades.Carro;
import negocio.entidades.Cliente;

/**
 * FXML Controller class
 *
 * @author julia_z20imv5
 */
public class ClientesNuevoController implements Initializable {

    private String rutaImagen = null;

    private GestorClientes gestorClientes = new GestorClientes();

    @FXML
    private ImageView imagen;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellido;
    @FXML
    private TextField tfCedula;

    public void setInfo(Cliente info) {
        this.info = info;
    }
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnFoto;

    private Cliente info = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void guardarClienteNuevo(ActionEvent event) {

        if (!this.verificarValidaciones()) {

            String cedula = tfCedula.getText();
            String nombre = tfNombre.getText();
            String apellido = tfApellido.getText();
            String telefono = tfTelefono.getText();
            String email = tfEmail.getText();

            String imagenPath = "D:\\NetBeansProjects\\AdminVehiculos\\src\\archivos\\imagenesClientes\\Default.png";
            if (this.rutaImagen != null) {
                imagenPath = this.rutaImagen;
            }

            this.gestorClientes.anadirCliente(cedula, nombre, apellido, telefono, email, imagenPath);
            Alert alertaExitosa = new Alert(Alert.AlertType.INFORMATION);
            alertaExitosa.setTitle("Registro Exitoso");
            alertaExitosa.setHeaderText(null);
            alertaExitosa.setContentText("El cliente fue registrado con ÉXITO");
            alertaExitosa.initStyle(StageStyle.UTILITY);
            alertaExitosa.showAndWait();
            this.volver(event);
            if (this.info == null) {

            } else {
                this.gestorClientes.editarCliente(this.info.getCedula());
            }

        } else {

        }
    }

    @FXML
    private void volver(ActionEvent event) {

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

            Stage stage1 = (Stage) this.btnCancelar.getScene().getWindow();
            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage1 = (Stage) this.btnCancelar.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void buscarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            this.rutaImagen = imgFile.getAbsolutePath();
            System.out.println(imgFile.getAbsolutePath());
            this.rutaImagen = imgFile.getAbsolutePath();
            this.imagen.setImage(image);
        }
    }

    public void clienteExistente(Cliente miCliente) {

        this.tfCedula.setText(miCliente.getCedula());
        this.tfCedula.setEditable(false);
        this.tfNombre.setText(miCliente.getNombre());
        this.tfNombre.disabledProperty();
        this.tfApellido.setText(miCliente.getApellido());
        this.tfApellido.disabledProperty();
        this.tfTelefono.setText(miCliente.getTelefono());
        this.tfTelefono.disableProperty();
        this.tfEmail.setText(miCliente.getEmail());
        this.tfEmail.disabledProperty();

        this.btnGuardar.setDisable(true);
        Image image = new Image("file:" + miCliente.getFoto());
        this.imagen.setImage(image);
    }

    public void editarAtributos(Cliente miCliente) {

        this.tfCedula.setText(miCliente.getCedula());
        this.tfNombre.setText(miCliente.getNombre());
        this.tfApellido.setText(miCliente.getApellido());
        this.tfTelefono.setText(miCliente.getTelefono());
        this.tfEmail.setText(miCliente.getEmail());
        Image image = new Image("file:" + miCliente.getFoto());
        this.imagen.setImage(image);

    }

    private boolean verificarValidaciones() {
        boolean hayError = false;


        if (!this.tfEmail.getText().matches("[-\\w\\.]+@\\w+\\.\\w+")) {
            hayError = true;
            this.tfEmail.requestFocus();
        }

        if (this.tfTelefono.getText().isEmpty()) {
            hayError = true;
            this.tfTelefono.requestFocus();
        }

        if (this.tfCedula.getText().isEmpty()) {
            hayError = true;
            this.tfCedula.requestFocus();
        }

        if (this.tfApellido.getText().isEmpty()) {
            hayError = true;
            this.tfApellido.requestFocus();
        }

        if (this.tfNombre.getText().isEmpty()) {
            hayError = true;
            this.tfNombre.requestFocus();
        }

        return hayError;
    }

    private void ajustaCadena(int caso, String cadena, String caracter) {
        int len;
        len = cadena.length();
        if (len > 0) {

            for (char c : caracter.toCharArray()) {
                cadena = cadena.replace(String.valueOf(c), "");
            }
            switch (caso) {
                case 1:
                    this.tfNombre.setText(cadena);
                    this.tfNombre.positionCaret(len - 1);
                    break;
                case 2:
                    this.tfApellido.setText(cadena);
                    this.tfApellido.positionCaret(len - 1);
                    break;
                case 3:
                    this.tfCedula.setText(cadena);
                    this.tfCedula.positionCaret(len - 1);
                    break;
                case 4:
                    this.tfTelefono.setText(cadena);
                    this.tfTelefono.positionCaret(len - 1);
                    break;
            }

        }

    }

    @FXML
    private void validarNombre(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        int codigoAsci = (int) (c);
        if (event.getSource() == this.tfNombre) {
            if (!(c == java.awt.event.KeyEvent.VK_BACK_SPACE || Character.isAlphabetic(c) || c == java.awt.event.KeyEvent.VK_SPACE)) {
                this.ajustaCadena(1, this.tfNombre.getText(), Character.toString(c));
            }

        }
    }
    
    @FXML
    private void validarApellido(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        int codigoAsci = (int) (c);
        if (event.getSource() == this.tfApellido) {
            if (!(c == java.awt.event.KeyEvent.VK_BACK_SPACE || Character.isAlphabetic(c) || c == java.awt.event.KeyEvent.VK_SPACE)) {
                this.ajustaCadena(2, this.tfApellido.getText(), Character.toString(c));
            }

        }
    }

    @FXML
    private void validarCedula(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        int codigoAsci = (int) (c);
        if (event.getSource() == this.tfCedula) {
            if (!(c == java.awt.event.KeyEvent.VK_BACK_SPACE || Character.isDigit(c))) {
                this.ajustaCadena(3, this.tfCedula.getText(), Character.toString(c));
            }

        }
    }

    @FXML
    private void validarTelefono(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        int codigoAsci = (int) (c);
        if (event.getSource() == this.tfTelefono) {
            if (!(c == java.awt.event.KeyEvent.VK_BACK_SPACE || Character.isDigit(c))) {
                this.ajustaCadena(4, this.tfTelefono.getText(), Character.toString(c));
            }

        }
    }

}
