package mohammadsajjad.magikey.service.restapi;

import mohammadsajjad.magikey.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMovieDataService {
    @GET("Alle/showAll.php")
    Call<MovieResponse>getAllMovies();


}
