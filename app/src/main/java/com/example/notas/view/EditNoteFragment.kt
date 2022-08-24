package com.example.notas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notas.databinding.FragmentEditNoteBinding
import com.example.notas.model.Note
import com.example.notas.viewmodel.NotesViewModel

class EditNoteFragment : Fragment() {
    private val noteViewModel: NotesViewModel by activityViewModels()
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDone.setOnClickListener {
            noteViewModel.currentNote.observe(viewLifecycleOwner, Observer {
                saveChanges(it)
            })
            val action = EditNoteFragmentDirections.actionEditNoteFragmentToNoteFragment()
            findNavController().navigate(action)
        }
        noteViewModel.currentNote.observe(viewLifecycleOwner, Observer {
            renderNote(it)
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun renderNote(note: Note) {
        binding.edNoteTitle.setText(note.title)
        binding.edNoteBody.setText(note.body)
    }

    private fun saveChanges(note: Note) {
        note.title = binding.edNoteTitle.text.toString()
        note.body = binding.edNoteBody.text.toString()
        if (!noteViewModel.isStored(note))
            noteViewModel.addNote(note)
    }
}