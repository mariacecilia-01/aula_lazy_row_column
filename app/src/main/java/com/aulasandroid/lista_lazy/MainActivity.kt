package com.aulasandroid.lista_lazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.ArrayMap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulasandroid.lista_lazy.model.Game
import com.aulasandroid.lista_lazy.repository.getAllGames
import com.aulasandroid.lista_lazy.repository.getGamesByStudio
import com.aulasandroid.lista_lazy.ui.theme.Lista_lazyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista_lazyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GamesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GamesScreen(modifier: Modifier = Modifier) {

    var studio by remember {
        mutableStateOf("")
    }

    var gamesList by remember {
        mutableStateOf(getGamesByStudio(""))
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Meus jogos favoritos",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(211, 12, 123, 255)
        )

        OutlinedTextField(
            value = studio,
            onValueChange = { studio = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nome do estúdio") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        gamesList = getGamesByStudio(studio)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Icone de busca"
                    )
                }
            }
        )

//        LazyRow() {
//            items(gamesList){
//                StudioCard(it)
//            }
//        }
//
//        LazyColumn() {
//            items(gamesList) {
//                GameCard(it)
//            }
//        }

        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
            items(gamesList){
                StudioCard(it)

            }
        }
    }
}


@Composable
fun GameCard(game: Game) {
    Card(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(3f)
            ) {
                Text(
                    text = game.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = game.studio,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "${game.releaseYear}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}

@Composable
fun StudioCard(game: Game) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = game.studio,
                textAlign = TextAlign.Center
            )
        }
    }
}