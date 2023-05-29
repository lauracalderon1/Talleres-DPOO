package test;

import logica.Ingrediente;
import logica.ProductoAjustado;
import logica.ProductoMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductoAjustadoTest {

    private ProductoMenu productoBase;
    private ProductoAjustado productoAjustado;

    @Before
    public void setup() {
        productoBase = new ProductoMenu("corral", 14000);
        productoAjustado = new ProductoAjustado(productoBase);
    }

    @Test
    public void testGetPrecio() {
        Ingrediente ingrediente1 = new Ingrediente("lechuga", 1000);
        Ingrediente ingrediente2 = new Ingrediente("queso americano", 2500);

        productoAjustado.ingredientesparaAñadir.add(ingrediente1);
        productoAjustado.ingredientesparaAñadir.add(ingrediente2);

        double precio = productoAjustado.getPrecio();
        Assert.assertEquals(17500, precio, 0.001);
    }

    @Test
    public void testGetNombre() {
        String nombre = productoAjustado.getNombre();
        Assert.assertEquals("corral", nombre);
    }

    @Test
    public void testGenerarTextoFactura() {
        String textoFactura = productoAjustado.generarTextoFactura();
        Assert.assertEquals(" ", textoFactura);
    }
}