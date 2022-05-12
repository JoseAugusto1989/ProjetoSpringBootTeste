package com.ciandt.noteMovies.services;

import com.ciandt.noteMovies.exceptions.NoteMovieNotFoundException;
import com.ciandt.noteMovies.models.NoteMovieModel;
import com.ciandt.noteMovies.repositories.NoteMoviesRepository;
import com.ciandt.noteMovies.requests.NoteMoviesRequest;
import com.ciandt.noteMovies.responses.NoteMovieResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteMovieService {

    @Autowired
    NoteMoviesRepository noteMoviesRepository;

    @Autowired
    private ModelMapper modelMapper;

    public NoteMovieResponse save(NoteMoviesRequest noteMoviesRequest) {
        NoteMovieModel noteMovieModel = new NoteMovieModel();
        NoteMovieResponse noteMovieResponse = new NoteMovieResponse();
        modelMapper.map(noteMoviesRequest, noteMovieModel);
        noteMovieModel.setRegistrationDate(LocalDateTime.now());
        noteMoviesRepository.save(noteMovieModel);
        modelMapper.map(noteMovieModel, noteMovieResponse);
        return noteMovieResponse;
    }

    @Transactional
    public ResponseEntity<String> delete(UUID id) {
        var noteMovieModel = getMovieById(id);
        noteMoviesRepository.deleteById(noteMovieModel.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Movie/Serie deleted succesfully!!!");
    }

    public Page<NoteMovieModel> findAll(Pageable pageable) {
        return noteMoviesRepository.findAll(pageable);
    }

    public NoteMovieResponse findById(UUID id) {
        var noteMovieModel = getMovieById(id);
        NoteMovieResponse noteMovieResponse = new NoteMovieResponse();
        modelMapper.map(noteMovieModel, noteMovieResponse);
        return noteMovieResponse;
    }

    public NoteMovieResponse updateNoteMovies(NoteMoviesRequest noteMoviesRequest, UUID id) {
        var noteMovieModel = getMovieById(id);
        NoteMovieResponse noteMovieResponse = new NoteMovieResponse();
        modelMapper.map(noteMoviesRequest, noteMovieModel);
        noteMoviesRepository.save(noteMovieModel);
        modelMapper.map(noteMovieModel, noteMovieResponse);
        return noteMovieResponse;
    }

    private NoteMovieModel getMovieById(UUID id) {
        Optional<NoteMovieModel> noteMovieModelOptional = noteMoviesRepository.findById(id);
        if (!noteMovieModelOptional.isPresent()) {
            throw new NoteMovieNotFoundException("Movie/Serie not found!!!");
        }
        return noteMovieModelOptional.get();
    }
}
