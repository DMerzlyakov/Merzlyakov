package com.example.merzlyakov.presentation.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merzlyakov.databinding.KinopoiskFragmentBinding
import com.example.merzlyakov.presentation.adapters.FilmsListAdapter
import com.example.merzlyakov.presentation.dialogs.DialogUtil
import com.example.merzlyakov.presentation.view_models.KinopoiskViewModel

class KinopoiskFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = KinopoiskFragment()
    }

    private val viewModel: KinopoiskViewModel by activityViewModels()
    private lateinit var binding: KinopoiskFragmentBinding
    private lateinit var adapter: FilmsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = KinopoiskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecycleView()
        loadFilms()

    }

    private fun createRecycleView() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = FilmsListAdapter()
        recyclerView.adapter = adapter

        viewModel.liveFilmsList.observe(viewLifecycleOwner) {
            if (progressBar.isVisible){
                progressBar.visibility =ProgressBar.INVISIBLE
            }
            adapter.submitList(it.subList(0, it.size))
        }

        setFilmItemClickListeners(adapter)


    }

    private fun loadFilms() {
        viewModel.loadData()
        viewModel.liveError.observe(viewLifecycleOwner) {
            DialogUtil.errorDialog(requireContext(), it)
        }
    }

    private fun setFilmItemClickListeners(adapter: FilmsListAdapter) {
        adapter.onFilmItemClickListener = {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom,
                    androidx.appcompat.R.anim.abc_shrink_fade_out_from_bottom,
                    androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom,
                    androidx.appcompat.R.anim.abc_shrink_fade_out_from_bottom,
                )
                .replace(this@KinopoiskFragment.id, FilmDetailFragment.newInstance(it.id))
                .addToBackStack(null)
                .commit()
        }

        adapter.onFilmItemLongClickListener = {
            Log.d("LONG_CLICK_ITEM", "Долгое нажатие на ${it.title}")
        }
    }

}