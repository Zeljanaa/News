package com.example.newsapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val navController = this.findNavController(R.id.nav_host_fragment)


        /**
         *Bottom Navigation with action bar
         * */
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(
                item, Navigation.findNavController(
                    this,
                    R.id.nav_host_fragment
                )
            )
        }

        binding.bottomNavigation.apply {
            setupWithNavController(navController)

            setOnNavigationItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(
                    item, Navigation.findNavController(
                        this@HomeActivity,
                        R.id.nav_host_fragment
                    )
                )
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.countries,
                R.id.categories
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavigation.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                //logout of app
                FirebaseAuth.getInstance().signOut()
                val i = Intent(applicationContext, Login::class.java)
                startActivity(i)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}