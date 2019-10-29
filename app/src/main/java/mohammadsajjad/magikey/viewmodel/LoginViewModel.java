package mohammadsajjad.magikey.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import mohammadsajjad.magikey.model.LoginUser;
import mohammadsajjad.magikey.repository.Repository;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<String> emailAddress=new MutableLiveData<>();

    public MutableLiveData<String>password=new MutableLiveData<>();

    private MutableLiveData<LoginUser> userMutableLiveData;
    private Repository repository;

    private LiveData<Boolean>isLogin;

    public LiveData<Boolean> getIsLogin() {
        isLogin =repository.getIsLogin();
        return isLogin;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }



    public MutableLiveData<LoginUser> getUserMutableLiveData() {
        if(userMutableLiveData==null){
            userMutableLiveData=new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(View view){
        LoginUser loginUser=new LoginUser(emailAddress.getValue(), password.getValue());
        userMutableLiveData.setValue(loginUser);
    }

    public void onLogin(){
        repository.getUserMutableLiveData(emailAddress.getValue(), password.getValue());

    }


}
