package com.example.pruebaAlex.dtos;

import lombok.Data;

@Data
public class RespuestGenericaDto<T> {

    private Boolean isSuccess = false;
    private T data = null;
    private String message = null;

    public RespuestGenericaDto()
    {
    }

    public RespuestGenericaDto( Boolean isSuccess, T data, String message )
    {
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }

    public void successful( T data )
    {
        this.data = data;
        this.isSuccess = true;
    }

    public void successful( T data, String message )
    {
        successful( data );
        setMessage( message );
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

}
