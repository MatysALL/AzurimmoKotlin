package bts.sio.azurimmo.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.azurimmo.viewmodels.BatimentViewModel

@Composable
fun BatimentList(viewModel: BatimentViewModel = viewModel(), navController: NavController) {
    // Observer les données de manière réactive
    val batiments by viewModel.batiments.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    when {
        isLoading -> CircularProgressIndicator()
        errorMessage != null -> Text(text = errorMessage!!, color = Color.Red)
        else -> LazyColumn {
            items(batiments) { batiment ->
                BatimentCard(batiment = batiment, navController = navController)
            }
        }
    }
}