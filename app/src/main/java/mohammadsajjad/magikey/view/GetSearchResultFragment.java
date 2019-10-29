package mohammadsajjad.magikey.view;


import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.adapters.MoviesAllAdapter;
import mohammadsajjad.magikey.adapters.SearchResultAdapter;
import mohammadsajjad.magikey.databinding.FragmentGetSearchResultBinding;
import mohammadsajjad.magikey.model.Movies;
import mohammadsajjad.magikey.viewmodel.GetSearchViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetSearchResultFragment extends Fragment {
    private FragmentGetSearchResultBinding binding;
    private GetSearchViewModel getSearchViewModel;
    private ArrayList<Movies> movies = new ArrayList<>();
    private ListView listView;
    private ProgressBar loadingView;
    private boolean isLoading = false;
    LinearLayout layout;
    GetSearchResultFragmentArgs fragmentArgs;
    private String enteredSearchingWord;


    public GetSearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentGetSearchResultBinding fragmentGetSearchResultBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_search_result, container, false);
        binding = fragmentGetSearchResultBinding;

        return fragmentGetSearchResultBinding.getRoot();


    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSearchViewModel = ViewModelProviders.of(this).get(GetSearchViewModel.class);
        loadingView = binding.loadingView;
        layout = binding.searchResultLayout;
        fragmentArgs = GetSearchResultFragmentArgs.fromBundle(getArguments());
        enteredSearchingWord = fragmentArgs.getSearchingWords();
        getSearchMovie(GetSearchViewModel.searchingWords.getValue());
        GetSearchViewModel.searchingWords.setValue("");
        binding.newSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSearchMovie(binding.newSearchText.getText().toString());
                hideSoftKeyboard(binding
                .newSearchText);
                GetSearchViewModel.searchingWords.setValue("");


            }
        });





    }

    void getSearchMovie(String searchingWord) {
        GetSearchViewModel.searchingWords.setValue(searchingWord);
        getSearchViewModel.getLoading().observe((LifecycleOwner) getContext(), (Boolean isLoading) -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                binding.newSearchButton.setVisibility(View.GONE);
                binding.newSearchText.setVisibility(View.GONE);
                if (isLoading) {
                    isLoading = true;

                }


            }
        });

        if (isLoading != true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                layout.setBackgroundColor(getContext().getColor(R.color.app_yellow));
            }
            getSearchViewModel.getSearchedMovies().observe(this, new Observer<List<Movies>>() {
                @Override
                public void onChanged(List<Movies> moviesFromLiveData) {
                    movies = (ArrayList<Movies>) moviesFromLiveData;
                    showOnListView(searchingWord, movies);
                    loadingView.setVisibility(View.GONE);
                    binding.newSearchButton.setVisibility(View.VISIBLE);
                    binding.newSearchText.setVisibility(View.VISIBLE);

                    layout.setBackgroundColor(Color.WHITE);
                    Toast.makeText(getContext(), "تعداد کل : " + movies.size(), Toast.LENGTH_SHORT).show();
                }
            });



        }


    }

    private void showOnListView(String searchingWords, ArrayList<Movies> movies) {
        listView = binding.lvForSearch;
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(getContext(), 1, movies, searchingWords);
        searchResultAdapter.notifyDataSetChanged();
        listView.setAdapter(searchResultAdapter);




    }

    protected void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }


}

