package com.example.user.navigationdrawer;

import android.app.FragmentManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static FloatingActionButton fab;
    public static AppDB appDB;
    FragmentManager fragmentManager = getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDB = Room.databaseBuilder(getApplicationContext(),AppDB.class,"appdb").allowMainThreadQueries().build();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager.beginTransaction().replace(R.id.content_frame,new FirstFragment()).commit();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        hideFAB();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static void showFAB(){
        fab.show();
    }
    public static void hideFAB(){
        fab.hide();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) throws NullPointerException {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_dashboard:
                fragmentManager.beginTransaction().replace(R.id.content_frame,new FirstFragment()).commit();
                hideFAB();
                break;
            case R.id.nav_occupants:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new SecondFragment()).commit();
                showFAB();
                break;
            case R.id.nav_rooms:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new ThirdFragment()).commit();
                showFAB();
                break;
            case R.id.nav_rent:
                fragmentManager.beginTransaction().replace(R.id.content_frame,new FourthFragment()).commit();
                showFAB();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            Occupants occupant = new Occupants(Integer.parseInt(data.getStringExtra("Oid")),data.getStringExtra("Name"),data.getStringExtra("Phone"),data.getStringExtra("Room"));
            try{
                appDB.myDAO().updateOccupant(occupant);Snackbar snackbar = Snackbar.make(getCurrentFocus(),"Changes Saved !!", Snackbar.LENGTH_LONG);
                View snackbarview = snackbar.getView();
                snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialGreenDark,null));
                snackbar.show();
            } catch (Exception e) {
                String error = e.getClass().toString();
                Snackbar snackbar = Snackbar.make(getCurrentFocus(), error, Snackbar.LENGTH_LONG);
                View snackbarview = snackbar.getView();
                snackbarview.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.materialRedDark,null));
                snackbar.show();
            }
        }
        else if (requestCode == 3){

        }
    }
}
