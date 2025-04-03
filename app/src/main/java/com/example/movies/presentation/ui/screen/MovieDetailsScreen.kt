package com.example.movies.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movies.api.ApiResult
import com.example.movies.domain.model.MovieDetailsItem
import com.example.movies.presentation.viewmodels.MovieDetailsVM

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsVM = hiltViewModel()) {

    val movieDetails = viewModel.movieDetails.collectAsState(ApiResult.Loading)

    Box(modifier = Modifier
        .safeDrawingPadding()
        .fillMaxSize()
    ) {
        when(movieDetails.value){
            is ApiResult.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is ApiResult.Success -> {
                val item = (movieDetails.value as ApiResult.Success<MovieDetailsItem>).data
                MovieDetailsItem(item)
            }
            is ApiResult.Error -> {
                Toast.makeText(LocalContext.current, (movieDetails.value as ApiResult.Error).message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}

@Composable
fun MovieDetailsItem(item: MovieDetailsItem) {
    Card(modifier = Modifier.safeDrawingPadding()
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Text(modifier = Modifier.fillMaxWidth(),
                text = item.title?: "",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(12.dp))
            if(!item.videoId.isNullOrEmpty()){
                YouTubePlayer(
                    videoId = item.videoId
                )
            }else{
                AsyncImage(
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxWidth(.25f),
                    model = item.posterImageUrl,
                    contentDescription = item.title,
                )
            }

            Text(modifier = Modifier.fillMaxWidth()
                .padding(top = 12.dp),
                text = "No Of Episodes:- " + (item.noOfEpisodes ?: "0"),
                style = MaterialTheme.typography.bodyLarge)
            Text(modifier = Modifier.fillMaxWidth(),
                text = "Rating:- " + (item.rating ?: "0")+"*",
                style = MaterialTheme.typography.bodyLarge)

            Text(modifier = Modifier.fillMaxWidth(),
                text = "genres:- " + (item.genres ?: ""),
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(24.dp))
            Text(modifier = Modifier.fillMaxWidth(),
                text = "synopsis:- " + (item.synopsis ?: ""),
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}
