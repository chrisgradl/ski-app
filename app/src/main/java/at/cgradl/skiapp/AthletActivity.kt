package at.cgradl.skiapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.cgradl.skiapp.model.PersonRanking
import at.cgradl.skiapp.ui.theme.SkiAppTheme

class AthletActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val personRanking = this.intent.getSerializableExtra("PersonRanking") as PersonRanking

        setContent {
            SkiAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("${personRanking.FirstName} ${personRanking.LastName}") }
                        )
                    }
                ) {
                    Column(modifier = Modifier.padding(it)) {
                        Greeting2("${personRanking.FirstName} ${personRanking.LastName}")
                        Greeting2("${personRanking.FirstName} ${personRanking.LastName}")
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SkiAppTheme {
        Greeting2("Android")
    }
}