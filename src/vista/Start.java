package vista;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application{

    public static void main (String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage ventana) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
        Scene scene = new Scene(root);
        
        ventana.setScene(scene);
        ventana.setTitle("RENTAR DE VEHICULOS");
        ventana.setResizable(false);
        ventana.setOnCloseRequest(event->{event.consume();});
        ventana.show();
    }
    
}
