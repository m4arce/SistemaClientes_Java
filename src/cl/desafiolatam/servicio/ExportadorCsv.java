package cl.desafiolatam.servicio;

/**
 * @author Marcela Arce
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cl.desafiolatam.modelo.Cliente;


public class ExportadorCsv extends Exportador {
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
    	File archivo = new File(fileName + ".csv");

		try {
			if (!archivo.exists()) {			

				// Crear el archivo
				archivo.createNewFile();
				// System.out.println("**El archivo "+ archivo +" ha sido creado exitosamente");
				BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));

				writer.write("----------LISTA DE CLIENTES---------");

				for (Cliente cliente : listaClientes) {
					writer.newLine();
					writer.write("RUN: " + cliente.getRunCliente());
					writer.newLine();
					writer.write("Nombre: " + cliente.getNombreCliente());
					writer.newLine();
					writer.write("Apellido: " + cliente.getApellidoCliente());
					writer.newLine();
					writer.write("Años: " + cliente.getAniosCliente());
					writer.newLine();
					writer.write("Categoría: " + cliente.getNombreCategoria());
					writer.close();
				}

				System.out.println("Datos de clientes exportados correctamente en formato CSV .");
			} else {
				System.out.println("El archivo ya existe.");
			}
		} catch (IOException e) {
			System.out.println("Error al exportar a CSV: " + e.getMessage());
			e.printStackTrace();
		}

    }
    
}


