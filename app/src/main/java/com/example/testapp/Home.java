package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.testapp.fragments.Feeds;
import com.example.testapp.fragments.Recent;
import com.example.testapp.others.TabAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle abdt;
    private DrawerLayout dl;
    private TextView title;
    private ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Feeds(), "Feeds");
        adapter.addFragment(new Recent(), "Recent");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        dl = findViewById(R.id.drawer_layout);
        abdt = new ActionBarDrawerToggle(this, dl, toolbar, R.string.openDrawer, R.string.closeDrawer);
        dl.addDrawerListener(abdt);

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        title = findViewById(R.id.toolbar_title);
        search = findViewById(R.id.btn_search);

    }




    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        abdt.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        abdt.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (abdt.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void menuButton(View view) {
        dl.openDrawer(GravityCompat.START);
    }

    public void searchButton(View view) {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_LONG).show();
                title.setText("Home");
                search.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_downloads:
                title.setText(R.string.downloads);
                search.setVisibility(View.GONE);
                Toast.makeText(this, "Downloads", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_q:
                title.setText(R.string.questions);
                search.setVisibility(View.GONE);
                Toast.makeText(this, "Questions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_test:
                title.setText(R.string.tests);
                search.setVisibility(View.GONE);
                Toast.makeText(this, "Tests", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_todo:
                title.setText(R.string.to_do_list);
                search.setVisibility(View.GONE);
                Toast.makeText(this, "Todo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_notice:
                title.setText(R.string.notifications);
                search.setVisibility(View.GONE);
                Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about:
                title.setText(R.string.about_us);
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                title.setText(R.string.logout);
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }

        dl.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

