package com.example.tweets

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweets.api.TweetsyAPI
import com.example.tweets.screens.CategoryScreen
import com.example.tweets.screens.DetailScreen
import com.example.tweets.ui.theme.TweetsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(text = "Tweetsy")
                    } )
                }
            ) {
                Box(modifier = Modifier.padding(it))
                App()
            }


        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = "category"){
        composable("category"){
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }
        composable("detail/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            )
        ){
            DetailScreen()
        }

    }

}

//@Composable
//fun App() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "registration"){
//        composable(route = "registration"){
//            RegistrationScreen{
//                navController.navigate("main/${it}")
//            }
//        }
//        composable(route = "login"){
//            LoginScreen()
//        }
//        composable(route = "main/{email}", arguments = listOf(
//            navArgument("email"){
//                type = NavType.StringType
//            }
//
//        )){
//            val email = it.arguments!!.getString("email")
//            MainScreen(email!!)
//        }
//    }
//
//}
//
//@Composable
//fun RegistrationScreen(onClick : (email: String)-> Unit) {
//    Text(text = "Registration",
//        style = MaterialTheme.typography.displayLarge,
//        modifier = Modifier.clickable {
//            onClick("cheezycode@gmail.com")
//        }
//    )
//
//}
//@Composable
//fun LoginScreen() {
//    Text(text = "Login", style = MaterialTheme.typography.displayLarge)
//
//}
//@Composable
//fun MainScreen(email: String) {
//    Text(text = "Main Screen- $email", style = MaterialTheme.typography.displayLarge)
//
//}
