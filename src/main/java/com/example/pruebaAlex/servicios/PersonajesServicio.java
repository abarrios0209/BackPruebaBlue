package com.example.pruebaAlex.servicios;

import com.example.pruebaAlex.dtos.PersonajeDto;
import com.example.pruebaAlex.dtos.PokemonDto;
import com.example.pruebaAlex.dtos.RespuestGenericaDto;
import com.example.pruebaAlex.entidades.PersonajeEntidad;

import java.util.Date;
import java.util.Optional;

public interface PersonajesServicio {

    RespuestGenericaDto<PersonajeDto> personajeAleatorio();

    RespuestGenericaDto<String> darLike( PersonajeDto personajeDto );

    RespuestGenericaDto<String> darDislike( PersonajeDto personajeDto );

    RespuestGenericaDto<PersonajeDto> obtenerPersonajeConMasLikes();

    RespuestGenericaDto<PersonajeDto> obtenerPersonajeConMasDislikes();

    RespuestGenericaDto<PersonajeDto> obtenerStatusPikachu();
}
