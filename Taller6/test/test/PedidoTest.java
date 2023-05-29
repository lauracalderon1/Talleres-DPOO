package test;

import logica.Pedido;
import logica.PedidoException;
import logica.Producto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PedidoTest {

    private Pedido pedido;

    @Before
    public void setup() {
        pedido = new Pedido("Laura", "Carrera 16");
    }

    @Test
    public void testGetIdPedido() {
        int idPedido = pedido.getIdPedido();
        Assert.assertEquals(0, idPedido);
    }

    @Test
    public void testAgregarProducto() throws PedidoException {
        Producto producto = new ProductoMock("corral", 14000);
        pedido.agregarProducto(producto);
        double precioNeto = pedido.getPrecioNetoPedido();
        Assert.assertEquals(14000, precioNeto, 0.001);
    }

    @Test(expected = PedidoException.class)
    public void testAgregarProductoExcedeLimite() throws PedidoException {
        Producto producto = new ProductoMock("corral", 200000);
        pedido.agregarProducto(producto);
    }

    @Test
    public void testGetNombre() {
        String nombreCliente = pedido.getNombre();
        Assert.assertEquals("Laura", nombreCliente);
    }

    @Test
    public void testGetDireccion() {
        String direccionCliente = pedido.getDireccion();
        Assert.assertEquals("Carrera 16", direccionCliente);
    }

    // Clase de prueba mock para simular un producto
    private class ProductoMock implements Producto {

        private String nombre;
        private double precio;

        public ProductoMock(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        @Override
        public String getNombre() {
            return nombre;
        }

        @Override
        public double getPrecio() {
            return precio;
        }

        @Override
        public String generarTextoFactura() {
            // Implementación del método generarTextoFactura simulado
            return "Texto de factura simulado para " + nombre;
        }
    }
}