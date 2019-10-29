package mohammadsajjad.magikey.model;

import android.util.Patterns;

import com.google.gson.annotations.SerializedName;

public class LoginUser {
    private String userEmailAddress;
    private String userPassword;
    @SerializedName("response")
    private String response;
    @SerializedName("name")
    private String logined_name;

    public LoginUser(String userEmailAddres, String userPassword) {
        this.userEmailAddress = userEmailAddres;
        this.userPassword = userPassword;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isEmailValid(){
        return Patterns.EMAIL_ADDRESS.matcher(getUserEmailAddress()).matches();
    }

    public boolean isPasswordValid(){
        return getUserPassword().length()>5;
    }

    public String getResponse() {
        return response;
    }

    public String getLogined_name() {
        return logined_name;
    }
}
