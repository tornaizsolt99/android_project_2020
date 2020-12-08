package com.example.where_to_eat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var profileFragment= ProfileFragment()
        var restaurantFragment= RestaurantFragment()

        var bottomNavigationView=findViewById<BottomNavigationView>(R.id.main_bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.menu_item_profile -> replacefragment(profileFragment)
                R.id.menu_item_restaurant ->replacefragment(restaurantFragment)
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