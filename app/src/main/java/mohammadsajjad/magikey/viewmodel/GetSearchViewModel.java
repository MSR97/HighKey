package mohammadsajjad.magikey.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mohammadsajjad.magikey.model.Movies;
import mohammadsajjad.magikey.repository.Repository;
import mohammadsajjad.magikey.util.PrefHelper;

public class GetSearchViewModel extends AndroidViewModel {
    Repository repository;
    private LiveData<List<Movies>> getSearchedMovies;
    private LiveData<Boolean>loading;
    public static  MutableLiveData<String>searchingWords=new MutableLiveData<>();



    public GetSearchViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);

    }


    public LiveData<List<Movies>>getSearchedMovies(){
        getSearchedMovies =repository.getSearchMutableAllMovies(searchingWords.getValue());
        return getSearchedMovies;
    }
    public LiveData<Boolean> getLoading() {
        loading=repository.getLoading();
        return loading;
    }





}
