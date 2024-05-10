package com.example.ejercicio2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio2.model.AvatarDetailDto
import com.example.ejercicio2.model.AvatarDto
import com.example.ejercicio2.network.AvatarApi
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

sealed interface AvatarUiState{
    data class Success(val characters: List<AvatarDto>): AvatarUiState
    object Error: AvatarUiState
    object Loading: AvatarUiState
}

class AvatarViewModel: ViewModel() {
    var avatarUiState: AvatarUiState by mutableStateOf(AvatarUiState.Loading)
        private set

    init {
        getAvatarCharacters()
    }

    private fun getAvatarCharacters() {
        viewModelScope.launch {
            avatarUiState = try{
                val response = AvatarApi.retrofitService.getCharacters().await()  // Await the result
                AvatarUiState.Success(response)
            }catch(e: IOException){
                AvatarUiState.Error
            }
        }
    }
    /*
    fun getCharacterDetail(id:String){
        viewModelScope.launch {
            avatarUiState = try{
                val response = AvatarApi.retrofitService.getCharacterDetail(id).await()  // Await the result
                AvatarUiState.DetailSuccess(response)
            }catch(e: IOException){
                AvatarUiState.Error
            }
        }
    }*/
    suspend fun getCharacterDetail(id:String):AvatarDetailDto?{
        return try{
            val response = AvatarApi.retrofitService.getCharacterDetail(id).await()
            response
        }catch (e: IOException){
            null
        }
    }

}