package mohammadsajjad.magikey.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

public class PrefHelper {
    private static PrefHelper instance;
    private SharedPreferences preferences;
    Context context;

    public PrefHelper(Context context) {
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
        this.context=context;

    }

    public static PrefHelper getInstance(Context context){
        if(instance==null){
            instance=new PrefHelper(context);
        }
        return instance;
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("login_status",status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return preferences.getBoolean("login_status",false);
    }

    public void writeName(String name){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user_name", name);
        editor.commit();
    }

    public String readName(){
        return preferences.getString("user_name", "User");
    }

    public void displayToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }



}
