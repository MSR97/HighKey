package mohammadsajjad.magikey.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import mohammadsajjad.magikey.model.LoginUser;
import mohammadsajjad.magikey.model.MovieResponse;
import mohammadsajjad.magikey.model.Movies;
import mohammadsajjad.magikey.service.restapi.GetDataService;
import mohammadsajjad.magikey.service.restapi.RetrofitInstance;
import mohammadsajjad.magikey.util.PrefHelper;
import mohammadsajjad.magikey.view.LoginFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    Application application;

    private PrefHelper prefHelper;

    private MutableLiveData<LoginUser>userMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Boolean>isLogin=new MutableLiveData<>();

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MutableLiveData<List<Movies>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Movies> movies = new ArrayList<>();

    public Repository(Application application) {
        this.application = application;
        prefHelper=new PrefHelper(application);
    }


    public MutableLiveData<LoginUser> getUserMutableLiveData(String userName,String userPassword ) {
        loading.setValue(true);
        GetDataService getDataService=RetrofitInstance.getService();
        Call<LoginUser> call=getDataService.performUserLogin(userName, userPassword);

        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                LoginUser loginUser = response.body();
                if (loginUser != null) {
                    if (loginUser.getResponse().equals("ok")) {
                        prefHelper.writeLoginStatus(true);
                        isLogin.setValue(true);
                    }
                    else if(loginUser.getResponse().equals("acc_not_active")){
                        isLogin.setValue(false);
                        prefHelper.displayToast("اکانت شما در انتظار فعالسازی می باشد");



                    }

                    else if (loginUser.getResponse().equals("failed")) {
                        prefHelper.displayToast("اطلاعات نا معتبر است");
                        isLogin.setValue(false);
                    }



                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                prefHelper.displayToast("مشکل در برقراری ارتباط");
                isLogin.setValue(false);

            }
        });



        return userMutableLiveData;
    }

    public MutableLiveData<List<Movies>> getRemoteMutableAllMovies() {
        loading.setValue(true);
        GetDataService getDataService = RetrofitInstance.getService();
        Call<MovieResponse> call = getDataService.getAllMovies();
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


    public MutableLiveData<List<Movies>> getSearchMutableAllMovies(String searchingWord) {
        loading.setValue(true);
        GetDataService getDataService = RetrofitInstance.getService();
        Call<MovieResponse> call = getDataService.getSearchedMovies(searchingWord);
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

    public MutableLiveData<Boolean> getIsLogin() {
        return isLogin;
    }
}







