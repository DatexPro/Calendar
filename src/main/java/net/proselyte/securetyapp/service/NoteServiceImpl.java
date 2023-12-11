package net.proselyte.securetyapp.service;

import net.proselyte.securetyapp.dao.NoteDao;
import net.proselyte.securetyapp.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteDao noteDao;

    @Override
    public void save(Note note) {
        noteDao.save(note);
    }

    @Override
    public List<Note> findByClient(Long id) {
        return noteDao.findByClientId(id);
    }

    @Override
    public List<Note> getAllNote() {
        return noteDao.findAll();
    }
}
