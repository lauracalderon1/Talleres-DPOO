package logica;

import java.util.ArrayList;
import java.util.HashMap;

import consola.Aplicacion;

public class Pedido {
	
	// Atributos:
	
	private static int numeroPedidos;
	public static int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	
	private HashMap<String, ArrayList<String>> tablaPedido = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> listaProductos = new ArrayList<String>();
	private ArrayList<String> listaPrecios = new ArrayList<String>();
	public double precioFinal;
	
	// Métodos:
	
	public Pedido(String nombreCliente, String direccionCliente) {
			this.nombreCliente = nombreCliente;
			this.direccionCliente = direccionCliente;
			ArrayList<String> nombre = new ArrayList<String>();
			ArrayList<String> direccion = new ArrayList<String>();
			nombre.add(nombreCliente);
			direccion.add(direccionCliente);
			tablaPedido.put("Nombre cliente", nombre);
			tablaPedido.put("Dirección cliente", direccion);
			//Aplicacion.listaPedidos.add(tablaPedido);
		
	}
	
	public int getIdPedido() {
		return idPedido;
		
	}

	public void agregarProducto(Producto nuevoProducto) {
			
			listaProductos.add(nuevoProducto.getNombre());
			double precioProductoNuevo = nuevoProducto.getPrecio();
			String precioProductoNuevoEncadenado = Double.toString(precioProductoNuevo);
			listaPrecios.add(precioProductoNuevoEncadenado);
			tablaPedido.put("Productos", listaProductos);
			tablaPedido.put("Precios", listaPrecios);
			this.precioFinal += precioProductoNuevo;
			System.out.println("precio ajustado Pedido: " + precioProductoNuevo);
		}
	
	private double getPrecioNetoPedido() {
		return precioFinal;
	}
	
	private double getPrecioTotalPedido() {
		
		double precioFinal = (getPrecioNetoPedido() * 0.19) + getPrecioNetoPedido();
		return precioFinal;
	}

	private double getPrecioIVAPedido() {
		
		double precioIva = getPrecioNetoPedido() * 0.19;
		return precioIva;
	}
	
	private String generarTextoFactura() {
			
			String pneto = String.valueOf(getPrecioNetoPedido());
			String pfinal = String.valueOf(getPrecioTotalPedido());
			String piva = String.valueOf(getPrecioIVAPedido());
			String textoFactura = "Valor neto: $" + pneto + "   "
					+ "Valor final: $" + pfinal + "    "
							+ " Precio del IVA: $" + piva;
			
			return textoFactura;
		}
	
	public String guardarFactura() {
		String textoFactura = generarTextoFactura();
		return textoFactura; 
	}
	
	public String getNombre() {
		return nombreCliente;
	}

	public String getDireccion() {
		return direccionCliente;
	}
	
	public static int getNumeroPedidos() {
		return numeroPedidos;
	}

	public boolean contains(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	


}
