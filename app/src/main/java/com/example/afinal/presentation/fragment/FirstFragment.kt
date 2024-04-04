package com.example.afinal.presentation.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.R
import com.example.afinal.databinding.FragmentFirstBinding
import com.example.afinal.presentation.adapter.RecyclerAdapter
import com.example.afinal.presentation.vm.ShoppingListViewModel
import com.example.afinal.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: ShoppingListViewModel by viewModels()
    private lateinit var adapter: RecyclerAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserversMain(2.0)


        adapter = RecyclerAdapter(
            RecyclerAdapter.OnClickListener {

                val bundle = Bundle()
                bundle.putInt("ID", it.id)

                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        )

        _binding?.recyclerView?.adapter = adapter
        _binding?.recyclerView?.layoutManager = LinearLayoutManager(activity)

        _binding?.chipCatAll?.setOnClickListener {
            startObserversMain(2.5)
        }
        _binding?.chipCatWom?.setOnClickListener {
            startObserversSortCatWomen()
        }
        _binding?.chipCatMen?.setOnClickListener {
            startObserversSortCatMen()
        }
        _binding?.chipCatJew?.setOnClickListener {
            startObserversSortCatJew()
        }
        _binding?.chipCatTech?.setOnClickListener {
            startObserversSortCatTec()
        }

        _binding?.chipPrices10?.setOnClickListener {
            startObserversSortPrice10()
        }
        _binding?.chipPrices25?.setOnClickListener {
            startObserversSortPrice25()
        }
        _binding?.chipPrices50?.setOnClickListener {
            startObserversSortPrice50()

        }
        _binding?.chipPrices100?.setOnClickListener {
            startObserversSortPrice100()
        }
    }

    private fun startObserversMain(time: Double) {
        viewModel.repositoryList.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(time)
                }
            }
        }
    }

    private fun startObserversSortPrice10() {
        viewModel.p10.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }
            }
        }
    }

    private fun startObserversSortPrice25() {
        viewModel.p25.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }
            }
        }
    }

    private fun startObserversSortPrice50() {
        viewModel.p50.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }
            }
        }
    }

    private fun startObserversSortPrice100() {
        viewModel.p100.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }
            }
        }
    }

    private fun startObserversSortCatMen() {
        viewModel.men.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }

            }
        }
    }

    private fun startObserversSortCatWomen() {
        viewModel.women.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }

            }
        }
    }

    private fun startObserversSortCatJew() {
        viewModel.jewelery.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }

            }
        }
    }

    private fun startObserversSortCatTec() {
        viewModel.electronics.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    adapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                    Log.i("contextTry", it.message.toString())
                }

                Resource.Status.LOADING -> {
                    progressDialog(1.0)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun progressDialog(time: Double) {
        val progDial = ProgressDialog(activity)
        progDial.setMessage("Loading...")
        progDial.setCancelable(false)
        progDial.show()

        Handler().postDelayed({ progDial.dismiss() }, (time * 1000).toLong())
    }
}
