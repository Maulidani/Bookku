package com.example.bookku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bookku.fragment.BacaNantiFragment;
import com.example.bookku.fragment.BooksFragment;
import com.example.bookku.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack){
            super.onBackPressed();
            return;
        }
        this.doubleBack = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack=false;
            }
        },2000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        loadFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigasi);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_fragment,fragment)
                    .commit();
            return true;
        }
        return false;
    }

//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//        loadFragment(new HomeFragment());
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigasi);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//
//        bottomNavigationView.setSelectedItemId(R.id.home_menu);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
            case R.id.books_menu:
                fragment = new BooksFragment();
                break;
            case R.id.baca_nanti_menu:
                fragment = new BacaNantiFragment();
                break;
        }
        return loadFragment(fragment);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_item, menu);
//
//        MenuItem search = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
//
//        return true;
//
//    }
}