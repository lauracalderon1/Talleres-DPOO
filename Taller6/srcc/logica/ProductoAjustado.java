package logica;

import java.util.ArrayList;

public class ProductoAjustado implements Producto{
	
	private static double precio;
	public static String nombre;

	public static ArrayList<Ingrediente> ingredientesparaAñadir = new ArrayList<Ingrediente>();
	
	// Métodos:
	
	public ProductoAjustado(ProductoMenu base) {
		precio = base.getPrecio();
		nombre = base.getNombre();
	}

	public double getPrecio() {
		for (int i = 0; i < ingredientesparaAñadir.size(); i++) {
			precio += ingredientesparaAñadir.get(i).getCostoAdicional();
		}
		return precio;
	}
	
	public String getNombre() {
		return nombre;
		
	}
	
	public String generarTextoFactura() {
		return " ";
	}
	
}