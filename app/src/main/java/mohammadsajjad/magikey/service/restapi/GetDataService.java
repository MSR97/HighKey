package mohammadsajjad.magikey.service.restapi;

import mohammadsajjad.magikey.model.LoginUser;
import mohammadsajjad.magikey.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("Alle/showAll.php")
    Call<MovieResponse>getAllMovies();

    @GET("users/login.php")
    Call<LoginUser>performUserLogin(@Query("user_name")String userName,@Query("user_password")String user_password);

    @GET("Alle/showAll.php")
    Call<MovieResponse>getSearchedMovies(@Query("data") String searchingWord);




}
