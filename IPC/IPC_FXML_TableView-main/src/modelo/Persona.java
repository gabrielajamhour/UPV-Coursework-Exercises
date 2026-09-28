package modelo;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
	
	private final StringProperty nombre = new SimpleStringProperty();
	private final StringProperty apellidos = new SimpleStringProperty();
        
        private String imagePath;
		
	public Persona(String nombre, String apellidos)
	{
		this.nombre.setValue(nombre);
		this.apellidos.setValue(apellidos);
	}
	
        public Persona(String nombre, String apellidos, String imagen)
        {
            this.nombre.setValue(nombre);
            this.apellidos.setValue(apellidos);
            this.imagePath=imagen;
        }
            
        /**
         * Get the value of imagePath
         *
         * @return the value of imagePath
         */
        public String getImagePath() {
            return imagePath;
        }

        /**
         * Set the value of imagePath
         *
         * @param imagePath new value of imagePath
         */
        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

	public  StringProperty NombreProperty() {
		return this.nombre;
	}
	public String getNombre() {
		return this.NombreProperty().get();
	}
	public final void setNombre(String Nombre) {
		this.NombreProperty().set(Nombre);
	}
	public  StringProperty ApellidosProperty() {
		return this.apellidos;
	}
	public String getApellidos() {
		return this.ApellidosProperty().get();
	}
	public  void setApellidos(String Apellidos) {
		this.ApellidosProperty().set(Apellidos);
	}

}