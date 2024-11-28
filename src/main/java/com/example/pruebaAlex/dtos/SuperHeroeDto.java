package com.example.pruebaAlex.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperHeroeDto {

    private int id;
    private String name;
    private Image image;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String url;

    }
}

