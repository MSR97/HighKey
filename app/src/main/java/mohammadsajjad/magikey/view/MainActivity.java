package mohammadsajjad.magikey.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.databinding.ToollbarBinding;
import mohammadsajjad.magikey.util.PrefHelper;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

                fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                navController = Navigation.findNavController(this, R.id.fragment);

            }







        }
    }

