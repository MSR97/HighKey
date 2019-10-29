package mohammadsajjad.magikey.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.databinding.MainFragmentBinding;
import mohammadsajjad.magikey.databinding.ToollbarBinding;
import mohammadsajjad.magikey.util.PrefHelper;
import mohammadsajjad.magikey.viewmodel.MainViewModel;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    MainFragmentBinding binding;
    MainFragmentClickHandlers handlers;
    ToollbarBinding toollbarBinding;



    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.main_fragment, container, false);
        MainFragmentBinding binding= DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        this.binding=binding;
        handlers=new MainFragmentClickHandlers(getContext());
        binding.setClickHandlers(handlers);
        setHasOptionsMenu(true);
        toollbarBinding=DataBindingUtil.inflate(inflater, R.layout.toollbar, container, false);






        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

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

        public void onExit(View view){
            getActivity().finish();
        }

        public void onLogOut(View view){
            PrefHelper prefHelper=new PrefHelper(context);

            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setMessage("آیا میخواهید از حساب کاربری خارج شوید ؟");
            builder.setPositiveButton("بلی", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    prefHelper.writeLoginStatus(false);
                    Navigation.findNavController(view).navigate(MainFragmentDirections.actionMainFragmentToLoginFragment2());

                }
            });
            builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog=builder.create();
            dialog.setCancelable(true);
            dialog.show();
        }



    }


}
