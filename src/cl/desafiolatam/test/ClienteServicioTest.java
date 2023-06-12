package cl.desafiolatam.test;

/**
 * @author Marcela Arce
 */

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cl.desafiolatam.modelo.CategoriaEnum;
import cl.desafiolatam.modelo.Cliente;
import cl.desafiolatam.servicio.ClienteServicio;

public class ClienteServicioTest {
	
	private ClienteServicio clienteServicio;

    @BeforeEach
    public void setUp() {
        clienteServicio = new ClienteServicio();
    }

    @Test
    public void agregarClienteTest() {
        
        Cliente cliente = new Cliente("12345678-9", "John", "Doe", "30", CategoriaEnum.ACTIVO);

        
        clienteServicio.agregarCliente(cliente);
        List<Cliente> listaClientes = clienteServicio.getListaClientes();

        
        Assertions.assertEquals(1, listaClientes.size());
        Assertions.assertEquals(cliente, listaClientes.get(0));
    }

    @Test
    public void agregarClienteNullTest() {
        
        Cliente cliente = null;

        
        clienteServicio.agregarCliente(cliente);
        List<Cliente> listaClientes = clienteServicio.getListaClientes();

        
        Assertions.assertEquals(0, listaClientes.size());
    }


}
