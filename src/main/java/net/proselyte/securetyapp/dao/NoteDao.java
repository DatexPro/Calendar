package net.proselyte.securetyapp.dao;

import net.proselyte.securetyapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteDao extends JpaRepository<Note, Long> {
    @Query("select n from Note n where n.client.id =:id")
    List<Note> findByClientId(Long id);

}
