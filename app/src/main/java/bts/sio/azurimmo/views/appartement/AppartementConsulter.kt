package bts.sio.azurimmo.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.AppartementViewModel
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.viewmodels.BatimentViewModel

@Composable
fun AppartementConsulter(
    appartementId: Int,
    appartementViewModel: AppartementViewModel = viewModel(),
    onModifierClick: (Int) -> Unit // Callback pour naviguer vers la modifier
) {
    val appartement by appartementViewModel.appartement.collectAsState()
    val isLoading by appartementViewModel.isLoading.collectAsState()
    val errorMessage by appartementViewModel.errorMessage.collectAsState()

    // Lancer la récupération des données de l'appartement au chargement de la vue
    LaunchedEffect(appartementId) {
        appartementViewModel.getAppartement(appartementId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            errorMessage != null -> Text(
                text = errorMessage ?: "Erreur inconnue",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
            appartement != null -> Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "${appartement!!.numero ?: "Non disponible"}",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${appartement!!.description ?: "Non disponible"}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
                Text(
                    text = "Détails de l'appartement :",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Surface : ${appartement!!.surface ?: "Non spécifiée"} m²",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Nombre de pièces : ${appartement!!.nbPiece ?: "Non spécifié"}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Détails du Batiment :",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Ville de l'appartement : ${appartement!!.batiment?.ville ?: "Aucune"}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Adresse de l'appartement : ${appartement!!.batiment?.adresse ?: "Aucune"}",
                    style = MaterialTheme.typography.bodyLarge
                )

                // Ajoutez un bouton pour modifier l'appartement
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onModifierClick(appartement!!.id) }, // Utilisez l'ID de l'appartement pour la navigation
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Modifier l'appartement")
                }
            }
        }
    }
}

