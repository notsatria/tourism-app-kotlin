package com.notsatria.tourismapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        val btnGetStarted:Button = findViewById(R.id.btn_get_started)

        btnGetStarted.setOnClickListener {
            val intent = Intent(this@GetStartedActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}