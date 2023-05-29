package logica;

public class IngredienteRepetidoException extends HamburguesaException {
    private String nombreIngrediente;

    public IngredienteRepetidoException(String nombreIngrediente) {
        super("El ingrediente: " + nombreIngrediente + " que desea agregar ya fue seleccionado previamente");
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }
    
}