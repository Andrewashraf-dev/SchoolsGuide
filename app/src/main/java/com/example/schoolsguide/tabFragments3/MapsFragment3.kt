package com.example.schoolsguide.tabFragments

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

class MapsFragment3 : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val MaadiGadida = LatLng(29.9578205, 31.2589186)
        googleMap.addMarker(MarkerOptions().position(MaadiGadida).title("El Maadi El Gadida Preparatory School"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MaadiGadida,16.0f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}