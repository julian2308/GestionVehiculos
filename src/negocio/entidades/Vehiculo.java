package negocio.entidades;

public class Vehiculo {

    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String linea;
    private String valorAlquiler;
    private String estaAlquilado;
    private String imagen;

    public Vehiculo(String placa, String marca, String linea, String modelo, String color, String valorAlquiler, String estaAlquilado, String imagen) {
        this.placa = placa;
        this.marca = marca;
        this.linea = linea;
        this.modelo = modelo;
        this.color = color;
        this.valorAlquiler = valorAlquiler;
        this.estaAlquilado = estaAlquilado;
        this.imagen = imagen;
    }
    
    public Vehiculo(){
        
    }
    
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValorAlquiler() {
        return valorAlquiler;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }
    
    public void setValorAlquiler(String valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    public String isEstaAlquilado() {
        return estaAlquilado;
    }

    public void setEstaAlquilado(String estaAlquilado) {
        this.estaAlquilado = estaAlquilado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    

    @Override
    public String toString() {
        return this.placa+ '/' +
                this.marca + '/' +
                this.linea + '/' +
                this.modelo + '/' +
                this.color + '/' +
                this.valorAlquiler + '/' +
                this.estaAlquilado + '/' +
                this.imagen;
    }
        
}