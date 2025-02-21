package com.insa.mygamelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.insa.mygamelist.data.IGDBStatic
import com.insa.mygamelist.ui.components.AppNavHost
import com.insa.mygamelist.ui.theme.MyGamesListTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        IGDBStatic.load(this)
        enableEdgeToEdge()
        setContent {

            MyGamesListTheme {
                AppNavHost()
            }
        }
    }
}