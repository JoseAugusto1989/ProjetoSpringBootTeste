package com.ciandt.noteMovies.repositories;

import com.ciandt.noteMovies.models.NoteMovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteMoviesRepository extends JpaRepository<NoteMovieModel, UUID> {


}
