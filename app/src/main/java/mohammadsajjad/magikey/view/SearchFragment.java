package mohammadsajjad.magikey.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.databinding.FragmentSearchBinding;
import mohammadsajjad.magikey.model.Movies;
import mohammadsajjad.magikey.viewmodel.GetAllViewModel;
import mohammadsajjad.magikey.viewmodel.GetSearchViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private EditText editText;
    private FragmentSearchBinding binding;
    SearchFragmentClickHandlers handlers;
    GetSearchViewModel getSearchViewModel;
    GetAllViewModel getAllViewModel;
    ArrayList<Movies>moviesList=new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        handlers=new SearchFragmentClickHandlers(getContext());
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false);

        return binding.getRoot();


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
        binding.setClickHandle(handlers);
        super.onViewCreated(view, savedInstanceState);
        getSearchViewModel= ViewModelProviders.of(this).get(GetSearchViewModel.class);
        getAllViewModel=ViewModelProviders.of(this).get(GetAllViewModel.class);

        binding.setGetSearchViewModel(getSearchViewModel);
        binding.setLifecycleOwner(this);
        spinnerShow();







    }

    public class SearchFragmentClickHandlers{
        Context context;

        public SearchFragmentClickHandlers(Context context) {
            this.context = context;
        }

        public void onStartGetSearchResultFragment(View view){
            editText=binding.edtSearch;
            Navigation.findNavController(view).navigate(SearchFragmentDirections.actionSearchFragmentToGetSearchResultFragment(editText.getText().toString()));
            hideSoftKeyboard(editText);




        }


    }
    private void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }


    void spinnerShow(){
        hideSoftKeyboard(binding.edtSearch);
        List<String> list=new ArrayList<>();
        AppCompatSpinner spinner=binding.spinner;
        list.add(0, "تمامی داده ها");

        getAllViewModel.getAllMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                for(Movies m:movies){

//                        String name=m.getName().substring(0,m.getName().indexOf("_"));
                        list.add(m.getName().replace(".wav", ""));



                }
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,list);
        spinner.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setSelection(0);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(MainActivity.this, "Selected : "+ categories.get(position), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }









}
