package com.example.pruebaAlex.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "personajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeEntidad {

        @Id
        private int id;
        private String name;
        private String image;
        private int likes = 0;
        private int dislikes = 0;
        private Date fechaEvaluacion = new Date();
}
