package com.example.marvel_2.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvel_2.domain.model.Personagem
import com.example.marvel_2.data.datasource.local.Converters
import com.example.marvel_2.data.datasource.local.dao.PersonagemDAO

@Database(entities = [Personagem::class], version = 1)
@TypeConverters(Converters::class)
abstract class PersonagemDatabase: RoomDatabase() {
    abstract fun characterDao() : PersonagemDAO

    companion object {
        @Volatile
        private var INSTANCE: PersonagemDatabase? = null
        fun getDatabase(context: Context): PersonagemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDatabase::class.java,
                    "character_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

