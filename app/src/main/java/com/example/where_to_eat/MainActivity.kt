package com.example.where_to_eat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.example.where_to_eat.databinding.ActivityMainBinding
import com.example.where_to_eat.fragments.FavoritesFragment
import com.example.where_to_eat.fragments.ProfileFragment
import com.example.where_to_eat.fragments.RestaurantFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var profileFragment= ProfileFragment()
        var restaurantFragment= RestaurantFragment()
        var favoritesFragment= FavoritesFragment()


        //var bottomNavigationView=findViewById<BottomNavigationView>(R.id.main_bottom_navigation)
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.menu_item_profile -> replacefragment(profileFragment)
                R.id.menu_item_restaurant ->replacefragment(restaurantFragment)
                R.id.menu_item_favourites -> replacefragment(favoritesFragment)
            }
            true

        }


    }

    private fun replacefragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_frame ,fragment)
            commit()
        }
    }
}