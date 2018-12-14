package com.example.varun.reactive

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.varun.reactive.Data.NoteRepository
import com.example.varun.reactive.Model.Note

class NoteViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val repository by lazy { NoteRepository(appContext) }
    val allNotes by lazy { repository.getAll() }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}