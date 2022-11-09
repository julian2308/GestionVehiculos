/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import gestores.GestorCarros;
import gestores.GestorMarcas;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.entidades.Carro;
import negocio.entidades.Marca;

/**
 * FXML Controller class
 *
 * @author julia_z20imv5
 */
public class CarrosNuevoController implements Initializable {

    @FXML
    private TextField tfAlquiler;
    @FXML
    private TextField tfPlaca;
    @FXML
    private ChoiceBox<String> cbModelo;
    @FXML
    private ChoiceBox<String> cbColor;
    @FXML
    private ChoiceBox<String> cbLinea;
    @FXML
    private ChoiceBox<String> cbMarca;
    @FXML
    private Button btnCancelar;

    private Carro info = null;

    private String rutaImagen = null;

    @FXML
    private Button btnGuardar;
    @FXML
    private ImageView imagen;
    @FXML
    private Button btnBuscar;

    public void setInfo(Carro info) {
        this.info = info;
    }

    private GestorCarros gestorCarros = new GestorCarros();
    private GestorMarcas gestorMarcas = new GestorMarcas();

    private String[] colores = {"Rojo", "Azul", "Gris", "Blanco", "Negro"};
    private String[] modelos = {"2017", "2018", "2019", "2020", "2021"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.cbColor.getItems().addAll(this.colores);
        this.cbModelo.getItems().addAll(this.modelos);
        this.llamarMarcas();

        ArrayList misLineas = this.lineasBoton();

        ArrayList<String> lineas0 = (ArrayList<String>) misLineas.get(0);
        ArrayList<String> lineas1 = (ArrayList<String>) misLineas.get(1);
        ArrayList<String> lineas2 = (ArrayList<String>) misLineas.get(2);
        ArrayList<String> lineas3 = (ArrayList<String>) misLineas.get(3);
        ArrayList<String> lineas4 = (ArrayList<String>) misLineas.get(4);

        this.cbMarca.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue ov, Number value, Number newValue) {

                //cbLinea.setValue(choiceBat[newValue.intValue()]);
                //cbLinea.setItems(FXCollections.observableArrayList(choiceBat));
                switch (newValue.intValue()) {
                    case 0 ->
                        cbLinea.setItems(FXCollections.observableArrayList(lineas0));
                    case 1 ->
                        cbLinea.setItems(FXCollections.observableArrayList(lineas1));
                    case 2 ->
                        cbLinea.setItems(FXCollections.observableArrayList(lineas2));
                    case 3 ->
                        cbLinea.setItems(FXCollections.observableArrayList(lineas3));
                    case 4 ->
                        cbLinea.setItems(FXCollections.observableArrayList(lineas4));
                    default -> {
                    }

                }
            }

        });

        //acaaaaaaa
        /*btnBuscar.setOnAction(event -> {
            
        });*/
    }

    private void llamarMarcas() {
        ObservableList salu2 = this.marcasBoton();
        this.cbMarca.setItems(salu2);
    }

    private void getMarcas(ActionEvent event) {

        ArrayList marcasObtenidas = this.gestorMarcas.obtenerMarcasCarros();
        /*System.out.println(marcas.size() + " aca");
        this.misMarcas = marcas;*/

        ArrayList<String> nombresMarcas = new ArrayList<>();
        List<List<String>> listaLineas = new ArrayList<>();

        for (int i = 0; i < marcasObtenidas.size(); i++) {
            Marca xd = (Marca) marcasObtenidas.get(i);
            nombresMarcas.add(xd.getMarca());
            System.out.println(xd.getMarca() + "aqui");
            listaLineas.add(xd.getLineas());
            System.out.println(xd.getLineas() + "aqui lineas");
        }

        System.out.println(nombresMarcas.toString());
        System.out.println(listaLineas.toString());
    }

    @FXML
    private void guardarCarroNuevo(ActionEvent event) {

        if (!this.verificarValidaciones()) {

            String placa = tfPlaca.getText();

                String linea = cbLinea.getValue();
                String marca = cbMarca.getValue();
                String modelo = cbModelo.getValue();
                String color = cbColor.getValue();
                String valorAlquiler = tfAlquiler.getText();

                String imagenPath = "D:\\NetBeansProjects\\AdminVehiculos\\src\\archivos\\imagenesCarros\\Default.jpg";
                if (this.rutaImagen != null) {
                    imagenPath = this.rutaImagen;
                }
                
                this.gestorCarros.anadirCarro(placa, marca, linea, modelo, color, valorAlquiler, "No", imagenPath);
                ObservableList salu2 = this.marcasBoton();
                this.cbMarca.setItems(salu2);
                Alert alertaExitosa = new Alert(AlertType.INFORMATION);
                alertaExitosa.setTitle("Registro Exitoso");
                alertaExitosa.setHeaderText(null);
                alertaExitosa.setContentText("El carro fue registrado con ÉXITO");
                alertaExitosa.initStyle(StageStyle.UTILITY);
                alertaExitosa.showAndWait();
                this.volver(event);
                if (this.info == null) {
                    System.out.println("Es null");
                } else {
                    this.gestorCarros.editarCarro(this.info.getPlaca());
                }



        } else {
            System.out.println("Esta mal");
        }
    }

    @FXML
    private void volver(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/carros.fxml"));
            Parent root = loader.load();
            CarrosController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnCancelar.getScene().getWindow();
            stage1.close();
            stage.setResizable(false);

        } catch (IOException ex) {
            Logger.getLogger(CarrosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage1 = (Stage) this.btnCancelar.getScene().getWindow();
        stage1.close();

    }

    //Métodos propios
    private ObservableList marcasBoton() {
        ArrayList marcasObtenidas = this.gestorMarcas.obtenerMarcasCarros();

        ObservableList misNombresMarcas = FXCollections.observableArrayList();
        for (int i = 0; i < marcasObtenidas.size(); i++) {
            Marca xd = (Marca) marcasObtenidas.get(i);
            misNombresMarcas.add(xd.getMarca());
        }

        return misNombresMarcas;
    }

    private ArrayList lineasBoton() {
        ArrayList marcasObtenidas = this.gestorMarcas.obtenerMarcasCarros();
        ArrayList misNombresLineas = new ArrayList();
        for (int i = 0; i < marcasObtenidas.size(); i++) {
            Marca xd = (Marca) marcasObtenidas.get(i);
            misNombresLineas.add(xd.getLineas());
        }

        return misNombresLineas;
    }

    public void carroExistente(Carro miCarro) {

        this.tfPlaca.setText(miCarro.getPlaca());
        this.tfPlaca.setEditable(false);
        this.cbMarca.setValue(miCarro.getMarca());
        this.cbMarca.disabledProperty();
        this.cbModelo.setValue(miCarro.getModelo());
        this.cbModelo.disabledProperty();
        this.cbLinea.setValue(miCarro.getLinea());
        this.cbLinea.disableProperty();
        this.cbColor.setValue(miCarro.getColor());
        this.cbColor.disabledProperty();
        this.tfAlquiler.setText(miCarro.getValorAlquiler());
        this.tfAlquiler.setEditable(false);
        this.btnGuardar.setDisable(true);
        Image image = new Image("file:" + miCarro.getImagen());
        this.imagen.setImage(image);
    }

    public void editarAtributos(Carro miCarro) {

        this.tfPlaca.setText(miCarro.getPlaca());
        this.cbMarca.setValue(miCarro.getMarca());
        this.cbModelo.setValue(miCarro.getModelo());
        this.cbLinea.setValue(miCarro.getLinea());
        this.cbColor.setValue(miCarro.getColor());
        this.tfAlquiler.setText(miCarro.getValorAlquiler());
        Image image = new Image("file:" + miCarro.getImagen());
        this.imagen.setImage(image);

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

    @FXML
    private void validarPlaca(KeyEvent event) {

        char c = event.getCharacter().charAt(0);
        int codigoAsci = (int) (c);
        if (event.getSource() == this.tfPlaca) {
            if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || Character.isAlphabetic(c) || c == java.awt.event.KeyEvent.VK_SPACE)) {
                this.ajustaCadena(1, this.tfPlaca.getText(), Character.toString(c));
            }

        }

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
                    this.tfPlaca.setText(cadena);
                    this.tfPlaca.positionCaret(len - 1);
                    break;
                case 2:
                    this.tfAlquiler.setText(cadena);
                    this.tfAlquiler.positionCaret(len - 1);
            }

        }

    }

    @FXML
    private void validarAlquiler(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        int codigoAsci = (int) (c);
        if (event.getSource() == this.tfAlquiler) {
            if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE)) {
                this.ajustaCadena(2, this.tfAlquiler.getText(), Character.toString(c));
            }

        }

    }

    private boolean verificarValidaciones() {
        boolean hayError = false;
        //System.out.println(this.tfPlaca.getText().matches("[A-Z]{3}[ ][0-9]{3}") + " ACAAAAAAAAA");

        if (this.tfAlquiler.getText().isEmpty()) {
            hayError = true;
            this.tfAlquiler.requestFocus();
        }

        if (this.cbColor.getValue() == null) {
            this.cbColor.requestFocus();
            hayError = true;
        }
        if (this.cbModelo.getValue() == null) {
            this.cbModelo.requestFocus();
            hayError = true;
        }
        if (this.cbLinea.getValue() == null) {
            this.cbLinea.requestFocus();
            hayError = true;

        }

        if (this.cbMarca.getValue() == null) {
            this.cbMarca.requestFocus();
            hayError = true;
        }

        if (!this.tfPlaca.getText().matches("[A-Z]{3}[ ][0-9]{3}")) {
            hayError = true;
            this.tfPlaca.requestFocus();
        }

        System.out.println(this.tfAlquiler.getText().isEmpty() + "vacio");

        return hayError;
    }
}
