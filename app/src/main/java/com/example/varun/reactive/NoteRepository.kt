package com.example.varun.reactive

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NoteRepository(val application: Application) {

    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>

    init {
        val database = NoteDatabase.getInstance(application)
        noteDao = database.noteDao()
        allNotes = noteDao.getAll()
    }

    fun insert(note: Note) {
        GlobalScope.launch { noteDao.insert(note) }
    }

    fun update(note: Note) {
        GlobalScope.launch { noteDao.update(note) }
    }

    fun delete(note: Note) {
        GlobalScope.launch { noteDao.delete(note) }
    }

    fun deleteAll() {
        GlobalScope.launch { noteDao.deleteAll() }
    }

    fun getAll(): LiveData<List<Note>> = allNotes

}