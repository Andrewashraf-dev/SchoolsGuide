package com.example.schoolsguide

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AboutUsActivity : AppCompatActivity() {
    @SuppressLint("StringFormatInvalid", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        // Set up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up the views
        val appNameTextView = findViewById<TextView>(R.id.app_name_text_view)
        val versionTextView = findViewById<TextView>(R.id.version_text_view)
        val developerTextView = findViewById<TextView>(R.id.developer_text_view)

        // Set the app name and version
        val appInfo = packageManager.getPackageInfo(packageName, 0)
        appNameTextView.text = getString(R.string.app_name)
        versionTextView.text = getString(R.string.version, appInfo.versionName)

        // Set the developer information
        developerTextView.text = getString(R.string.developer_info)

        val emailButton = findViewById<Button>(R.id.email_button)
        emailButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:Thisisandrwashrf@gmail.com") // Replace with your email address
                putExtra(Intent.EXTRA_SUBJECT, "Regarding My App") // Add a default subject line
                putExtra(Intent.EXTRA_TEXT, "Dear Support Team,") // Add a default message body
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No email app found on your device", Toast.LENGTH_SHORT).show()
            }
        }

        val termsButton = findViewById<Button>(R.id.terms)
        termsButton.setOnClickListener {
            val intent = Intent(this, terms::class.java)
            startActivity(intent)
        }


        val logoImage = findViewById<ImageView>(R.id.logo_image)
        logoImage.setOnClickListener {
            val facebookUrl = "https://www.facebook.com/andrew.ashraf.18488" // Replace with your Facebook page URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // If the Facebook app is not installed, open the Facebook website in a browser
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl))
                startActivity(browserIntent)
            }
        }

        val igimage = findViewById<ImageView>(R.id.igimage)
        igimage.setOnClickListener {
            val instagramUrl = "https://www.instagram.com/iamandrewashraf" // Replace with your Instagram page URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/_u/iamandrewashraf"))
            intent.setPackage("com.instagram.android")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // If the Instagram app is not installed, open the Instagram website in a browser
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))
                startActivity(browserIntent)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}