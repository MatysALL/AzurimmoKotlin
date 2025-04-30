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
import androidx.navigation.NavHostController
import bts.sio.azurimmo.viewmodels.AppartementViewModel
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.viewmodels.BatimentViewModel

@Composable
fun AppartementList(
    navController: NavHostController,
    batimentId: Int? = null,  // batimentId devient optionnel
    appartementViewModel: AppartementViewModel = viewModel(),
    batimentViewModel: BatimentViewModel = viewModel()
) {
    val appartements by appartementViewModel.appartements.collectAsState()
    val isLoading by appartementViewModel.isLoading.collectAsState()
    val errorMessage by appartementViewModel.errorMessage.collectAsState()
    val batiment by batimentViewModel.batiment.collectAsState()

    LaunchedEffect(batimentId) {
        if (batimentId == null) {
            appartementViewModel.getAppartements()  // Charge tous les appartements
        } else {
            appartementViewModel.getAppartementsByBatiment(batimentId) // Charge les appartements par bâtiment
            batimentViewModel.getBatiment(batimentId) // Charge les informations du bâtiment
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
                LazyColumn {
                    // Bloc pour afficher les informations sur le bâtiment
                    batiment?.let {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally // Centrer le contenu horizontalement
                            ) {
                                Text(
                                    text = "Informations sur le bâtiment",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Adresse : ${it.adresse}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Ville : ${it.ville}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    // Liste des appartements
                    if (appartements.isNotEmpty()) {
                        // Titre de la liste des appartements
                        item {
                            Text(
                                text = "Liste des appartements",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Afficher les appartements dans une liste
                        items(appartements) { appartement ->
                            AppartementCard(
                                appartement = appartement,
                                onClick = {
                                    // Navigue vers les détails de l'appartement
                                    navController.navigate("appartement_details/${appartement.id}")
                                }
                            )
                        }
                    } else {
                        // Message si aucun appartement n'est associé à ce bâtiment
                        item {
                            Text(
                                text = "Pas d'appartement pour ce bâtiment",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}