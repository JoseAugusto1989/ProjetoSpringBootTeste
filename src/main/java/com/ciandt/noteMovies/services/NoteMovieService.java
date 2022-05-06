package com.ciandt.noteMovies.services;

import com.ciandt.noteMovies.models.NoteMovieModel;
import com.ciandt.noteMovies.repositories.NoteMoviesRepository;
import com.ciandt.noteMovies.requests.NoteMoviesRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteMovieService {

    final NoteMoviesRepository noteMoviesRepository;

    public NoteMovieService(NoteMoviesRepository noteMoviesRepository) {
        this.noteMoviesRepository = noteMoviesRepository;
    }

    public NoteMovieModel save(NoteMovieModel noteMovieModel) {
        var noteMoviesRequest = new NoteMoviesRequest();
        BeanUtils.copyProperties(noteMoviesRequest, noteMovieModel);
        noteMovieModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
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
