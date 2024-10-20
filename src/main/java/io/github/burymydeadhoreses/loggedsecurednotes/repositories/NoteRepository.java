package io.github.burymydeadhoreses.loggedsecurednotes.repositories;

import io.github.burymydeadhoreses.loggedsecurednotes.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
}
