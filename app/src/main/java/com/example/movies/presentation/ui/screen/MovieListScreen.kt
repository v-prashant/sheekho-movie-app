package com.example.movies.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.movies.domain.model.MovieItem
import com.example.movies.presentation.viewmodels.MovieListVM

@Composable
fun MovieListScreen(viewModel: MovieListVM = hiltViewModel(), onClick: (id: Int?) -> Unit ) {

    val movieList = viewModel.movieList.collectAsState(ApiResult.Loading)

    Box(modifier = Modifier.safeDrawingPadding()
         .fillMaxSize()
    ) {
        when(movieList.value){
            is ApiResult.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is ApiResult.Success -> {
                LazyColumn {
                    val list = (movieList.value as ApiResult.Success<List<MovieItem>>).data
                    items(list) { item ->
                        MovieCard(item){ id->
                            onClick(id)
                        }
                    }
                }
            }
            is ApiResult.Error -> {
                Toast.makeText(LocalContext.current, (movieList.value as ApiResult.Error).message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}

@Composable
fun MovieCard(item: MovieItem, onClick: (id: Int?) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        onClick = {
            onClick(item.id)
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Text(modifier = Modifier.fillMaxWidth(),
                text = item.title?: "",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center)
            AsyncImage(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                model = item.posterImageUrl,
                contentDescription = item.title
            )

            Text(modifier = Modifier.fillMaxWidth()
                .padding(top = 12.dp),
                text = "No Of Episodes:- " + (item.noOfEpisodes ?: "0"),
                style = MaterialTheme.typography.bodyLarge)
            Text(modifier = Modifier.fillMaxWidth(),
                text = "Rating:- " + (item.rating ?: "0")+"*",
                style = MaterialTheme.typography.bodyLarge)

        }
    }
}
