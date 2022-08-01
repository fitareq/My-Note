package com.example.mynote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynote.database.NotesDatabase
import com.example.mynote.model.Notes
import com.example.mynote.repo.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun getAllNotes(): LiveData<List<Notes>> {
        return repository.getAllNotes()
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

    fun getAllNotesByPriority(p: String): LiveData<List<Notes>> = repository.getAllNotesByPriority(p)
}