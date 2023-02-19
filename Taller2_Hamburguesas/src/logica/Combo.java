package logica;

public class Combo implements Producto{
	
	// Atributos:
	private double descuento;
	private String nombreCombo;
	
	public Combo(double descuento, String nombreCombo) {
		this.setDescuento(descuento);
		this.nombreCombo = nombreCombo;
	}
	
	public String getNombre() {
		return nombreCombo;
	}
	
	public String generarTextoFactura() {
		return nombreCombo;
	}
	
	public double getPrecio() {
		return descuento;
	}
	
	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

}
