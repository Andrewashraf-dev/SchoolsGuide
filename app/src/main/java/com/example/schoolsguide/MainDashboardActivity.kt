package com.example.schoolsguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.schoolsguide.databinding.ActivityMainDashboardBinding
import com.example.schoolsguide.schools.intern.StGeorge
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainDashboardActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    lateinit var viewBinding: ActivityMainDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainDashboardBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.cardPublic.setOnClickListener {

            val intent = Intent(this, PublicSchoolsActivity::class.java);
            startActivity(intent);

        }

        viewBinding.cardPrivate.setOnClickListener {

            val intent = Intent(this, PrivateSchoolsActivity::class.java);
            startActivity(intent);

        }

        viewBinding.cardInternational.setOnClickListener {

            val intent = Intent(this, InternationalSchoolsActivity::class.java);
            startActivity(intent);
        }

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {


            when (it.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, MainDashboardActivity::class.java))
                }
                R.id.nav_message -> Toast.makeText(applicationContext, "Clicked Message", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(applicationContext, "Clicked Setting", Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> {
                    logout()
                    true
                    finish()
                }
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.nav_about_us -> startActivity(Intent(this, AboutUsActivity::class.java))
            }

            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

}