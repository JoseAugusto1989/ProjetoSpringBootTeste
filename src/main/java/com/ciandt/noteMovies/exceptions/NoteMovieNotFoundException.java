package com.ciandt.noteMovies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoteMovieNotFoundException extends IllegalArgumentException {

    public NoteMovieNotFoundException(String message) {
        super(message);
    }
}