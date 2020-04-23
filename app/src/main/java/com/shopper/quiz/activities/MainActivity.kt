package com.shopper.quiz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shopper.quiz.MainApplication
import com.shopper.quiz.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(MainApplication.appContext, "Some", Toast.LENGTH_LONG).show()
    }
}
