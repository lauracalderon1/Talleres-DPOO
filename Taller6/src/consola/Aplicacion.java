package consola;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import logica.Combo;
import logica.ProductoAjustado;
import logica.Restaurante;
import logica.Pedido;
import logica.PedidoException;
import logica.ProductoMenu;
import logica.ProductoRepetidoException;
import logica.Ingrediente;
import logica.IngredienteRepetidoException;

import java.util.List;
import java.util.ArrayList;

public class Aplicacion {
	
	public void mostrarMenu() {
		System.out.println("Menú de Opciones ");
		System.out.println("1. Iniciar un nuevo pedido ");
		System.out.println("2. Mostrar el menu ");
		System.out.println("3. Cerrar mi pedido y mostrar factura");
		System.out.println("4. Consultar mi pedido dado su id");
		System.out.println("5. Salir");	
	}
	
	
	public static ArrayList<String> listaPedidos = new ArrayList<String>();
	private Restaurante restaurante = new Restaurante();
	private Pedido pedido;
	private String modificacion = "";
	
	public static void main(String[] args) throws IOException, IngredienteRepetidoException, ProductoRepetidoException, PedidoException {
		Aplicacion consola = new Aplicacion();
		System.out.println("\n👋 Bienvenido a la tienda de hamburguesas 🍔, para iniciar un nuevo pedido ingresa 1 ");
		consola.cargarArchivos();
		consola.ejecutarOpcion();
	}
	
	public void cargarArchivos() throws IOException, IngredienteRepetidoException, ProductoRepetidoException {
   
		restaurante.cargarInformacionRestaurante
		("./data/ingredientes.txt", 
				"./data/menu.txt",
				"./data/combos.txt");

	}

	public void ejecutarOpcion() throws FileNotFoundException, UnsupportedEncodingException, PedidoException {
		boolean iniciado = false;
		boolean agregado = false;
		boolean finalizado = false;
		boolean continuar = true;
		int pedidoAbierto = 0;
		while (continuar) {
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleciona una opcion"));
				if (opcion_seleccionada == 1) {
					if (pedidoAbierto == 1) {
						System.out.print("\n No puedes iniciar con  tu pedido hasta finalizar el anterior ");
					}
					else {
						iniciado = true;
						pedidoAbierto = 1;
						iniciar_pedido();
					}
				}
				else if (opcion_seleccionada == 2) {
					if (iniciado) {
						try {
							agregar_elemento();
						} catch (ProductoRepetidoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						agregado = true;
					}
					else
						System.out.print("\n Debes iniciar un pedido antes de ver el menu.");
				}
				else if (opcion_seleccionada == 3) {
					if (iniciado) {
						if (agregado) {
							finalizar_pedido();
							finalizado = true;
							iniciado = false;
							agregado = false;
						}
						else
							System.out.print("\n Debes ver el menu y ordenar algun producto antes de finalizar tu pedido.");
					}
					else
						System.out.print("\n No puedes finalizar tu pedido antes de iniciarlo.");
				}				
				else if (opcion_seleccionada == 4)
					
					if (finalizado)
							consultar_pedido();
					else
							System.out.print("\n Debes finalizar tu pedido antes de poder consultarlo.");
					
				else if (opcion_seleccionada == 5) {
					System.out.println("\nSaliendo de la aplicacion...");
					continuar = false;
				} 
				else
					System.out.println("\n Debes seleccionar uno de los numeros de las opciones");
			}
			catch (NumberFormatException e)
			{
				System.out.println("\n Debes seleccionar uno de los numeros de las opciones");
			}
		}
	}
	
	
	private void iniciar_pedido() {
		String nombreCliente = input("\nPor favor ingresa tu nombre");
		listaPedidos.add(nombreCliente);
		String direccionCliente = input("Por favor ingresa la direccion de envio");
		listaPedidos.add(direccionCliente);
		Pedido.idPedido += 1;
		
		
		this.pedido = restaurante.iniciarPedido(nombreCliente, direccionCliente);
		System.out.println("\nHola " + nombreCliente + ", recuerda que el ID de tu pedido es: " + Pedido.idPedido + ".");
		System.out.println("Selecciona la opcion 2 para ver el menu.");
	}
	
