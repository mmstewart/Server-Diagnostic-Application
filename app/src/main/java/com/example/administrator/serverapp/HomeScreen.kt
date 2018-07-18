package com.example.administrator.serverapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawer : DrawerLayout
    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        
        //Sets Home screen title of action bar to "Home"
        supportActionBar!!.title = "Home"

        drawer = findViewById(R.id.drawer1)
        toggle = object : ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close) {
            //When the navigation drawer opens, add 'Navigation' in the title
            override fun onDrawerOpened(drawerView: View) {
                supportActionBar!!.title = "Navigation"
                super.onDrawerOpened(drawerView)
            }
        }
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Adds menu icon

        nav_view1.setNavigationItemSelectedListener(this)

        //Fade in/zoom in animation of 'Welcome to your Server' text
        val myText : TextView = findViewById(R.id.textView)
        val animation = AnimationSet(true)
        animation.addAnimation(AlphaAnimation(0.0f, 1.0f))
        animation.addAnimation(ScaleAnimation(
                .5f,
                1f,
                .5f,
                1f,
                Animation.RELATIVE_TO_SELF,
                .5f,
                Animation.RELATIVE_TO_SELF,
                .5f
        ))
        animation.duration = 4500
        myText.alpha
        myText.startAnimation(animation)
    }

    //Function allow user to access and press on the tabs in the navigation drawer
    private fun navScreen(id: Int) {
        val fragment = when (id) {
            R.id.Home -> { HomeFragment() }
            R.id.Diagnostic -> { ServerReportScreen() }
            else -> { HomeFragment() }
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.relative_layout, fragment)
                .addToBackStack(null)
                .commit()
    }

    //Called when item in the navigation meu is selected
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navScreen(item.itemId)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    //Used whenever item in the navigation menu is selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

//Fragment Class of Home (Allows user to press on the navigation drawer tabs)
class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        
        //After the navigation drawer is used, the title of the action bar is maintained to say "Home"
        (activity as AppCompatActivity).supportActionBar!!.title = "Home"

        return LayoutInflater.from(container?.context).inflate(
                R.layout.activity_home_screen,
                container,
                false
        )
    }
}
