package io.github.burymydeadhoreses.loggedsecurednotes.services;


import io.github.burymydeadhoreses.loggedsecurednotes.entities.Note;
import io.github.burymydeadhoreses.loggedsecurednotes.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> list() {
        return noteRepository.findAll();
    }

    public Optional<Note> get(UUID id) {
        return noteRepository.findById(id);
    }

    public Optional<Note> update(Note note) {
        var optionalNote = noteRepository.findById(note.getId());

        optionalNote.ifPresent(n -> {
            n.setTitle(note.getTitle());
            n.setContent(note.getContent());
            noteRepository.save(n);
        });

        return optionalNote;
    }

    public Optional<Note> delete(UUID id) {
        var optionalNote = noteRepository.findById(id);

        optionalNote.ifPresent(noteRepository::delete);

        return optionalNote;
    }
}