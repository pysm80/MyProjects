
package com.myapplication.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.myapplication.Fragments.ContactsFragment;
import com.myapplication.Fragments.ContactsFragment_;
import com.myapplication.Fragments.GeneralFragment;
import com.myapplication.Fragments.GeneralFragment_;
import com.myapplication.Fragments.ParentFragment_;
import com.myapplication.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements ParentFragment_.OnFragmentInteractionListener,
        ContactsFragment_.OnFragmentInteractionListener,GeneralFragment_.OnFragmentInteractionListener{

    //Base fragment. This parent will be placed on frame window
     ParentFragment_ parentFragment;

    //Toolbar object
    @ViewById(R.id.app_bar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
    }

    @AfterViews
    void afterViews(){
        setupToolbar();
        setDefaultFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(this, "Fav selected", Toast.LENGTH_SHORT).show();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    void setupToolbar() {
        toolbar.setTitle("Sample Data");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(null);
    }

    private void setDefaultFragment() {
        parentFragment = new ParentFragment_();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_main, parentFragment).commitAllowingStateLoss();
    }

    public void onFragmentInteraction(Uri uri){
    }
}