package com.example.pruebaAlex.servicios;

import com.example.pruebaAlex.dtos.PersonajeDto;
import com.example.pruebaAlex.dtos.RespuestGenericaDto;

public interface PersonajesServicio {

    RespuestGenericaDto<PersonajeDto> personajeAleatorio();

    RespuestGenericaDto<String> darLike( PersonajeDto personajeDto );

    RespuestGenericaDto<String> darDislike( PersonajeDto personajeDto );

    RespuestGenericaDto<PersonajeDto> obtenerPersonajeConMasLikes();

    RespuestGenericaDto<PersonajeDto> obtenerPersonajeConMasDislikes();

    RespuestGenericaDto<PersonajeDto> obtenerStatusPikachu();
}
