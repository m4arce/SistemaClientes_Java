package cl.desafiolatam.utilidades;

/**
 * @author Marcela Arce
 */

public class Utilidad {
	
	public static void limpiarPantalla() {		
		
			//saltos de linea en reemplazo de limpieza de pantalla

			for (int i = 0; i < 10; i++) {

				System.out.println(" ");
			}

        // Código para limpiar la pantalla de la consola que, no funciona, pero es el codigo sugerido
		/*
		 * try { if (System.getProperty("os.name").toLowerCase().contains("win") ||
		 * System.getProperty("os.name").toLowerCase().contains("windows") ||
		 * System.getProperty("os.name").contains("Windows")) { new
		 * ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); } else {
		 * Runtime.getRuntime().exec("clear"); } } catch (Exception e) { // Error al
		 * intentar limpiar la pantalla }
		 */
    }
    
	public static void esperar(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			System.out.println("Error al esperar.");
		}
	}

}
