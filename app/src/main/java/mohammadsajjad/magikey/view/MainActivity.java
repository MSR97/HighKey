package mohammadsajjad.magikey.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import mohammadsajjad.magikey.R;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {

            fragment=getSupportFragmentManager().findFragmentById(R.id.fragment);

            navController= Navigation.findNavController(this,R.id.fragment);




        }
    }
}
