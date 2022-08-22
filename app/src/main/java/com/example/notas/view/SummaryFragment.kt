package com.example.notas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notas.R
import com.example.notas.databinding.FragmentSummaryBinding
import com.example.notas.model.Note
import com.example.notas.model.NotesDB
import com.example.notas.recyclerview.NoteRecyclerViewAdapter
import com.example.notas.viewmodel.NotesViewModel

class SummaryFragment() : Fragment() {

    private var mutableNoteList: MutableList<Note> = NotesDB.notes.toMutableList()
    private val noteViewModel: NotesViewModel by activityViewModels()
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
        binding.btnAdd.setOnClickListener {
            onItemAdded((1..mutableNoteList.size).random())
        }
        initRecyclerView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        llmanager = LinearLayoutManager(this.context)
        adapter = NoteRecyclerViewAdapter(
            mutableNoteList, //todo utilizar livedata con el recycler view
            { position -> onItemSelected(position) },
            { position -> onItemDeleted(position) }
        )
        binding.rvNotes.layoutManager = llmanager
        binding.rvNotes.adapter = adapter
    }

    //todo Dónde colocar estas funciones?
    private fun onItemSelected(position: Int) {
        noteViewModel.setCurrent(position)
        val action = SummaryFragmentDirections.actionSummaryFragmentToNoteFragment()
        findNavController().navigate(action)
    }

    private fun onItemDeleted(position: Int) {
        mutableNoteList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun onItemAdded(position: Int) {
        mutableNoteList.add(position, Note("Nueva Nota", "Soy muy importante"))
        adapter.notifyItemInserted(position)
        llmanager.scrollToPosition(position)
    }

}