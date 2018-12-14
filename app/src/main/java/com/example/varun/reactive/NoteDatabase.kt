package com.example.varun.reactive

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlin.reflect.KClass


@Database(entities = [Note::class],version = 1)
 abstract class NoteDatabase :RoomDatabase(){

}
