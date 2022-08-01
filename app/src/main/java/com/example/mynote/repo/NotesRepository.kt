package com.example.mynote.repo

import androidx.lifecycle.LiveData
import com.example.mynote.dao.NotesDao
import com.example.mynote.model.Notes

class NotesRepository(private val dao: NotesDao) {


    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

    fun getAllNotesByPriority(p: String): LiveData<List<Notes>> = dao.getNotesByPriority(p)
}