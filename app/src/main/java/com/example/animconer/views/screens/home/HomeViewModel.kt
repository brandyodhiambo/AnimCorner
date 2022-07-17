package com.example.animconer.views.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animconer.data.repository.AnimeRepo
import com.example.animconer.utils.Resource
import com.example.animconer.views.screens.home.states.GenresState
import com.example.animconer.views.screens.home.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepo: AnimeRepo
):ViewModel() {
    private var _animeSate = mutableStateOf(HomeState())
    val animeState:State<HomeState> = _animeSate

    private var _genresState = mutableStateOf(GenresState())
    val genresState:State<GenresState> = _genresState


    init {
        getAnimes()
        getGenres()
    }

    private fun getGenres(){
        viewModelScope.launch {
            _animeSate.value =animeState.value.copy(
                isLoading = true
            )
            animeRepo.getGenre().collectLatest { result->
                when(result){
                    is Resource.Success->{
                        _genresState.value = genresState.value.copy(
                            data = result.value,
                            isLoading = false
                        )
                    }
                    is Resource.Loading->{

                    }
                    is Resource.Error->{
                        _genresState.value = genresState.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getAnimes(){
        viewModelScope.launch {
            _animeSate.value =animeState.value.copy(
                isLoading = true
            )
            animeRepo.getAnime().collectLatest { result->
                when(result){
                    is Resource.Success->{
                        _animeSate.value = animeState.value.copy(
                            data = result.value,
                            isLoading = false
                        )
                    }
                    is Resource.Loading->{

                    }
                    is Resource.Error->{
                        _animeSate.value = animeState.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }


}