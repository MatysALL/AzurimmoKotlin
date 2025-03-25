package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.AppartementViewModel
import bts.sio.azurimmo.model.Appartement

@Composable
fun AppartementList(
    batimentId: Int? = null,  // batimentId devient optionnel
    viewModel: AppartementViewModel = viewModel()) {

    val appartements by viewModel.appartements.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(batimentId) {
        if (batimentId == null) {
            viewModel.getAppartements()  // Charge tous les appartements
        } else {
            viewModel.getAppartementsByBatiment(batimentId)  // Charge par bâtiment
        }
    }

    when {
        isLoading -> CircularProgressIndicator()
        errorMessage != null -> Text(text = errorMessage!!, color = Color.Red)
        else -> LazyColumn {
            items(appartements) { appartement ->
                AppartementCard(appartement = appartement)
            }
        }
    }
}
