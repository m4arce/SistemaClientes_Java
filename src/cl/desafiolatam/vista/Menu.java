package cl.desafiolatam.vista;

/**
 * @author Marcela Arce
 */

import java.util.Scanner;

import cl.desafiolatam.modelo.CategoriaEnum;
import cl.desafiolatam.modelo.Cliente;
import cl.desafiolatam.servicio.ArchivoServicio;
import cl.desafiolatam.servicio.ClienteServicio;
import cl.desafiolatam.servicio.ExportadorCsv;
import cl.desafiolatam.servicio.ExportadorTxt;
import cl.desafiolatam.utilidades.Utilidad;

public class Menu {
	
	//atributos
	private ClienteServicio clienteServicio;
	private ArchivoServicio archivoServicio;
	private ExportadorCsv exportadorCsv;
	private ExportadorTxt exportadorTxt;
	private String fileName = "Clientes";
	private String fileName1 = "DBClientes.csv";
	private Scanner scanner;

	public Menu() {
		clienteServicio = new ClienteServicio();
		archivoServicio = new ArchivoServicio();
		exportadorCsv = new ExportadorCsv();
		exportadorTxt = new ExportadorTxt();
		scanner = new Scanner(System.in);
	}

	//iniciar menu
	public void iniciarMenu() {
		int opcion;
		do {			
			mostrarMenuPrincipal();
			System.out.print("Opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer de entrada

			switch (opcion) {
			case 1:
				listarClientes();
				break;
			case 2:
				agregarCliente();
				break;
			case 3:
				editarCliente();
				break;
			case 4:
				importarDatos();
				break;
			case 5:
				exportarDatos();
				break;
			case 6:
				terminarPrograma();
				break;
			default:
				System.out.println("Opción inválida. Inténtelo nuevamente.");

			}
		} while (opcion != 6);
	}

	//menu principal
	private void mostrarMenuPrincipal() {
		Utilidad.limpiarPantalla();
		System.out.println("----- MENÚ PRINCIPAL -----");
		System.out.println("1. Listar clientes");
		System.out.println("2. Agregar cliente");
		System.out.println("3. Editar cliente");
		System.out.println("4. Importar datos");
		System.out.println("5. Exportar datos");
		System.out.println("6. Terminar programa");
	}

	//listar
	private void listarClientes() {
		Utilidad.limpiarPantalla();
		Utilidad.esperar(3);
		clienteServicio.listarClientes();
	}

	//agregar
	private void agregarCliente() {
		Utilidad.limpiarPantalla();
		Utilidad.esperar(3);
		System.out.println("Ingrese los datos del cliente:");
		System.out.print("RUN: ");
		String runCliente = scanner.nextLine();
		System.out.print("Nombre: ");
		String nombreCliente = scanner.nextLine();
		System.out.print("Apellido: ");
		String apellidoCliente = scanner.nextLine();
		System.out.print("Años: ");
		String aniosCliente = scanner.nextLine();

		Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.ACTIVO);
		clienteServicio.agregarCliente(cliente);
	}

	//editar
	private void editarCliente() {
		Utilidad.limpiarPantalla();
		Utilidad.esperar(3);
		
		//valida si hay clientes en el sistema antes de editar
		if (clienteServicio.getListaClientes().isEmpty()) {
	        System.out.println("**No hay clientes para editar.**");
	        return;
		}

		//menu de edicion de cliente
		System.out.println("Editar Cliente");
		System.out.println("----------------");

		System.out.println("Seleccione qué desea hacer:");
		System.out.println("1. Cambiar el estado del Cliente");
		System.out.println("2. Editar los datos ingresados del Cliente");
		System.out.print("Ingrese opción: ");
		int opcion = scanner.nextInt();
		scanner.nextLine(); // Limpiar el buffer de entrada

		System.out.print("Ingrese RUN del Cliente a editar: ");
		String runCliente = scanner.nextLine();

		//busca cliente
		Cliente cliente = clienteServicio.buscarCliente(runCliente);
		if (cliente == null) {
			System.out.println("Cliente no encontrado.");

			return;
		}

		//cambio de estado: muestra estado del cliente y despliega menu para hacer los cambios
		if (opcion == 1) {
			System.out.println("----- Actualizando estado del Cliente -----");
			System.out.println("El estado actual es: " + cliente.getNombreCategoria());
			System.out.println("1. Si desea cambiar el estado del Cliente a Inactivo");
			System.out.println("2. Si desea mantener el estado del cliente Activo");
			System.out.print("Ingrese opción: ");
			int opcionEstado = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer de entrada

			if (opcionEstado == 1) {
				cliente.setNombreCategoria(CategoriaEnum.INACTIVO);
				System.out.println("Estado cambiado con éxito.");
			} else if (opcionEstado == 2) {
				cliente.setNombreCategoria(CategoriaEnum.ACTIVO);
				System.out.println("Estado mantenido como Activo.");
			} else {
				System.out.println("Opción inválida.");
			}
		//edicion de otros datos y muestra opciones de datos a editar
		} else if (opcion == 2) {
			System.out.println("---- Actualizando datos del Cliente ----");
			System.out.println("1. El RUN del Cliente es: " + cliente.getRunCliente());
			System.out.println("2. El Nombre del Cliente es: " + cliente.getNombreCliente());
			System.out.println("3. El Apellido del Cliente es: " + cliente.getApellidoCliente());
			System.out.println("4. Los años como Cliente son: " + cliente.getAniosCliente() + " años");
			System.out.print("Ingrese opción a editar de los datos del cliente: ");
			int opcionDato = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer de entrada

			switch (opcionDato) {
			case 1: //cambio de rut
				System.out.print("Ingrese nuevo RUN del Cliente: ");
				String nuevoRun = scanner.nextLine();
				cliente.setRunCliente(nuevoRun);
				break;
			case 2: //cambio nombre
				System.out.print("Ingrese nuevo Nombre del Cliente: ");
				String nuevoNombre = scanner.nextLine();
				cliente.setNombreCliente(nuevoNombre);
				break;
			case 3: //cambio apellido
				System.out.print("Ingrese nuevo Apellido del Cliente: ");
				String nuevoApellido = scanner.nextLine();
				cliente.setApellidoCliente(nuevoApellido);
				break;
			case 4: //cambio años
				System.out.print("Ingrese nuevos años como Cliente: ");
				String nuevosAnios = scanner.nextLine();
				scanner.nextLine(); // Limpiar el buffer de entrada
				cliente.setAniosCliente(nuevosAnios);
				break;
			default:
				System.out.println("Opción inválida.");
				break;
			}
			System.out.println("--------------------------");
			System.out.println("**Datos cambiados con éxito.**");
		} else {
			System.out.println("Opción inválida.");
		}
	}

