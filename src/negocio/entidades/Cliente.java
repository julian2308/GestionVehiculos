package negocio.entidades;

public class Cliente {
    
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String foto;
    
    public Cliente(String cedula, String nombre, String apellido, String telefono, String email, String foto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return this.cedula+ '/' +
                this.nombre + '/' +
                this.apellido + '/' +
                this.telefono + '/' +
                this.email + '/' +
                this.foto;

    }
   
}
