package com.notsatria.tourismapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notsatria.tourismapp.models.Place
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlace: RecyclerView
    private var list: ArrayList<Place> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val civAvatar: CircleImageView = findViewById(R.id.civ_avatar)

        civAvatar.setOnClickListener {
            val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
        }

        rvPlace = findViewById(R.id.rv_place)
        rvPlace.setHasFixedSize(true)

        list.addAll(placeList)
        showRecyclerList()
    }

    private val placeList: ArrayList<Place>
        get() {
            val dataName = resources.getStringArray(R.array.data_place_name)
            val dataCountry = resources.getStringArray(R.array.data_place_country)
            val dataRating = resources.getStringArray(R.array.data_place_rating)
            val dataImage = resources.obtainTypedArray(R.array.data_place_image)
            val dataDescription = resources.getStringArray(R.array.data_place_description)
            val listPlace = ArrayList<Place>()
            for (i in dataName.indices) {
                val place = Place(dataImage.getResourceId(i, 0), dataName[i], dataCountry[i], dataRating[i], dataDescription[i])
                listPlace.add(place)
            }
            return listPlace
        }

    private fun showRecyclerList() {
        rvPlace.layoutManager = LinearLayoutManager(this)
        val listPlaceAdapter = ListPlaceAdapter(list)
        rvPlace.adapter = listPlaceAdapter

        listPlaceAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Place) {
                moveToDetailPlaceActivity(data)
            }
        })
    }

    private fun moveToDetailPlaceActivity(place: Place) {
        val detailPlaceIntent = Intent(this@MainActivity, PlaceDetailActivity::class.java)
        detailPlaceIntent.putExtra(PlaceDetailActivity.EXTRA_PLACE, place)
        startActivity(detailPlaceIntent)
    }

    private fun showSelectedPlace(place: Place) {
        Toast.makeText(this, "Kamu memilih " + place.name, Toast.LENGTH_SHORT).show()
    }
}