	private ArrayList<ProductoMenu> listaValores = new ArrayList<>();
	private ArrayList<Ingrediente> listaIngredientes = new ArrayList<>();
	
	private void agregar_elemento() throws ProductoRepetidoException, PedidoException {
		boolean continuar = true;
		while (continuar) {
			try {
				int menu = Integer.parseInt(input("Ingresa 1 si deseas ver los Productos y 2 si deseas ver los Combos"));
				if (menu == 1) {
					continuar = false;
					System.out.println("\nPRODUCTOS:       \n");
					ArrayList<ProductoMenu> productosMenu = restaurante.getMenuBase();
					for (int i = 0; i < productosMenu.size(); i++) {
						ProductoMenu valorP = productosMenu.get(i);
						System.out.println((i+1) + ". " + valorP.getNombre() + " ----------------- $" + valorP.getPrecio());
					}
					boolean vistaProducto = true;
					while (vistaProducto) {
						try {
							int numProducto = Integer.parseInt(input("\nIngresa el numero del producto que deseas agregar"));
							if (numProducto > productosMenu.size()) 
								System.out.println("\nPor favor ingresa una opcion valida.\n");
							else {
								vistaProducto = false;
								ProductoMenu valorP = productosMenu.get(numProducto-1);
								
					
								// Agregar valorP a la lista

						        // Verificar si valorP ya está en la lista
								
						        if (listaValores.contains(valorP)) {
						        	
					 
						            throw new ProductoRepetidoException(valorP.getNombre());
								}
								
						        listaValores.add(valorP);
								
								boolean continuar0 = true;
								
								while (continuar0) {
									try {
										int modificar = Integer.parseInt(input("\nPara agregar o quitar algun ingrediente ingresa 1. De lo contrario ingresa 0"));
										if (modificar == 1){
											ProductoAjustado valorPA = new ProductoAjustado(valorP);
											continuar0 = false;
											boolean continuar1 = true;
											while (continuar1) {
												try {
													System.out.println("\n--------------- INGREDIENTES ---------------\n");
													ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
													for (int i = 0; i < ingredientes.size(); i++) {
														Ingrediente valorI = ingredientes.get(i);
														System.out.println((i+1) + ". " + valorI.getNombre() + " ----------------- $" + valorI.getCostoAdicional());
													}
													
													boolean continuarI = true;
													while (continuarI) {
														try {
															int numIngrediente = Integer.parseInt(input("\nIngresa el numero del ingrediente que deseas agregar o quitar"));
															if (numIngrediente > ingredientes.size())
																System.out.println("\nPor favor ingresa una numero valido \n");
															else {
																continuarI = false;
																Ingrediente valorI = ingredientes.get(numIngrediente-1);
																
																boolean continuar12 = true;
																while (continuar12) {
																	try {
																		int accionIngrediente = Integer.parseInt(input("\nIngresa 1 si deseas agregar el ingrediente o 0 si deseas quitarlo"));
																		if (accionIngrediente == 1) {
																			ProductoAjustado.nombre += " con " + valorI.getNombre();
																			valorPA.ingredientesparaAñadir.add(valorI);
																			continuar1 = false;
																			continuar12 = false;
																			
																			if (listaIngredientes.contains(valorI)) {
																	        	
																				 
																	            throw new ProductoRepetidoException(valorI.getNombre());
																			}
																			
																	        listaIngredientes.add(valorI);
																	        
																			
																		}
																		else if (accionIngrediente == 0) {
																			ProductoAjustado.nombre += " sin " + valorI.getNombre();
																			continuar1 = false;
																			continuar12 = false;
																		}
																		else
																			System.out.println("\nPor favor ingresa una opcion valida.\n");
																	}
																	catch (NumberFormatException e)
																	{
																		System.out.println("\nPor favor ingresa una opcion valida.\n");
																	}
																}
																boolean continuar2 = true;
																while (continuar2) {
																	try {
																		int seguir = Integer.parseInt(input("Para seguir modificando los ingredientes del producto " + valorP.getNombre() + " ingresa 1. De lo contrario ingresa 0"));
																		if (seguir == 1){
																			continuar1 = true;
																			continuar2 = false;
																		}
																		else if (seguir == 0) {
																			
																			pedido.agregarProducto(valorPA);
																			System.out.println("\nEl producto " + valorPA.getNombre() + modificacion + " se agrego correctamente a tu pedido.");
																			System.out.println("\nTotal: $" + pedido.precioFinal);
																			System.out.println("Para seguir agregando elementos selecciona la opcion 2.");
																			continuar2 = false;
																		}
																		else
																			System.out.println("\nPor favor ingresa una opcion valida.\n");
																	}
																	catch (NumberFormatException e)
																	{
																		System.out.println("\nPor favor ingresa una opcion valida.\n");
																	}
																}
															}
														}
														catch (NumberFormatException e)
														{
															System.out.println("\nPor favor ingresa una opcion valida.\n");
														}
													}
													
												}
												catch (NumberFormatException e)
												{
													System.out.println("\nPor favor ingresa una opcion valida.\n");
												}
											}
										}
										else if (modificar == 0) {
											continuar0 = false;
											pedido.agregarProducto(valorP);
											System.out.println("\nEl producto " + valorP.getNombre() + " se agrego correctamente a tu pedido.");
											System.out.println("\nPor el momento, el total de tu compra es de: $" + pedido.precioFinal);
											System.out.println("Para seguir agregando elementos selecciona la opcion 2. Para finalizar tu pedido ingresa 3.");
										}
										else 
											System.out.println("\nPor favor ingresa una opcion valida.\n");
									}
									catch (NumberFormatException e)
									{
										System.out.println("\nPor favor ingresa una opcion valida.\n");
									}
								}
							}
						}
						catch (NumberFormatException e)
						{
							System.out.println("\nPor favor ingresa una opcion valida.\n");
						}
					}
				}
				else if (menu == 2){
					continuar = false;
					System.out.println("\n--------------- COMBOS ---------------\n");
					ArrayList<Combo> combos = restaurante.getCombos();
					for (int i = 0; i < combos.size(); i++) {
						Combo valorC = combos.get(i);
						System.out.println((i+1) + ". " + valorC.getNombre() + " ----------------- $" + valorC.getPrecio());
					}
					boolean continuarC = true;
					while (continuarC) {
						int numCombo = Integer.parseInt(input("\nIngresa el numero del combo que deseas agregar"));
						if (numCombo > combos.size())
							System.out.println("\nPor favor ingresa una opcion valida.\n");
						else {
							continuarC = false;
							Combo valorC = combos.get(numCombo-1);
							pedido.agregarProducto(valorC);
							System.out.println("\nEl " + valorC.getNombre() + " se agrego correctamente a tu pedido.");
							System.out.println("\nTotal: $" + pedido.precioFinal);
							System.out.println("Para seguir agregando elementos selecciona la opcion 2. Para finalizar tu pedido ingresa 3.");
						}
					}
				}
				else
					System.out.println("\nPor favor ingresa una opcion valida.\n");
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nPor favor ingresa una opcion valida.\n");
			}
		}
	}
	
	private void finalizar_pedido() throws FileNotFoundException, UnsupportedEncodingException {
		System.out.println("Se acaba de cerrar su pedido exitosamente ✅ ");
		System.out.println("");
		System.out.println("Factura: ");
		System.out.println(restaurante.cerrarYGuardarPedido());
		System.out.println("");
		int id = pedido.getIdPedido();
		PrintWriter writer = new PrintWriter(String.valueOf(id)+ ".txt","UTF-8");


		writer.close();
	}

	private void consultar_pedido() 
		{
		boolean continuarC = true;	
		while (continuarC) 
			{
			int id = Integer.parseInt(input("Ingrese el ID de su pedido"));
			if (id == 1) {
				continuarC = false;
				System.out.println("La información del pedido que busca es: ");
				System.out.println("Nombre: " + listaPedidos.get(0));
				System.out.println("Dirección: " + listaPedidos.get(1));
				System.out.println("Factura: " + restaurante.cerrarYGuardarPedido());
				System.out.println("  ");
			
				
				
			
			}
			else {
				System.out.println("\nPor favor ingresa una opcion valida.\n");
			 	}
			}
		}
	
	public String input(String textoFactura)
	{
		try
		{
			System.out.print(textoFactura + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	


	

}