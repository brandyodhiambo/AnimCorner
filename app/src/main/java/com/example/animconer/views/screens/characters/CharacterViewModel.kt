package com.example.animconer.views.screens.characters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animconer.data.repository.CharactersRespository
import com.example.animconer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersRespository: CharactersRespository
    ) :ViewModel() {

        private val _characterState = mutableStateOf(CharacterState())
        val characterStata:State<CharacterState> = _characterState


    fun getCharacter(malId:Int){
        viewModelScope.launch {
            _characterState.value = characterStata.value.copy(
                isLoading = true
            )
            charactersRespository.getCharacter(malId).collectLatest { character->
                when(character){
                    is Resource.Success->{
                        Timber.d(character.value.toString())
                        _characterState.value = characterStata.value.copy(
                            characters = character.value.data,
                            isLoading = false
                        )
                    }
                    is Resource.Error->{
                        _characterState.value = characterStata.value.copy(
                            error = character.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}