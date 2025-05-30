package com.example.schoolsguide.tabFragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schoolsguide.R
import com.example.schoolsguide.schools.HafezIbrahim

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment2 : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val HafezIbrahim = LatLng(30.0820427, 31.2429107)
        googleMap.addMarker(MarkerOptions().position(HafezIbrahim).title("Hafez Ibrahim School"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HafezIbrahim,16.0f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}