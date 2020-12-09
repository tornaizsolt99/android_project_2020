package com.example.where_to_eat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    @BindView(R.id.main_bottom_navigation) lateinit var navi: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        var profileFragment= ProfileFragment()
        var restaurantFragment= RestaurantFragment()
        var favoritesFragment= FavoritesFragment()


        var bottomNavigationView=findViewById<BottomNavigationView>(R.id.main_bottom_navigation)
        navi.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.menu_item_profile -> replacefragment(profileFragment)
                R.id.menu_item_restaurant ->replacefragment(restaurantFragment)
                R.id.menu_item_favorites -> replacefragment(favoritesFragment)
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