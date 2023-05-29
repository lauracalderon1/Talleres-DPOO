package test;

import logica.ProductoMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductoMenuTest {

    private ProductoMenu productoMenu;

    @Before
    public void setup() {
        productoMenu = new ProductoMenu("mexicana", 22000);
    }

    @Test
    public void testGenerarTextoFactura() {
        String textoFactura = productoMenu.generarTextoFactura();
        Assert.assertEquals(" ", textoFactura);
    }
}