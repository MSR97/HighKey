package mohammadsajjad.magikey.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import mohammadsajjad.magikey.model.MovieResponse;
import mohammadsajjad.magikey.model.Movies;
import mohammadsajjad.magikey.service.restapi.GetMovieDataService;
import mohammadsajjad.magikey.service.restapi.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    Application application;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MutableLiveData<List<Movies>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Movies> movies = new ArrayList<>();

    public Repository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Movies>> getRemoteMutableAllMovies() {
        loading.setValue(true);

        GetMovieDataService getMovieDataService = RetrofitInstance.getService();
        Call<MovieResponse> call = getMovieDataService.getAllMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse != null && movieResponse.getMovies() != null) {

                    movies = (ArrayList<Movies>) movieResponse.getMovies();
                    mutableLiveData.setValue(movies);


                }
            }


            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                loading.setValue(false);


            }
        });


        return mutableLiveData;
    }


    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

}





