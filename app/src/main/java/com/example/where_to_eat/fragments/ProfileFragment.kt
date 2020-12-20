package com.example.where_to_eat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.where_to_eat.databinding.FragmentProfileBinding
import com.example.where_to_eat.databinding.FragmentRestaurantBinding
import com.example.where_to_eat.room.Profile
import com.example.where_to_eat.room.ProfileDatabase
import com.example.where_to_eat.room.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    lateinit var nameText:TextView
    lateinit var addressTtext: TextView
    lateinit var phoneText: TextView
    lateinit var emailText: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       // val view =inflater.inflate(R.layout.fragment_profile, container, false)
        _binding =  FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        //mProfileViewModel=ViewModelProvider(this).get(ProfileViewModel::class.java)

        val mProfiledao = ProfileDatabase.getDatabase(requireContext()).profileDao()

        //mProfileViewModel.addProfile(profile)
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val profile = ProfileRepository(mProfiledao).getProfile(0)
                if(profile == null) {
                    val profilee=Profile(0,"Tornai Zsolt","Zetelaka","0758485835","zsoltika991001@gmail.com")
                    Log.d("zsolt", "null ")
                    setProfile(profilee)
                    ProfileRepository(mProfiledao).addProfile(profilee)
                }
                else {
                    Log.d("zsolt", "oksii ${profile.name}")
                    setProfile(profile)
                }

            }
            catch (e: Exception){
                Log.d("zsolti",e.toString())
            }
        }
        return view
    }

    private fun setProfile(profile: Profile) {
        nameText=binding.nameTextView
        addressTtext=binding.adressTextView
        phoneText=binding.phoneTextView
        emailText=binding.emailTextView
        activity?.runOnUiThread {
            nameText.text=profile.name
            addressTtext.text=profile.adress
            phoneText.text=profile.phone
            emailText.text=profile.email
        }
    }


}