package bts.sio.azurimmo.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Batiment

@RequiresApi(Build.VERSION_CODES.O)
class BatimentViewModel : ViewModel() {

    // Liste des bâtiments
    private val _batiments = MutableStateFlow<List<Batiment>>(emptyList())
    val batiments: StateFlow<List<Batiment>> = _batiments

    // État de chargement
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Message d'erreur
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        // On récupère les bâtiments dès que le ViewModel est créé
        getBatiments()
    }

    // Fonction pour récupérer les bâtiments via Retrofit
    @RequiresApi(Build.VERSION_CODES.O)
    fun getBatiments() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialiser les erreurs avant de faire un appel API

            try {
                // Appel Retrofit pour obtenir les bâtiments
                val response = RetrofitInstance.api.getBatiments()
                _batiments.value = response // Mettre à jour la liste des bâtiments
            } catch (e: Exception) {
                // Gérer les erreurs ici
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false // Fin du chargement
            }
        }
    }
}
