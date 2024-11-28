package com.example.pruebaAlex.serviciosImpl;

import com.example.pruebaAlex.dtos.*;
import com.example.pruebaAlex.entidades.PersonajeEntidad;
import com.example.pruebaAlex.repositorios.PersonajeRepositorio;
import com.example.pruebaAlex.servicios.PersonajesServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Service
public class PersonajeServicioImpl implements PersonajesServicio {

    private final PersonajeRepositorio personajeRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Value("${rickAndMortyApiUrl}")
    private String rickAndMortyApiUrl;

    @Value("${pokeApiUrl}")
    private String pokeApiUrl;

    @Value("${superheroApiUrl}")
    private String superheroApiUrl;

    public PersonajeServicioImpl(PersonajeRepositorio personajeRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.personajeRepository = personajeRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public RespuestGenericaDto<PersonajeDto> personajeAleatorio() {
        int numeroAleatorio = generarNumeroAleatorio(3);
        switch (numeroAleatorio) {
            case 1:
                return consumirRickAndMortyApi();
            case 2:
                return consumirPokemonApi();
            case 3:
                return consumirSuperheroApi();
            default:
                throw new IllegalArgumentException("Número aleatorio fuera de rango: " + numeroAleatorio);
        }
    }

    private int generarNumeroAleatorio(int max) {
        return (int) (Math.random() * max) + 1;
    }

    private RespuestGenericaDto<PersonajeDto> consumirRickAndMortyApi() {
        int personajeId = generarNumeroAleatorio(826);
        String url = rickAndMortyApiUrl + personajeId;
        RickAndMortyDto rickAndMortyDto = restTemplate.getForObject(url, RickAndMortyDto.class);

        PersonajeDto personajeDto = new PersonajeDto();
        personajeDto.setId(rickAndMortyDto.getId());
        personajeDto.setName(rickAndMortyDto.getName());
        personajeDto.setImage(rickAndMortyDto.getImage());

        return crearRespuestaExitosa(personajeDto);
    }

    private RespuestGenericaDto<PersonajeDto> consumirPokemonApi() {
        int pokemonId = generarNumeroAleatorio(500);
        String url = pokeApiUrl + pokemonId;
        PokemonDto pokemonDto = restTemplate.getForObject(url, PokemonDto.class);

        PersonajeDto personajeDto = new PersonajeDto();
        personajeDto.setId(pokemonDto.getId());
        personajeDto.setName(pokemonDto.getName());
        personajeDto.setImage(pokemonDto.getSprites().getOther().getHome().getFrontDefault());

        return crearRespuestaExitosa(personajeDto);
    }

    private RespuestGenericaDto<PersonajeDto> consumirSuperheroApi() {
        int superHeroId = generarNumeroAleatorio(732);
        Optional<PersonajeEntidad> personajeEntidad = personajeRepository.findById(superHeroId);

        if (personajeEntidad.isPresent()) {
            PersonajeDto personajeDto = objectMapper.convertValue(personajeEntidad.get(), PersonajeDto.class);
            return crearRespuestaExitosa(personajeDto);
        }

        String url = superheroApiUrl + superHeroId;
        SuperHeroeDto superHeroeDto = restTemplate.getForObject(url, SuperHeroeDto.class);

        PersonajeDto personajeDto = new PersonajeDto();
        personajeDto.setId(superHeroeDto.getId());
        personajeDto.setName(superHeroeDto.getName());
        personajeDto.setImage(superHeroeDto.getImage().getUrl());

        return crearRespuestaExitosa(personajeDto);
    }

    private <T> RespuestGenericaDto<T> crearRespuestaExitosa(T data) {
        RespuestGenericaDto<T> respuesta = new RespuestGenericaDto<>();
        respuesta.successful(data);
        return respuesta;
    }

    public RespuestGenericaDto<String> darLike(PersonajeDto personajeDto) {
        return manejarReaccion(personajeDto, true);
    }

    public RespuestGenericaDto<String> darDislike(PersonajeDto personajeDto) {
        return manejarReaccion(personajeDto, false);
    }

    private RespuestGenericaDto<String> manejarReaccion(PersonajeDto personajeDto, boolean esLike) {
        RespuestGenericaDto<String> result = new RespuestGenericaDto<>();
        Optional<PersonajeEntidad> personajeEntidad = personajeRepository.findById(personajeDto.getId());

        if (personajeEntidad.isPresent()) {
            PersonajeEntidad personaje = personajeEntidad.get();
            if (esLike) {
                personaje.setLikes(personaje.getLikes() + 1);
            } else {
                personaje.setDislikes(personaje.getDislikes() + 1);
            }
            personaje.setFechaEvaluacion(new Date());
            personajeRepository.save(personaje);
            result.successful(esLike ? "Like agregado correctamente" : "Dislike agregado correctamente");
        } else {
            PersonajeEntidad nuevoPersonaje = new PersonajeEntidad();
            nuevoPersonaje.setId(personajeDto.getId());
            nuevoPersonaje.setName(personajeDto.getName());
            nuevoPersonaje.setLikes(esLike ? 1 : 0);
            nuevoPersonaje.setDislikes(esLike ? 0 : 1);
            nuevoPersonaje.setImage( personajeDto.getImage() );
            nuevoPersonaje.setFechaEvaluacion(new Date());
            personajeRepository.save(nuevoPersonaje);
            result.successful("Nuevo personaje creado y " + (esLike ? "like" : "dislike") + " agregado");
        }

        return result;
    }

    public RespuestGenericaDto<PersonajeDto> obtenerPersonajeConMasLikes() {
        Optional<PersonajeEntidad> personajeEntidad = personajeRepository.findFirstByOrderByLikesDesc();
        if (personajeEntidad.isPresent()) {
            PersonajeDto personajeDto = objectMapper.convertValue(personajeEntidad.get(), PersonajeDto.class);
            return crearRespuestaExitosa(personajeDto);
        }
        throw new RuntimeException("No se encontró un personaje con más likes");
    }

    public RespuestGenericaDto<PersonajeDto> obtenerPersonajeConMasDislikes() {
        Optional<PersonajeEntidad> personajeEntidad = personajeRepository.findFirstByOrderByDislikesDesc();
        if (personajeEntidad.isPresent()) {
            PersonajeDto personajeDto = objectMapper.convertValue(personajeEntidad.get(), PersonajeDto.class);
            return crearRespuestaExitosa(personajeDto);
        }
        throw new RuntimeException("No se encontró un personaje con más dislikes");
    }

    public RespuestGenericaDto<PersonajeDto> obtenerStatusPikachu( ){
        Optional<PersonajeEntidad> personajeEntidad = personajeRepository.findById(25);

        if(personajeEntidad.isPresent()){
            PersonajeDto personajeDto = objectMapper.convertValue( personajeEntidad, PersonajeDto.class );
            return crearRespuestaExitosa( personajeDto );
        }
        String url = pokeApiUrl + 25;
        PokemonDto pokemonDto = restTemplate.getForObject(url, PokemonDto.class);

        PersonajeEntidad nuevoPersonaje = new PersonajeEntidad();
        nuevoPersonaje.setId( pokemonDto.getId() );
        nuevoPersonaje.setName( pokemonDto.getName() );
        nuevoPersonaje.setImage( pokemonDto.getSprites().getOther().getHome().getFrontDefault() );
        nuevoPersonaje.setLikes( 0 );
        nuevoPersonaje.setDislikes( 0 );
        nuevoPersonaje.setFechaEvaluacion( new Date() );
        PersonajeDto nuevoPersonajeDto = objectMapper.convertValue( nuevoPersonaje, PersonajeDto.class );
        personajeRepository.save(nuevoPersonaje);

        return crearRespuestaExitosa(nuevoPersonajeDto);
    }
}
