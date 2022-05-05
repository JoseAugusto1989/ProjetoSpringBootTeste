package com.ciandt.noteMovies.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoteMoviesDto {

    @NotBlank
    private String name;

    @NotBlank
    private String genre;

    @NotBlank
    private double note;

    @NotBlank
    private double noteIMDB;

    @NotBlank
    private String director;

    @NotBlank
    private String whereFound;

    @NotBlank
    private String productor;

}
