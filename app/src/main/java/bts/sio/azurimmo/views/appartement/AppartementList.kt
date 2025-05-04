package bts.sio.azurimmo.views

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.AppartementViewModel

@Composable
fun AppartementList(viewModel: AppartementViewModel = viewModel()) {
    // Appel de la méthode pour récupérer les appartements
    LaunchedEffect(Unit) {
        viewModel.getAppartements()
    }

    val appartements by viewModel.appartements.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Titre en violet
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

        // Affichage du contenu en fonction de l'état
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Text(text = errorMessage ?: "Une erreur est survenue", color = Color.Red)
        } else {
            LazyColumn {
                items(appartements) { appartement ->
                    AppartementCard(appartement = appartement)
                }
            }
        }
    }
}
