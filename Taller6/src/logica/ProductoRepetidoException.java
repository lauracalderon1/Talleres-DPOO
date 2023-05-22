
package logica;


public class ProductoRepetidoException extends HamburguesaException {
    private String nombreProducto;

    public ProductoRepetidoException(String nombreProducto) {
        super("El producto " + nombreProducto+ " que desea agregar ya fue agregado previamente");
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
}