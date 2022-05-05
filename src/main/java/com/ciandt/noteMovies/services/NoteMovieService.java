package com.ciandt.noteMovies.services;

import com.ciandt.noteMovies.models.NoteMovieModel;
import com.ciandt.noteMovies.repositories.NoteMoviesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteMovieService {

    final NoteMoviesRepository noteMoviesRepository;

    public NoteMovieService(NoteMoviesRepository noteMoviesRepository) {
        this.noteMoviesRepository = noteMoviesRepository;
    }

    public NoteMovieModel save(NoteMovieModel noteMovieModel) {
        return noteMoviesRepository.save(noteMovieModel);
    }

    @Transactional
    public void delete(NoteMovieModel noteMovieModel) {
        noteMoviesRepository.delete(noteMovieModel);

    }

    public Page<NoteMovieModel> findAll(Pageable pageable) {
        return noteMoviesRepository.findAll(pageable);
    }

    public Optional<NoteMovieModel> findById(UUID id) {
        return noteMoviesRepository.findById(id);
    }

}
