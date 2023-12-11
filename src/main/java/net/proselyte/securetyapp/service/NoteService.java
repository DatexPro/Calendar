package net.proselyte.securetyapp.service;

import net.proselyte.securetyapp.model.Client;
import net.proselyte.securetyapp.model.Note;

import javax.xml.crypto.Data;
import java.util.List;

public interface NoteService {
    void save(Note note);

    List<Note> findByClient(Long id);

    List<Note> getAllNote();
}
