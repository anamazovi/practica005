package es.cic.curso.practica005.exception;

public class CursoNotFoundException extends RuntimeException {

    // Constructor vacío
    public CursoNotFoundException() {
        super();
    }

    public CursoNotFoundException(String mensaje) {
        super(mensaje);

    }

    public CursoNotFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}