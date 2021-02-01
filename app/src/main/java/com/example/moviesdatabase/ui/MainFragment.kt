package com.example.moviesdatabase.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesdatabase.R
import com.example.moviesdatabase.databinding.MainFragmentBinding
import com.example.moviesdatabase.model.MovieList
import com.example.moviesdatabase.network.ApiHelper
import com.example.moviesdatabase.network.RetrofitBuilder
import com.example.moviesdatabase.utils.Status

class MainFragment : Fragment(), MovieAdapter.MovieItemListener {
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(this, arrayListOf())
        binding.movieList.layoutManager = GridLayoutManager(activity, 2)
        binding.movieList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { users -> retrieveList(users) }

                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        Log.d("BENJAMIN", "benjamimn" + it.message)
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.movieApiService))
        ).get(MainViewModel::class.java)
    }

    private fun retrieveList(users: MovieList) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    override fun onClickedMovie(movieId: Int) {
        findNavController().navigate(
                R.id.action_mainFragment_to_detailsFragment
        )
    }
}