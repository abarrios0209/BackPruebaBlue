package com.example.pruebaAlex.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDto {

    private int id;
    private String name;

    @JsonProperty("sprites")
    private Sprites sprites;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sprites {

        @JsonProperty("other")
        private OtherSprites other;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class OtherSprites {

            @JsonProperty("home")
            private HomeSprites home;

            @Getter
            @Setter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class HomeSprites {

                @JsonProperty("front_default")
                private String frontDefault;
            }
        }
    }
}