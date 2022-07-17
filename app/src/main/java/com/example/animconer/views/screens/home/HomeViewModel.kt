package com.example.animconer.views.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animconer.data.repository.AnimeRepo
import com.example.animconer.utils.Resource
import com.example.animconer.views.screens.home.states.GenresState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepo: AnimeRepo
):ViewModel() {
    private var _genreState = mutableStateOf(GenresState())
    val genresState:State<GenresState> = _genreState


    init {
        getGenras()
    }

    private fun getGenras(){
        viewModelScope.launch {
            _genreState.value =genresState.value.copy(
                isLoading = true
            )
            animeRepo.getGenre().collectLatest { result->
                when(result){
                    is Resource.Success->{
                        _genreState.value = genresState.value.copy(
                            data = result.value,
                            isLoading = false
                        )
                    }
                    is Resource.Loading->{

                    }
                    is Resource.Error->{
                        _genreState.value = genresState.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }


}