package com.example.ricknmortyquiz.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ricknmortyquiz.R
import com.example.ricknmortyquiz.databinding.ActivityMainBinding
import com.example.ricknmortyquiz.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<TitleFragmentBinding>(
            inflater,
            R.layout.title_fragment, container, false
        )
        return binding.root
    }

}