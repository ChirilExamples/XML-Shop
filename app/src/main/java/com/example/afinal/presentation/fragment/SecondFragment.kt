package com.example.afinal.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.afinal.data.data_structure.ClothesItem
import com.example.afinal.databinding.FragmentSecondBinding
import com.example.afinal.presentation.vm.DetailsViewModel
import com.example.afinal.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: DetailsViewModel by viewModels()

    //   This property is only valid between onCreateView andon DestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.get("ID").let {
            viewModel.startDetailsCall(it as Int)
        }
        startDetailsObserversId()
    }

    private fun bindDetailsDetailed(result: ClothesItem) {
        binding.textViewDetailsName.text = result.title
        binding.textViewDetailsCategory.text = result.category
        binding.textViewDetailsPrice.text = result.price.toString()
        binding.textViewDetailsDescription.text = result.description

        Glide.with(binding.root).load(result.image).into(binding.imageViewDetails)
    }

    private fun startDetailsObserversId() {
        viewModel.clothes.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    bindDetailsDetailed(it.data!!)
                }

                Resource.Status.ERROR -> {
                    Log.i("Error", it.message.toString())
                }

                Resource.Status.LOADING -> {
                    //  progress dialog placeholder, progress too quick
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
