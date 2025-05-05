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
import android.util.Log // Importez la classe Log

// L'annotation RequiresApi est probablement inutile ici, comme mentionné précédemment
// @RequiresApi(Build.VERSION_CODES.O)
@RequiresApi(Build.VERSION_CODES.O)
class BatimentViewModel : ViewModel() {

    // --- StateFlows pour gérer les états ---
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess: StateFlow<Boolean> = _updateSuccess

    private val _addSuccess = MutableStateFlow(false) // Ajout d'un StateFlow pour le succès de l'ajout

    private val _batiments = MutableStateFlow<List<Batiment>>(emptyList())
    val batiments: StateFlow<List<Batiment>> = _batiments

    private val _batiment = MutableStateFlow<Batiment?>(null) // Pour charger un bâtiment spécifique
    val batiment: StateFlow<Batiment?> = _batiment

    init {
        // On récupère les bâtiments dès que le ViewModel est créé
        getBatiments()
    }

    // Fonction pour récupérer les bâtiments via Retrofit
    // L'annotation RequiresApi est probablement inutile ici
    // @RequiresApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    fun getBatiments() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialiser les erreurs avant de faire un appel API

            try {
                // Appel Retrofit pour obtenir les bâtiments
                // Retrofit gère la réponse HTTP et désérialise directement en List<Batiment>
                val response = RetrofitInstance.api.getBatiments()
                _batiments.value = response // Mettre à jour la liste des bâtiments
            } catch (e: Exception) {
                // Gérer les erreurs ici (principalement erreurs réseau ou désérialisation)
                _errorMessage.value = "Erreur lors du chargement des bâtiments : ${e.localizedMessage ?: "Une erreur s'est produite"}"
                Log.e("BatimentViewModel", "Erreur lors de la récupération des bâtiments", e) // Utilisation de Log
            } finally {
                _isLoading.value = false // Fin du chargement
                Log.d("BatimentViewModel", "Chargement des bâtiments terminé") // Utilisation de Log
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBatiment(batimentId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel
            _batiment.value = null // Optionnel : Réinitialiser le bâtiment précédent pendant le chargement

            try {
                // Appel Retrofit. Si la réponse est réussie (2xx), Retrofit désérialise
                // directement en Batiment. Si la réponse est une erreur HTTP (4xx, 5xx),
                // Retrofit lèvera généralement une HttpException.
                val response = RetrofitInstance.api.getBatiment(batimentId)
                _batiment.value = response // Mettre à jour le bâtiment unique

            } catch (e: retrofit2.HttpException) {
                // Gérer spécifiquement les erreurs HTTP (4xx, 5xx)
                _errorMessage.value = "Erreur HTTP ${e.code()} : ${e.message()}"
                Log.e("BatimentViewModel", "Erreur HTTP lors de la récupération du bâtiment ${batimentId}: ${e.code()}", e)
                // Vous pouvez éventuellement essayer de lire e.response()?.errorBody() ici
            } catch (e: Exception) {
                // Gérer les autres exceptions (réseau, désérialisation, etc.)
                _errorMessage.value = "Erreur lors du chargement du bâtiment ${batimentId} : ${e.localizedMessage ?: "Une erreur s'est produite"}"
                Log.e("BatimentViewModel", "Erreur lors de la récupération du bâtiment ${batimentId}", e)
            } finally {
                _isLoading.value = false
                Log.d("BatimentViewModel", "Chargement du bâtiment ${batimentId} terminé") // Utilisation de Log
            }
        }
    }

    fun addBatiment(batiment: Batiment, onComplete: (Batiment?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Envoi à l'API (ici, un POST)
                val response = RetrofitInstance.api.addBatiment(batiment)
                onComplete(response)
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
                onComplete(null)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteBatiment(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteBatiment(id)  // 🔴 Appel API
                _batiments.value = _batiments.value.filterNot { it.id == id }  // 🔄 Mise à jour UI
            } catch (e: Exception) {
                _errorMessage.value = "Erreur lors de la suppression du bâtiment: Ce batiment ne peut pas être supprimer car il possède des appartements liés. Erreur: ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}