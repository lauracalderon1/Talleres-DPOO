package logica;

public class ProductoMenu implements Producto {

	// Atributos:
	private String nombre;
	private int precioBase;
	
	// Métodos:
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		
	}
	
	public double getPrecio() {
		return precioBase;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String generarTextoFactura() {
		return " ";
	}
	
}
