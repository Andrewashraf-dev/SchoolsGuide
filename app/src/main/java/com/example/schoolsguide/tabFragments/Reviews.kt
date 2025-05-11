package com.example.schoolsguide.tabFragments

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.schoolsguide.R
import com.example.schoolsguide.databinding.DialogCommentAddBinding
import com.example.schoolsguide.databinding.FragmentReviewsBinding
import com.google.firebase.auth.FirebaseAuth

class Reviews : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false)

    }
}