package com.example.marvel_2.domain.repository

import com.example.marvel_2.data.datasource.local.dao.PersonagemDAO
import com.example.marvel_2.domain.model.Personagem

class PersonagemRepository(private val personagemDAO: PersonagemDAO) {

    suspend fun getAllCharacters() : List<Personagem> = personagemDAO.getAllCharacters()

    suspend fun insertCharacter(personagem: Personagem) {
        personagemDAO.newChar(personagem)
    }
}