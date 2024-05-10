package com.example.ejercicio2.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ejercicio2.R
import com.example.ejercicio2.model.AvatarDto
import com.example.ejercicio2.navigation.Screen
import com.example.ejercicio2.viewmodel.AvatarUiState

@Composable
fun HomeScreen(
    navController: NavController,
    avatarUiState: AvatarUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    when( avatarUiState){
        is AvatarUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AvatarUiState.Success -> CharacterGridScreen(navController,characters = avatarUiState.characters, modifier =
        modifier.fillMaxSize())
        is AvatarUiState.Error -> ErrorScreen(modifier =  modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.loader),
            contentDescription = "Loading")
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.error_load)
            , contentDescription = "Error Loading" )
        Text(text = stringResource(R.string.problem_with_connection))
    }
}

@Composable
fun AvatarCharacterCard(navController: NavController,context: Context, character:AvatarDto, modifier: Modifier){
    Card(
        modifier= modifier
            .clickable {
                navController.navigate(Screen.DetailScreen.withArgs(character.id))
            }
            .fillMaxWidth()
            .padding(5.dp),
        elevation=CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color(74,161,107))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(end=10.dp)
        ) {
            Box(
                modifier= Modifier
                    .size(150.dp)
                    .padding(10.dp)
                    .aspectRatio(1f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(
                        context = LocalContext.current
                    ).data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.avatar_image),
                    modifier= modifier
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .border(4.dp, color = Color(139, 101, 101), shape = CircleShape),
                    error = painterResource(id = R.drawable.no_image),
                    placeholder = painterResource(id = R.drawable.carga),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = character.name?:"",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(10.dp))
                val text=stringResource(R.string.no_affiliation)
                Text(
                    text = character.affiliation?:text,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun CharacterGridScreen(
    navController: NavController,
    characters:List<AvatarDto>,
    modifier:Modifier=Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    val context= LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier= modifier
            .padding(horizontal = 5.dp)
            .background(Color(159, 234, 249)),
        contentPadding=contentPadding,
    ){
        items(
            items=characters,
            key = {
                character ->character.id
            }
        ){
            character ->
            AvatarCharacterCard(
                navController,
                context,
                character = character,
                modifier = modifier
                    .padding(4.dp)
                    .aspectRatio(2f)
            )
        }
    }
}

