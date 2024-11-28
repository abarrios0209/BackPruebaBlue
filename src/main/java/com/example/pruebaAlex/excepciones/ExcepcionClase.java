package com.example.pruebaAlex.excepciones;

import java.io.Serial;

public class ExcepcionClase extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ExcepcionClase ( String message )
    {
        super ( message );
    }

    public ExcepcionClase ( Throwable cause )
    {
        super ( cause );
    }

    public ExcepcionClase ( String message, Throwable cause )
    {
        super ( message, cause );
    }
}
