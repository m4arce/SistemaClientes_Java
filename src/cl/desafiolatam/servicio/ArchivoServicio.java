package cl.desafiolatam.servicio;

/**
 * @author Marcela Arce
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import cl.desafiolatam.modelo.CategoriaEnum;
import cl.desafiolatam.modelo.Cliente;

public class ArchivoServicio extends Exportador {

	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) {

	}

	public void cargarDatos(String fileName1, ClienteServicio clienteServicio) {

		try {
			FileReader fileReader = new FileReader(fileName1);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(",");

				String runCliente = data[0];
				String nombreCliente = data[1];
				String apellidoCliente = data[2];
				String aniosCliente = data[3];
				CategoriaEnum nombreCategoria = CategoriaEnum.valueOf(data[4].toUpperCase());

				Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente,
						nombreCategoria);
				// if (nombreCategoria.equals("INACTIVO"))

				clienteServicio.agregarCliente(cliente);
			}

			System.out.println("**Datos cargados correctamente en la lista**");

			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println("**Error al cargar los datos desde el archivo.**" + e.getMessage());
		}

	}
}
