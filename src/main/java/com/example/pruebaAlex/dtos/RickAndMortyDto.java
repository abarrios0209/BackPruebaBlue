package com.example.pruebaAlex.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RickAndMortyDto {

    private int id;
    private String name;
    private String status;
    private String species;
    private String image;
}
