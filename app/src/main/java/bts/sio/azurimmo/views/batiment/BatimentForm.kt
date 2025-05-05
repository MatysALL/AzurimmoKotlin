package bts.sio.azurimmo.views.batiment

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewmodels.BatimentViewModel

@Composable
fun BatimentForm(navController: NavHostController) {
    var adresse by remember { mutableStateOf("") }
    var ville by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val viewModel: BatimentViewModel = viewModel()

    // Fonction d'ajout
    @RequiresApi(Build.VERSION_CODES.O)
    fun addBatiment() {
        if (adresse.isBlank() || ville.isBlank()) {
            errorMessage = "Adresse et Ville sont obligatoires"
            return
        }

        isLoading = true
        val batiment = Batiment(id = 0, adresse = adresse, ville = ville)

        // Appel à la fonction d'ajout du ViewModel
        viewModel.addBatiment(batiment) {
            isLoading = false
            if (it != null) {
                // Si l'ajout est réussi, revenir à la liste des bâtiments
                navController.navigate("batiments_list")
            } else {
                errorMessage = "Erreur lors de l'ajout du bâtiment"
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = "Ajouter un Bâtiment")

        Spacer(modifier = Modifier.height(16.dp))

        // Formulaire de saisie
        TextField(
            value = adresse,
            onValueChange = { adresse = it },
            label = { Text("Adresse") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = ville,
            onValueChange = { ville = it },
            label = { Text("Ville") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Message d'erreur
        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { try {
                addBatiment()
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Ajouter le Bâtiment")
            }
        }
    }
}