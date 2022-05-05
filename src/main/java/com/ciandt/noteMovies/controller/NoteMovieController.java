package com.ciandt.noteMovies.controller;

import com.ciandt.noteMovies.dtos.NoteMoviesDto;
import com.ciandt.noteMovies.models.NoteMovieModel;
import com.ciandt.noteMovies.services.NoteMovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/note-movie")
public class NoteMovieController {

    final NoteMovieService noteMovieService;


    public NoteMovieController(NoteMovieService noteMovieService) {
        this.noteMovieService = noteMovieService;
    }

    @PostMapping
    private ResponseEntity<Object> saveNoteMovie(@Valid NoteMoviesDto noteMoviesDto) {

        var noteMovieModel = new NoteMovieModel();
        BeanUtils.copyProperties(noteMoviesDto, noteMovieModel);
        noteMovieModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMovieService.save(noteMovieModel));

    }

    @GetMapping
    private ResponseEntity<Page<NoteMovieModel>> getAllNoteMovies(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(noteMovieService.findAll(pageable));

    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> getOneNoteMovie(@PathVariable(value = "id") UUID id) {
        Optional<NoteMovieModel> noteMovieModelOptional = noteMovieService.findById(id);
        if (!noteMovieModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Movie/Serie not found!!!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(noteMovieModelOptional.get());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteNoteMovie(@PathVariable(value = "id") UUID id) {
        Optional<NoteMovieModel> noteMovieModelOptional = noteMovieService.findById(id);
        if (!noteMovieModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie/Serie not found!!!");
        }
        noteMovieService.delete(noteMovieModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movie/Serie deleted succesfully!!!");
    }

    @PutMapping
    private ResponseEntity<Object> updateNoteMovie(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid NoteMoviesDto noteMoviesDto) {

        Optional<NoteMovieModel> noteMovieModelOptional = noteMovieService.findById(id);
        if (!noteMovieModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie/Serie not found!!!");
        }

        var noteMovieModel = new NoteMovieModel();
            BeanUtils.copyProperties(noteMoviesDto, noteMovieModel);
            noteMovieModel.setId(noteMovieModelOptional.get().getId());
            noteMovieModel.setRegistrationDate(noteMovieModelOptional.get().getRegistrationDate());
            return ResponseEntity.status(HttpStatus.OK).body(noteMovieService.save(noteMovieModel));
    }

}
