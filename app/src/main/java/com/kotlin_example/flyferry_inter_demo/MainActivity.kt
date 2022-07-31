package com.kotlin_example.flyferry_inter_demo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null;
    //private final  var drawer: DrawerLayout? = null;
    private lateinit var drawer: DrawerLayout;
    private lateinit var toggle: ActionBarDrawerToggle;
    private lateinit  var bottomNavigationView: BottomNavigationView;
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        val firstFragment=homeFragment();
        val secondFragment=profileFragment();
        val thirdFragment=searchFragment();
        if (savedInstanceState == null) {
            setCurrentFragment(firstFragment)
        }
        bottomNavigationView.setOnItemSelectedListener { when(it.itemId){
            R.id.ic_home->setCurrentFragment(firstFragment)
            R.id.ic_person->setCurrentFragment(secondFragment)
            R.id.ic_search->setCurrentFragment(thirdFragment)

        }
            true }



        ///------
        ////Drawer listner close
        navigationView.setNavigationItemSelectedListener { item ->
            Log.d("anil", "onNavigationItemSelected: run")
            val id = item.itemId
            if (id == R.id.home_nav_icon) Toast.makeText(
                applicationContext,
                "Click On Home icon",
                Toast.LENGTH_LONG
            ).show() else if (id == R.id.posts_nav_icon) Toast.makeText(
                applicationContext, "Click On Post icon", Toast.LENGTH_LONG
            ).show() else if (id == R.id.friend_nav_icon) Toast.makeText(
                applicationContext, "Click On Friend icon", Toast.LENGTH_LONG
            ).show() else if (id == R.id.logout_nav_icon) Toast.makeText(
                applicationContext, "Click On Logout icon", Toast.LENGTH_LONG
            ).show()
            drawer.closeDrawer(GravityCompat.START)
            true
        }

        ////-----------

       // navigationView.

    }//on create close

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()
    }

}//main class