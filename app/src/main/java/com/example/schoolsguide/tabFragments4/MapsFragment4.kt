package com.example.schoolsguide.tabFragments4

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schoolsguide.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment4 : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val MrsKhadija = LatLng(29.9700099, 31.3220693)
        googleMap.addMarker(MarkerOptions().position(MrsKhadija).title("El Sayeda Khadija Language School"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MrsKhadija,16.0f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}