	//importar
	private void importarDatos() {
		
		Utilidad.limpiarPantalla();
		Utilidad.esperar(3);

		String filePath = "";

		System.out.println("----------CARGAR DATOS---------");

		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			System.out.println("---------Cargar Datos en Windows---------------");
			System.out.print("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv: ");
			filePath = scanner.nextLine();
		} else {
			System.out.println("---------Cargar Datos en Linux o Mac-----------");
			System.out.print("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv: ");
			filePath = scanner.nextLine();
		}

		fileName1 = filePath + "/DBClientes.csv";
		archivoServicio.cargarDatos(fileName1, clienteServicio);

	}

	//exportar
	private void exportarDatos() {
		
		Utilidad.limpiarPantalla();
		Utilidad.esperar(3);
		
		if (clienteServicio.getListaClientes().isEmpty()) {
			System.out.println("No hay clientes para exportar.");
		} else {
			System.out.println("---------EXPORTAR DATOS---------");
			System.out.println("Seleccione el formato a exportar:");
			System.out.println("1. Formato csv");
			System.out.println("2. Formato txt");
			System.out.print("Ingrese una opción para exportar: ");
			int opcion = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer de entrada

			switch (opcion) {
			case 1:
				exportarCsv();
				break;
			case 2:
				exportarTxt();
				break;
			default:
				System.out.println("Opción inválida. Inténtelo nuevamente.");

			}
		}
	}

	//exportarCSV
	private void exportarCsv() {
		Utilidad.limpiarPantalla();
		Utilidad.esperar(3);
		String filePath = "";
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			System.out.println("---------Cargar Datos en Windows---------------");
			System.out.print("Ingresa la ruta donde desea exportar el archivo DBClientes.csv: ");
			filePath = scanner.nextLine();
		} else {
			System.out.println("---------Cargar Datos en Linux o Mac-----------");
			System.out.print("Ingresa la ruta donde desea exportar el archivo DBClientes.csv: ");
			filePath = scanner.nextLine();
		}

		fileName = filePath + "/" + fileName; //ruta
		exportadorCsv.exportar(fileName, clienteServicio.getListaClientes());

	}

	//exportarTXT
	private void exportarTxt() {
		Utilidad.limpiarPantalla();
		Utilidad.esperar(3);
		
		
		String filePath = "";
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			System.out.println("---------Cargar Datos en Windows---------------");
			System.out.print("Ingresa la ruta donde desea exportar el archivo Clientes.txt: ");
			filePath = scanner.nextLine();
		} else {
			System.out.println("---------Cargar Datos en Linux o Mac-----------");
			System.out.print("Ingresa la ruta donde desea exportar el archivo Clientes.txt: ");
			filePath = scanner.nextLine();
		}

		fileName = filePath + "/" + fileName; //ruta
		exportadorTxt.exportar(fileName, clienteServicio.getListaClientes());
	}

	private void terminarPrograma() {
		Utilidad.esperar(3);
		System.out.println("Gracias por utilizar el sistema. ¡Hasta luego!");
	}

}
