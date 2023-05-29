package logica;

public class PedidoException extends PrecioFinalException {
	private double precioFinal;

    public PedidoException(double precioFinal) {
        super(precioFinal);
        this.precioFinal = precioFinal;
    }

    public double getPrecio() {
        return precioFinal;
    }

}