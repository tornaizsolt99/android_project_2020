package com.example.where_to_eat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.fragments.DetailsFragment

class RestaurantsAdapter(private val restaurants:
                         List<Restaurants>) : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameView: TextView = itemView.findViewById(R.id.item_restaurants_name)
        val locationView: TextView = itemView.findViewById(R.id.item_restaurants_location)
        val priceView:TextView=itemView.findViewById(R.id.item_restaurants_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()= restaurants.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text=restaurants[position].name
        holder.locationView.text=restaurants[position].city
        holder.priceView.text= "Price: ${restaurants[position].price.toString()}"

        holder.itemView.setOnClickListener{
            val detailsFragment= DetailsFragment()
            val bundle=Bundle()
            bundle.putInt("id",restaurants[position].id)
            detailsFragment.arguments=bundle
            val activity: AppCompatActivity=it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().apply{
                replace(R.id.activity_frame,detailsFragment)
                commit()
            }
        }
    }

}
