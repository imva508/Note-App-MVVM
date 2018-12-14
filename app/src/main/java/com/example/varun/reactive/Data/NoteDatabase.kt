package com.example.varun.reactive.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.varun.reactive.Model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback).build()
            return instance!!
        }

        private val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                addToDatabase()
            }
        }

        private fun addToDatabase() {
            GlobalScope.launch {
                val noteDao = instance!!.noteDao()
                (1..3).forEach {
                    noteDao.insert(
                        Note(
                            title = "Note title $it",
                            description = "note description $it",
                            priority = it
                        )
                    )
                }
            }
        }

    }

}
