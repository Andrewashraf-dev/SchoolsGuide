package com.example.schoolsguide.schools.intern

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.daimajia.slider.library.Tricks.ViewPagerEx
import com.example.schoolsguide.R
import com.example.schoolsguide.databinding.AliAbnAbiTalebBinding
import com.example.schoolsguide.databinding.IsisBinding

import com.example.schoolsguide.databinding.StmaryBinding
import com.example.schoolsguide.tabAdapter.MyAdapter
import com.example.schoolsguide.tabAdapter.MyAdapter11
import com.example.schoolsguide.tabAdapter.MyAdapter6
import com.google.android.material.tabs.TabLayout

private lateinit var tabLayout: TabLayout
private lateinit var viewPager: ViewPager

private lateinit var binding: IsisBinding

class ISIS : AppCompatActivity() ,BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private val img = listOf(
        R.drawable.isi,
        R.drawable.isi1,
        R.drawable.isi2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = IsisBinding.inflate(layoutInflater)

        setContentView(binding.root)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Description"))
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"))
        tabLayout.addTab(tabLayout.newTab().setText("Location"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter11(
            this, supportFragmentManager,
            tabLayout.tabCount
        )
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        getSlider()
    }

    private fun getSlider() {
        for (item in 0 until img.count()) {

            val textSliderView = TextSliderView(this).apply {
                description("Photos")
                image(img[item])
                setOnSliderClickListener(this@ISIS)
                scaleType = BaseSliderView.ScaleType.FitCenterCrop
            }

            binding.slider.addSlider(textSliderView)
        }
    }

    override fun onStop() {
        binding.slider.stopAutoCycle()
        super.onStop()
    }

    override fun onSliderClick(slider: BaseSliderView?) {
        Toast.makeText(this, "Photos", Toast.LENGTH_SHORT).show()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {

    }

    fun fbClick(view: View) {
        startActivity(getOpenFacebookIntent());
    }

    private fun getOpenFacebookIntent(): Intent? {
        return try {
            packageManager.getPackageInfo("com.facebook.katana", 0)
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Isisschooll/?locale=ar_AR"))
        } catch (e: Exception) {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/Isisschooll/?locale=ar_AR")
            )
        }
    }
    fun Apply(view: View) {
        startActivity(getOpenformIntent());
    }

    private fun getOpenformIntent(): Intent? {
        return try {
            packageManager.getPackageInfo("com.facebook.katana", 0)
            Intent(Intent.ACTION_VIEW, Uri.parse("https://form.jotform.com/231373096171555"))
        } catch (e: Exception) {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://form.jotform.com/231373096171555")
            )
        }
    }
}