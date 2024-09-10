package  com.example.movie.data.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movie.data.models.MovieWatched

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieWatched)

    @Update
    suspend fun updateMovie(movie: MovieWatched)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieWatched>

    @Query("SELECT COUNT(*) FROM movies WHERE movieId = :id")
    suspend fun isMovieExists(id: Int): Int
}
