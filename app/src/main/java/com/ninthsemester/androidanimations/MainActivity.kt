package com.ninthsemester.androidanimations

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {

            R.id.menu_sample_window -> {
                Toast.makeText(this, "Clicked Sample Window", Toast.LENGTH_SHORT).show()
                openWindow(SampleAnimActivity::class.java)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun <T : AppCompatActivity>openWindow(classType : Class<T>) {

        val intent = Intent(this, classType)
        startActivity(intent)

    }
}
