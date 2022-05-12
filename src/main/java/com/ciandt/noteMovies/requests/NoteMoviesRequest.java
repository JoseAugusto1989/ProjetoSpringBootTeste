package com.ciandt.noteMovies.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NoteMoviesRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String genre;

    @NotNull
    private double note;

    @NotNull
    private double noteIMDB;

    @NotBlank
    private String director;

    @NotBlank
    private String whereFound;

    @NotBlank
    private String productor;

}
