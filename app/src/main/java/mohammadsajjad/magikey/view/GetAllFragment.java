package mohammadsajjad.magikey.view;


import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.adapters.MoviesAllAdapter;
import mohammadsajjad.magikey.databinding.FragmentGetAllBinding;
import mohammadsajjad.magikey.model.Movies;
import mohammadsajjad.magikey.viewmodel.GetAllViewModel;


public class GetAllFragment extends Fragment {
    private GetAllViewModel getAllViewModel;
    private ArrayList<Movies> movies;
    private FragmentGetAllBinding binding;
    private RecyclerView recyclerView;
    private MoviesAllAdapter moviesAllAdapter;
    private ProgressBar loadingView;
    private boolean isLoading=false;
    private ConstraintLayout layout;





    public GetAllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_get_all, container, //false);
        FragmentGetAllBinding fragmentGetAllBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_get_all, container, false);
        this.binding=fragmentGetAllBinding;

        return fragmentGetAllBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllViewModel= ViewModelProviders.of(this).get(GetAllViewModel.class);
        layout=binding.getAllLayout;
        loadingView=binding.loadingView;
        getAllMovies();



    }

    void getAllMovies() {
        getAllViewModel.getLoading().observe((LifecycleOwner) getContext(), isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    isLoading = true;

                }


            }
        });
        if (isLoading != true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                layout.setBackgroundColor(getContext().getColor(R.color.app_yellow));
            }
            getAllViewModel.getAllMovies().observe(this, new Observer<List<Movies>>() {
                @Override
                public void onChanged(List<Movies> moviesFromLiveData) {
                    movies = (ArrayList<Movies>) moviesFromLiveData;
                    showOnRecycleView();
                    loadingView.setVisibility(View.GONE);
                    layout.setBackgroundColor(Color.WHITE);
                    Toast.makeText(getContext(),"تعداد کل : "+ movies.size(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void showOnRecycleView(){
        recyclerView=binding.rvGetAll;
        moviesAllAdapter=new MoviesAllAdapter(getContext(),movies);
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }
        recyclerView.setAdapter(moviesAllAdapter);
        moviesAllAdapter.notifyDataSetChanged();


    }
}
