package at.cgradl.skiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.cgradl.skiapp.model.PersonRanking


import at.cgradl.skiapp.ui.theme.SkiAppTheme
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonResults: String = applicationContext.assets
            .open("ranking.json")
            .bufferedReader()
            .use { it.readText() }

        val listType: Type = object : TypeToken<List<PersonRanking>>() {}.type
        val rankings: List<PersonRanking> = Gson().fromJson(jsonResults, listType);

        setContent {
            SkiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   RankingListScreen(rankings)
                }
            }
        }
    }
}

@Composable
fun RankingItem(ranking: PersonRanking) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row() {
            Image(
                // on below line we are adding the image url
                // from which we will  be loading our image.
                painter = rememberAsyncImagePainter(ranking.PersonImage),
                contentScale = ContentScale.Inside,
                // on below line we are adding content
                // description for our image.
                contentDescription = "gfg image",
                // on below line we are adding modifier for our
                // image as wrap content for height and width.
                modifier = Modifier.size(128.dp).clip(CircleShape)
            )
            Column() {
                Text(
                    text = "${ranking.Rank}",
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${ranking.FirstName} ${ranking.LastName}",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
}

@Composable
fun RankingListScreen(rankings: List<PersonRanking>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(
            items = rankings,
            key = { ranking -> ranking.PersonId}
        ) {ranking ->
            RankingItem(ranking = ranking)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SkiAppTheme {
        var mockItem = PersonRanking(1, 1, "Christian", "Gradl", "empty", 1, "AT", "empty", 1)
        var mockItem1 = PersonRanking(1, 2, "Christian", "Gradl", "empty", 1, "AT", "empty", 1)
        var items = listOf(mockItem, mockItem1)
        RankingListScreen(items)
    }
}