package bts.sio.azurimmo.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.AppartementViewModel
import bts.sio.azurimmo.viewmodels.BatimentViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppartementList(
    batimentId: Int? = null,  // batimentId devient optionnel
    appartementViewModel: AppartementViewModel = viewModel(),
    batimentViewModel: BatimentViewModel = viewModel()  ) {

    val appartements by appartementViewModel.appartements.collectAsState()
    val isLoading by appartementViewModel.isLoading.collectAsState()
    val errorMessage by appartementViewModel.errorMessage.collectAsState()
    val batiment by batimentViewModel.batiment.collectAsState()

    // Appel de la méthode pour récupérer les appartements en fonction de batimentId
    LaunchedEffect(batimentId) {
        if (batimentId == null) {
            appartementViewModel.getAppartements()  // Charge tous les appartements
        } else {
            appartementViewModel.getAppartementsByBatiment(batimentId)
            batimentViewModel.getBatiment(batimentId)// Charge par bâtiment
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Erreur inconnue",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {
                // Utilisez un seul LazyColumn pour toute la liste
                LazyColumn {

                    // BLOC D'INFOS SUR LE BATIMENT SI SELECTIONNE
                    if (batiment != null) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Informations sur le bâtiment",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Adresse : ${batiment?.adresse ?: "Non défini"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Ville : ${batiment?.ville ?: "Non défini"}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                // Ajoutez d'autres infos du bâtiment ici
                            }
                        }
                    }

                    // TITRE DE LA LISTE D'APPARTEMENTS
                    item {
                        Text(
                            text = "Liste des Appartements",
                            style = TextStyle(
                                color = Color(0xFF7B1FA2), // Couleur violette
                                fontSize = 24.sp,           // Taille de la police
                                fontWeight = FontWeight.Bold // Gras
                            ),
                            modifier = Modifier
                                .padding(16.dp) // Espacement autour du titre
                        )
                    }


                    // LISTE DES APPARTEMENTS
                    if (appartements.isNotEmpty()) {
                        items(appartements) { appartement ->
                            AppartementCard(appartement = appartement)
                        }
                    } else {
                        // Message si aucun appartement n'est trouvé (pour tous ou pour le bâtiment)
                        item {
                            Text(
                                text = if (batimentId != null) "Pas d'appartement pour ce bâtiment" else "Aucun appartement trouvé",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp), // Plus d'espace vertical
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant // Couleur moins forte que le titre
                            )
                        }
                    }
                }
            }
        }
    }
}
