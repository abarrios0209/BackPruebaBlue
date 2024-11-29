package com.example.pruebaAlex.controladores;

import com.example.pruebaAlex.dtos.PersonajeDto;
import com.example.pruebaAlex.dtos.RespuestGenericaDto;
import com.example.pruebaAlex.excepciones.ExcepcionClase;
import com.example.pruebaAlex.servicios.PersonajesServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personajes")
public class PersonajesControlador {

    private final PersonajesServicio personajesService;

    public PersonajesControlador(PersonajesServicio personajesService) {
        this.personajesService = personajesService;
    }

    @GetMapping
    public ResponseEntity<RespuestGenericaDto<PersonajeDto>> obtenerPersonajeAleatorio() throws Exception {

        try {
            RespuestGenericaDto<PersonajeDto> response = personajesService.personajeAleatorio();
            return Boolean.TRUE.equals( response.getIsSuccess() ) ? ResponseEntity.ok( response ) : ResponseEntity.status(HttpStatus.BAD_REQUEST ).body( response ) ;
        } catch ( Exception e ){
            throw new ExcepcionClase( e.getMessage() );
        }
    }

    @PostMapping("/like")
    public ResponseEntity<RespuestGenericaDto<String>> like(@RequestBody PersonajeDto personaje) {
        try {
            RespuestGenericaDto<String> response = personajesService.darLike( personaje );
            return Boolean.TRUE.equals( response.getIsSuccess() ) ? ResponseEntity.ok( response ) : ResponseEntity.status(HttpStatus.BAD_REQUEST ).body( response ) ;
        } catch ( Exception e ){
            throw new ExcepcionClase( e.getMessage() );
        }
    }

    @PostMapping("/dislike")
    public ResponseEntity<RespuestGenericaDto<String>> dislike( @RequestBody PersonajeDto personaje ) {
        try {
            RespuestGenericaDto<String> response = personajesService.darDislike( personaje );
            return Boolean.TRUE.equals( response.getIsSuccess() ) ? ResponseEntity.ok( response ) : ResponseEntity.status(HttpStatus.BAD_REQUEST ).body( response ) ;
        } catch ( Exception e ){
            throw new ExcepcionClase( e.getMessage() );
        }
    }

    @GetMapping("/con-mas-likes")
    public ResponseEntity<RespuestGenericaDto<PersonajeDto>> obtenerPersonajeConMasLikes() {
        try {
            RespuestGenericaDto<PersonajeDto> respuesta = personajesService.obtenerPersonajeConMasLikes();
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/con-mas-dislikes")
    public ResponseEntity<RespuestGenericaDto<PersonajeDto>> obtenerPersonajeConMasDislikes() {
        try {
            RespuestGenericaDto<PersonajeDto> respuesta = personajesService.obtenerPersonajeConMasDislikes();
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/personaje/pikachu/status")
    public ResponseEntity<RespuestGenericaDto<PersonajeDto>> obtenerStatusPikachu() {
        try{
            RespuestGenericaDto<PersonajeDto> respuesta = personajesService.obtenerStatusPikachu();
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
