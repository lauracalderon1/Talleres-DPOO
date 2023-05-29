package test;

import logica.Combo;
import org.junit.Assert;
import org.junit.Test;

public class ComboTest {


    @Test
    public void testGenerarTextoFactura() {
        Combo combo = new Combo(0.1, "combo especial");
        Assert.assertEquals("combo especial", combo.generarTextoFactura());
    }

    @Test
    public void testGetDescuento() {
        Combo combo = new Combo(0.1, "combo corral queso");
        Assert.assertEquals(0.1, combo.getDescuento(), 0.001);
    }

    @Test
    public void testSetDescuento() {
        Combo combo = new Combo(0.1, "combo corral");
        combo.setDescuento(0.2);
        Assert.assertEquals(0.2, combo.getDescuento(), 0.001);
    }
}