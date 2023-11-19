package com.example.flowmvihilt.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowmvihilt.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor() : ViewModel() {

    @Inject
    lateinit var photoRepository: PhotoRepository

    fun handleIntent() {
        viewModelScope.launch {
            photoRepository.getPhotos(0, 20)
        }

    }
}