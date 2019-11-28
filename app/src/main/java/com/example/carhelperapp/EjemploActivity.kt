package com.example.carhelperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import java.util.*

class EjemploActivity : AppCompatActivity() {

    private lateinit var mRunnable:Runnable
    private lateinit var mHandler: Handler
    private lateinit var mRandom: Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejemplo)

        mRandom = Random()
        mHandler = Handler()

        mRunnable = Runnable {
            // Do something here
            Toast.makeText(this,"Random Number : ${mRandom.nextInt(100)}",Toast.LENGTH_LONG).show()

            // Schedule the task to repeat after 1 second
            mHandler.postDelayed(
                mRunnable, // Runnable
                5000 // Delay in milliseconds
            )

        }
        mHandler.postDelayed(
            mRunnable, // Runnable
            5000 // Delay in milliseconds
        )

    }

}
