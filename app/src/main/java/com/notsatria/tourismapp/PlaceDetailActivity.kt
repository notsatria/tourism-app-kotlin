package com.notsatria.tourismapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.notsatria.tourismapp.models.Place

class PlaceDetailActivity : AppCompatActivity() {
    companion object {
        var EXTRA_PLACE: String? = "extra_place"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        val placeDetailPhoto: ImageView = findViewById(R.id.iv_place_detail_photo)
        val placeDetailName: TextView = findViewById(R.id.tv_place_detail_name)
        val placeDetailCountry: TextView = findViewById(R.id.tv_place_detail_country)
        val placeDetailRating: TextView = findViewById(R.id.tv_place_detail_rating)
        val placeDetailDescription: TextView = findViewById(R.id.tv_place_detail_description)
        val firstDetailDescriptionImage: ImageView = findViewById(R.id.iv_place_desc_photo_1)
        val secondDetailDescriptionImage: ImageView = findViewById(R.id.iv_place_desc_photo_2)

        val ivShare: ImageView = findViewById(R.id.iv_share)

        val place = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Place>(EXTRA_PLACE, Place::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Place>(EXTRA_PLACE)
        }

        if (place != null) {
            placeDetailPhoto.setImageResource(place.image)
            placeDetailName.text = place.name
            placeDetailCountry.text = place.country
            placeDetailRating.text = place.rating
            placeDetailDescription.text = place.description
            firstDetailDescriptionImage.setImageResource(place.image)
            secondDetailDescriptionImage.setImageResource(place.image)

            ivShare.setOnClickListener {
                sharePlace(place)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun sharePlace(place: Place) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Cek tempat ini: ${place.name} in ${place.country}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}