package com.example.cleanlife;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.cleanlife.Rooms.onItemClick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
    implements FirstDialogFragment.MoveCheckedItem{

    FragmentManager fm;
    SharedPreferences prefs;
    final String PREFS_NAME = "PrefFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        prefs = getSharedPreferences(PREFS_NAME, 0);


        if (checkFirstRun())
        {


            //CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            //p.setAnchorId(View.NO_ID);
            //fab.setLayoutParams(p);
            //fab.setVisibility(View.GONE);
            //toolbar.setVisibility(View.GONE);
            fm = getSupportFragmentManager();

            Log.d("main", "first run");

            fm.beginTransaction()
                    .add(android.R.id.content, new FirstDialogFragment())
                    .addToBackStack(null)
                    .commit();


        }
        else
        {
            //fab.setVisibility(View.VISIBLE);
            //toolbar.setVisibility(View.VISIBLE);
            //fm.beginTransaction()
                    //.add(android.R.id.content, new FirstDialogFragment())
                    //.addToBackStack(null)
                    //.commit();

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.dropdown, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        fm = getSupportFragmentManager();

        Log.d("main", "first run");

        switch(item.getItemId()){
            case R.id.action_settings:

                fm.beginTransaction()
                        .add(android.R.id.content, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.checkListmenuoption:
                fm.beginTransaction()
                        .add(android.R.id.content, new CheckListFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.extraHelp:
                fm.beginTransaction()
                        .add(android.R.id.content, new HelpFragment())
                        .addToBackStack(null)
                        .commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }



    }


    private boolean checkFirstRun() {
        if (prefs.getBoolean("first_time", true))
        {
            prefs.edit().putBoolean("first_time",false).commit();
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void DifficultyChanged(int userchoice) {
        SecondDialogFragment secondDialogFragment = new SecondDialogFragment();


        secondDialogFragment.getUsersPreference(userchoice);


    }
}