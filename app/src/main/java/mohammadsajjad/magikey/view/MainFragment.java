package mohammadsajjad.magikey.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.databinding.MainFragmentBinding;
import mohammadsajjad.magikey.viewmodel.MainViewModel;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    MainFragmentBinding binding;
    MainFragmentClickHandlers handlers;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.main_fragment, container, false);
        MainFragmentBinding binding= DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        this.binding=binding;
        handlers=new MainFragmentClickHandlers(getContext());
        binding.setClickHandlers(handlers);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    public class MainFragmentClickHandlers{
        Context context;
        public MainFragmentClickHandlers(Context context) {
            this.context = context;
        }

        public void onStartGetAllFrag(View view){
            Navigation.findNavController(view).navigate(MainFragmentDirections.actionGetAll());


        }

        public void onStartSearchFragment(View view){
            Navigation.findNavController(view).navigate(MainFragmentDirections.actionSearch());


        }


    }

}
