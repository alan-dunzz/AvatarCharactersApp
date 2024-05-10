package com.example.ejercicio2.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ejercicio2.ui.AvatarTopBar
import com.example.ejercicio2.model.AvatarDetailDto
import com.example.ejercicio2.ui.screens.Detail
import com.example.ejercicio2.ui.screens.ErrorScreen
import com.example.ejercicio2.ui.screens.HomeScreen
import com.example.ejercicio2.viewmodel.AvatarViewModel


@Composable
fun Navigation(contentPadding: PaddingValues = PaddingValues(0.dp)){
    val navController = rememberNavController()
    val avatarViewModel: AvatarViewModel = viewModel()
    val modifier:Modifier = Modifier
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(avatarViewModel,navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{id}",
            arguments= listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue ="5cf5679a915ecad153ab6976"
                }
            )
        ){ entry->
            DetailScreen(modifier,avatarViewModel,navController,id=entry.arguments?.getString("id"))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(avatarViewModel: AvatarViewModel,navController: NavController){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = { AvatarTopBar(navController,scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
        ) {
            val avatarViewModel: AvatarViewModel = viewModel()
            HomeScreen(navController,avatarUiState = avatarViewModel.avatarUiState, contentPadding = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(modifier: Modifier,avatarViewModel: AvatarViewModel,navController:NavController,id: String?) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var characterDetail by remember {
        mutableStateOf<AvatarDetailDto?>(null)
    }
    LaunchedEffect(id){
        characterDetail = avatarViewModel.getCharacterDetail(id?:"")
    }
    Scaffold(
        topBar = { AvatarTopBar(navController,scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
        ) {
            characterDetail?.let{detail->
                Detail(contentPadding = it,characterDetail = detail)
            }?: run{
                ErrorScreen(modifier =  modifier.fillMaxSize())
            }
            characterDetail?.let { it1 -> Detail(contentPadding = it, characterDetail = it1) }
        }
    }
}