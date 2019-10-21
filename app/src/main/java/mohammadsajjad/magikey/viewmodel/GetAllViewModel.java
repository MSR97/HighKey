package mohammadsajjad.magikey.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mohammadsajjad.magikey.model.Movies;
import mohammadsajjad.magikey.repository.Repository;

public class GetAllViewModel extends AndroidViewModel {
    Repository repository;
    private LiveData<List<Movies>>getAllMovies;
    private LiveData<Boolean>loading;



    public GetAllViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);

    }

    public LiveData<List<Movies>>getAllMovies(){
        getAllMovies=repository.getRemoteMutableAllMovies();
        return getAllMovies;
    }
    public LiveData<Boolean> getLoading() {
        loading=repository.getLoading();
        return loading;
    }





}
