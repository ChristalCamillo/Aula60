package com.example.marvel_2.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel_2.domain.model.Personagem

@Dao
interface PersonagemDAO {
    @Query("SELECT * FROM characters ORDER BY nome ASC")
    fun getAllCharacters() : List<Personagem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newChar(char: Personagem)
}

