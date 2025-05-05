package bts.sio.azurimmo.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import bts.sio.azurimmo.viewmodels.BatimentViewModel
// Assurez-vous que BatimentCard est importé correctement
// import bts.sio.azurimmo.views.BatimentCard // Si BatimentCard est dans le même package

@RequiresApi(Build.VERSION_CODES.O) // Ajoutez l'annotation si nécessaire (pour Log par exemple)
@Composable
fun BatimentList(navController: NavController, viewModel: BatimentViewModel = viewModel()) {
    val batiments by viewModel.batiments.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Charger les bâtiments au démarrage du composable
    LaunchedEffect(Unit) {
        viewModel.getBatiments()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Naviguer vers l'écran d'ajout
                navController.navigate("add_batiment")
            }) {
                Icon(Icons.Filled.Add, "Ajouter un bâtiment")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Titre en violet
            Text(
                text = "Liste des Batiments",
                style = TextStyle(
                    color = Color(0xFF7B1FA2), // Couleur violette
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(16.dp)
            )

            // Affichage de l'interface utilisateur en fonction de l'état
            when {
                isLoading && batiments.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                errorMessage != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Erreur: $errorMessage", color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.getBatiments() }) {
                            Text("Réessayer")
                        }
                    }
                }
                batiments.isEmpty() && !isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Aucun bâtiment trouvé.", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp) // Ajoutez un padding horizontal si souhaité
                    ) {
                        items(batiments) { batiment ->
                            BatimentCard(
                                batiment = batiment,
                                // Le modificateur clickable est maintenant géré dans BatimentCard
                                onClick = {
                                    // Navigation vers la liste des appartements
                                    navController.navigate("appartements_list?batimentId=${batiment.id}")
                                },
                                onEditClick = {
                                    // Navigation vers l'écran de modification
                                    navController.navigate("edit_batiment/${batiment.id}")
                                },
                                onDeleteClick = {
                                    // Appel de la fonction de suppression du ViewModel
                                    viewModel.deleteBatiment(batiment.id)
                                }
                            )
                            // Pas besoin de Spacer ou Divider si le padding est géré dans la carte
                            // Divider()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBatimentList() {
    // Pour un preview réaliste, vous auriez besoin d'un ViewModel mocké
    // BatimentList(navController = rememberNavController())
}