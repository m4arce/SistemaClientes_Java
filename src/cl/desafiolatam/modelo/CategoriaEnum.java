package cl.desafiolatam.modelo;

/**
 * @author Marcela Arce
 */

public enum CategoriaEnum {
	ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private String nombre;

    CategoriaEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static CategoriaEnum fromString(String nombre) {
        for (CategoriaEnum categoria : CategoriaEnum.values()) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("No se encontró una categoría válida para: " + nombre);
    }

}
