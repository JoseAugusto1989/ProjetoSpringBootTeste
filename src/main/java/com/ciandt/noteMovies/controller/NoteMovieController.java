package com.ciandt.noteMovies.controller;

import com.ciandt.noteMovies.requests.NoteMoviesRequest;
import com.ciandt.noteMovies.models.NoteMovieModel;
import com.ciandt.noteMovies.responses.NoteMovieResponse;
import com.ciandt.noteMovies.services.NoteMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/note-movie")
public class NoteMovieController {

    @Autowired
    private NoteMovieService noteMovieService;

    @PostMapping
    private ResponseEntity<NoteMovieResponse> saveNoteMovie(@Valid @RequestBody NoteMoviesRequest noteMoviesRequest) {
        return ResponseEntity.ok(noteMovieService.save(noteMoviesRequest));
    }

    @GetMapping
    private ResponseEntity<Page<NoteMovieModel>> getAllNoteMovies(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(noteMovieService.findAll(pageable));
    }

    @GetMapping("/{id}")
    private ResponseEntity<NoteMovieResponse> getOneNoteMovie(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(noteMovieService.findById(id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteNoteMovie(@PathVariable(value = "id") UUID id) {
        return noteMovieService.delete(id);
    }

    @PutMapping
    private ResponseEntity<NoteMovieResponse> updateNoteMovie(@PathVariable(value = "id") UUID id,
                                                              @RequestBody @Valid NoteMoviesRequest noteMoviesRequest) {
        return ResponseEntity.ok(noteMovieService.updateNoteMovies(noteMoviesRequest, id));
    }
}
