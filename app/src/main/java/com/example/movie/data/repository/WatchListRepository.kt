package  com.example.movie.data.repository
import android.util.Log
import com.example.movie.data.local.MovieDao
import com.example.movie.data.models.MovieWatched

class WatchListMovieRepository(private val movieDao: MovieDao) {

    suspend fun saveMovie(movie: MovieWatched) {
        val exists = movieDao.isMovieExists(movie.movieId)
        if (exists == 0) {
            movieDao.insertMovie(movie)
            Log.d("WatchListMovieRepository", "Movie inserted: $movie")
        } else {
            movieDao.updateMovie(movie)
            Log.d("WatchListMovieRepository", "Movie updated: $movie")
        }
    }


    suspend fun getAllMovies(): List<MovieWatched> {
        return movieDao.getAllMovies()
    }
}
