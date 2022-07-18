package com.example.animconer.views.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animconer.data.repository.AnimeRepository
import com.example.animconer.model.Genre
import com.example.animconer.utils.Resource
import com.example.animconer.views.screens.home.states.GenresState
import com.example.animconer.views.screens.home.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {
    private var _animeSate = mutableStateOf(HomeState())
    val animeState: State<HomeState> = _animeSate

    private var _genresState = mutableStateOf(GenresState())
    val genresState: State<GenresState> = _genresState

    private val _selectedGenres = mutableStateOf("All")
    val selectedGenres:State<String> = _selectedGenres
    fun setGenres(value:String){
        _selectedGenres.value = value
    }

    private val _searchTerm = mutableStateOf("")
    val searchTerm:State<String> = _searchTerm
    fun setSearch(value:String){
        _searchTerm.value = value
    }

    init {
        getAnime(selectedGenres.value)
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            _animeSate.value = animeState.value.copy(
                isLoading = true
            )
            animeRepository.getGenre().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _genresState.value = genresState.value.copy(
                            data = listOf("All") + result.value.map { it.name },
                        )
                    }
                    is Resource.Error -> {

                    }
                    else -> {}
                }
            }
        }
    }

     fun getAnime(genres:String) {
        viewModelScope.launch {
/*            _animeSate.value = animeState.value.copy(
                isLoading = true
            )*/
            animeRepository.getAnime().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                      if(genres == "All"){
                          _animeSate.value = animeState.value.copy(
                              data = result.value ?: emptyList(),
                              isLoading = false
                          )
                      }
                        else{
                          _animeSate.value = animeState.value.copy(
                              data = result.value.filter { it.genres?.get(0)?.name == genres }
                                  ?: emptyList(),
                              isLoading = false
                          )
                      }
                    }
                    is Resource.Error -> {
                        _animeSate.value = animeState.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    fun getOneAnime(name:String){
        animeRepository.getOneAnime(name)
    }
}