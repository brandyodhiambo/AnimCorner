package com.example.animconer.views.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animconer.data.repository.AnimeRepository
import com.example.animconer.utils.Resource
import com.example.animconer.views.screens.home.states.GenresState
import com.example.animconer.views.screens.home.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {
    private var _animeSate = mutableStateOf(HomeState())
    val animeState: State<HomeState> = _animeSate

    private var _genresState = mutableStateOf(GenresState())
    val genresState: State<GenresState> = _genresState

    private val _selectedGenres = mutableStateOf("All")
    val selectedGenres: State<String> = _selectedGenres
    fun setGenres(value: String) {
        _selectedGenres.value = value
    }

    private val _searchTerm = mutableStateOf("")
    val searchTerm: State<String> = _searchTerm
    fun setSearch(value: String) {
        _searchTerm.value = value
    }

    init {
        getAnime(selectedGenres.value)
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            animeRepository.getGenre().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        val genres = result.data?.map {
                            it.name
                        } ?: emptyList()
                        _genresState.value = genresState.value.copy(
                            data = listOf("All") + genres,
                        )
                        // update loading on failure
                        _animeSate.value = animeState.value.copy(
                            isLoading = false
                        )

                    }
                    is Resource.Failure -> {
                        // update loading on failure
                        _animeSate.value = animeState.value.copy(
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        // update loading state on
                        _animeSate.value = animeState.value.copy(
                            isLoading = true,
                        )
                    }
                }
            }
        }
    }

    fun getAnime(genres: String = "All", searchString: String = "") {
        viewModelScope.launch {
            animeRepository.getAnime().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        if (genres == "All") {
                            _animeSate.value = animeState.value.copy(
                                data = if (searchString != "") {
                                    result.data?.filter { it.title == searchString.capitalize() }
                                        ?: emptyList()
                                } else {
                                    result.data ?: emptyList()
                                },
                                isLoading = false,
                            )
                        } else {
                            _animeSate.value = animeState.value.copy(
                                data = result.data?.filter { it.genres?.get(0)?.name == genres }
                                    ?: emptyList(),
                                isLoading = false,
                            )
                        }
                    }
                    is Resource.Failure -> {
                        _animeSate.value = animeState.value.copy(
                            error = result.message,
                            isLoading = false,
                        )
                    }
                    is Resource.Loading -> {
                        _animeSate.value = animeState.value.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getOneAnime(name: String) {
        animeRepository.getOneAnime(name)
    }
}
