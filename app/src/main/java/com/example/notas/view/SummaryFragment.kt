package com.example.notas.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notas.R
import com.example.notas.databinding.FragmentSummaryBinding
import com.example.notas.model.Note
import com.example.notas.model.NotesDB
import com.example.notas.recyclerview.NoteRecyclerViewAdapter
import com.example.notas.utils.ApplicationStates
import com.example.notas.viewmodel.NotesViewModel
import com.example.notas.viewmodel.NotesViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class SummaryFragment() : Fragment() {

    private val noteViewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(this.context)
    }
    private lateinit var llmanager: LinearLayoutManager
    private lateinit var adapter: NoteRecyclerViewAdapter
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApplicationStates.Loading -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    lifecycleScope.launch(Dispatchers.Main) {
                        withContext(Dispatchers.IO) { noteViewModel.setNotes() }
                    }
                }
                is ApplicationStates.Success -> {
                    initRecyclerView()
                    binding.btnAdd.setOnClickListener {
                        onItemAdded()
                    }
                    binding.loadingSpinner.visibility = View.GONE
                }
                is ApplicationStates.Error -> print("error")
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onStop() {
        noteViewModel.save()
        super.onStop()
    }

    private fun initRecyclerView() {
        noteViewModel.allNotes.observe(viewLifecycleOwner, Observer { allNotes ->
            llmanager = LinearLayoutManager(this.context)
            adapter = NoteRecyclerViewAdapter(
                allNotes,
                { position -> onItemSelected(position) },
                { position -> onItemDeleted(position) }
            )
            binding.rvNotes.layoutManager = llmanager
            binding.rvNotes.adapter = adapter
        })
    }

    //todo Dónde colocar estas funciones?
    private fun onItemSelected(index: Int) {
        noteViewModel.setCurrentNote(index)
        val action = SummaryFragmentDirections.actionSummaryFragmentToNoteFragment()
        findNavController().navigate(action)
    }

    private fun onItemDeleted(index: Int) {
        noteViewModel.removeNoteAt(index)
        adapter.notifyItemRemoved(index)
    }

    private fun onItemAdded() {
        noteViewModel.setCurrentEmptyNote()
        val action = SummaryFragmentDirections.actionSummaryFragmentToEditNoteFragment()
        findNavController().navigate(action)
    }

}