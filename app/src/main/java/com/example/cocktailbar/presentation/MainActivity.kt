package com.example.cocktailbar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cocktailbar.presentation.routing.NavState
import com.example.cocktailbar.presentation.ui.theme.CocktailBarTheme
import com.example.cocktailbar.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailBarTheme {
                // A surface container using the 'background' color from the theme
                MainActivityScreen()
            }
        }
    }
}

@Composable
fun MainActivityScreen() {
    val myViewModel: MainViewModel = koinViewModel()
    NavState(
        vm = myViewModel
    )
}

