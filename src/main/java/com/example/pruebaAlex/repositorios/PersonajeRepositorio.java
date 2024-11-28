package com.example.pruebaAlex.repositorios;

import com.example.pruebaAlex.entidades.PersonajeEntidad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonajeRepositorio extends MongoRepository<PersonajeEntidad, Integer> {

    Optional<PersonajeEntidad> findFirstByOrderByLikesDesc();

    Optional<PersonajeEntidad> findFirstByOrderByDislikesDesc();
}
