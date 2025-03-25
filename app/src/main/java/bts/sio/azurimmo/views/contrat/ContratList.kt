package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.ContratViewModel
import bts.sio.azurimmo.model.Contrat

@Composable
fun ContratList(viewModel: ContratViewModel = viewModel()) {
    val contrats by viewModel.contrats.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(contrats) { contrat ->
            ContratCard(contrat = contrat)
        }
    }
}
