package com.example.notas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.notas.databinding.FragmentNoteBinding
import com.example.notas.viewmodel.NotesViewModel

class NoteFragment(private val noteViewModel: NotesViewModel) : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.noteModel.observe(viewLifecycleOwner, Observer {
            binding.tvNoteTitle.text = it!!.title
            binding.tvNoteBody.text = it.body
        })
        noteViewModel.getNote()
    }

}