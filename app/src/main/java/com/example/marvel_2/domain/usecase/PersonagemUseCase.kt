package com.example.marvel_2.domain.usecase

import android.app.Application
import br.com.zup.marvelheroes.ui.viewstate.ViewState
import com.example.marvel_2.ERROR_INSERT_CHARACTER
import com.example.marvel_2.ERROR_LOAD_LIST
import com.example.marvel_2.data.datasource.local.database.PersonagemDatabase
import com.example.marvel_2.domain.model.Personagem
import com.example.marvel_2.domain.repository.PersonagemRepository

class PersonagemUseCase(application: Application) {
    private val characterDao = PersonagemDatabase.getDatabase(application).characterDao()
    private val personagemRepository = PersonagemRepository(characterDao)

    suspend fun getAllCharacters(): ViewState<List<Personagem>> {
        try {
           val characters = personagemRepository.getAllCharacters()
            return ViewState.Success(characters)
        } catch (e: Exception) {
            return ViewState.Error(Exception(ERROR_LOAD_LIST))
        }
    }

    suspend fun insertCharacter(char: Personagem) : ViewState<Personagem> {
        return try {
            personagemRepository.insertCharacter(char)
            ViewState.Success(char)
        } catch (e: Exception) {
            ViewState.Error(Exception(ERROR_INSERT_CHARACTER))
        }
    }
}