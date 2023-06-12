package cl.desafiolatam.servicio;

/**
 * @author Marcela Arce
 */

import java.util.ArrayList;
import java.util.List;
import cl.desafiolatam.modelo.Cliente;

public class ClienteServicio {	

	//atributos
	private List<Cliente> listaClientes;

	//constructor
	public ClienteServicio() {
		this.listaClientes = new ArrayList<>();

	}

	//getter y setters
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	//listar
	public void listarClientes() {
		if (listaClientes.isEmpty()) {
			System.out.println("**No se han agregado clientes.**");
		} else {
			System.out.println("----------LISTA DE CLIENTES----------");
			for (Cliente cliente : listaClientes) {
				System.out.println(cliente.toString());
			}
		}

	}

	//agregar
	public void agregarCliente(Cliente cliente) {
		listaClientes.add(cliente);
		System.out.println("**Cliente agregado correctamente.**");

	}

	//editar
	public void editarCliente(Cliente cliente) {
		

	}

	//buscar
	public Cliente buscarCliente(String runCliente) {
		return listaClientes.stream().filter(cliente -> cliente.getRunCliente().equals(runCliente)).findFirst()
				.orElse(null);
	}
}
