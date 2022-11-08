package negocio.entidades;

import java.util.ArrayList;

public class Marca {

    private String marca;
    private ArrayList<String> lineas;
    
    public Marca(String marca, ArrayList<String> lineas) {
        this.marca = marca;
        this.lineas = lineas;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public ArrayList<String> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }

    @Override
    public String toString() {
        String results = "";
        for(String linea: this.lineas){
            results += '/' + linea;
        
        }
        return marca + results;
    }

}