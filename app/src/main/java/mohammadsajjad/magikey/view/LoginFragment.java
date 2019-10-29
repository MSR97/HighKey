package mohammadsajjad.magikey.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.databinding.FragmentLoginBinding;
import mohammadsajjad.magikey.model.LoginUser;
import mohammadsajjad.magikey.util.PrefHelper;
import mohammadsajjad.magikey.viewmodel.LoginViewModel;

import static java.util.Objects.requireNonNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;
    FragmentLoginBinding binding;
    PrefHelper prefHelper;
    private MutableLiveData<Boolean> isLogin=new MutableLiveData<>();


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);


        FragmentLoginBinding binding = DataBindingUtil.inflate
                (inflater, R.layout.fragment_login, container, false);

        prefHelper=new PrefHelper(binding.getRoot().getContext());
        this.binding = binding;
        if(prefHelper.readLoginStatus()){
            Navigation.findNavController(container).navigate(LoginFragmentDirections.actionLoginFragment2ToMainFragment());
        }


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
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
                binding.setLifecycleOwner(this);
                binding.setLoginViewModel(loginViewModel);
                LoginFragmentClickHandler clickHandler=new LoginFragmentClickHandler(getContext());
                binding.setClickListener(clickHandler);

                loginViewModel.getIsLogin().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        isLogin.setValue(aBoolean);
                    }
                });

        loginViewModel.getUserMutableLiveData().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {
                if (TextUtils.isEmpty(requireNonNull(loginUser).getUserEmailAddress())) {
                    binding.username.setError("ایمیل را وارد نمایید");
                    binding.username.requestFocus();

                }
                else if(!loginUser.isEmailValid()){
                    binding.username.setError("ایمیل نامعتبر است");
                    binding.username.requestFocus();


                }
                else if(TextUtils.isEmpty(requireNonNull(loginUser).getUserPassword())){
                    binding.password.setError("گذرواژه را وارد نمایید");
                    binding.password.requestFocus();


                }
                else if(!loginUser.isPasswordValid()){
                    binding.password.setError("گذرواژه کوتاه است");
                    binding.password.requestFocus();
                }

                else {
                    loginViewModel.onLogin();
                    loginViewModel.getIsLogin().observe(requireNonNull(binding.getLifecycleOwner()), new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                navToMain(view);
                                loginViewModel.getIsLogin().removeObservers(binding.getLifecycleOwner());
                            }
                        }
                    });



                }

            }
        });


    }

    void navToMain(View view){
        Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragment2ToMainFragment());

    }

    public class LoginFragmentClickHandler{
        Context context;

        public LoginFragmentClickHandler(Context context) {
            this.context = context;
        }

        public void onExit(View view){
            getActivity().finish();
        }

    }
}
