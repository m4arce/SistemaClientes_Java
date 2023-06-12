package cl.desafiolatam.servicio;

/**
 * @author Marcela Arce
 */

import java.util.List;

import cl.desafiolatam.modelo.Cliente;

//clase abstracta segun requerimiento de Exportador
public abstract class Exportador {
	
	//metodo abstracto exportar
	public abstract void exportar(String fileName, List<Cliente> listaClientes);

}
