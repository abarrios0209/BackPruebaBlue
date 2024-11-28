package com.example.pruebaAlex.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeDto {

    private int id;
    private String name;
    private String image;
    private int likes = 0;
    private int dislikes =0;
}
