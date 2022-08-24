package com.example.notas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notas.databinding.FragmentNoteBinding
import com.example.notas.viewmodel.NotesViewModel

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val noteViewModel: NotesViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.currentNote.observe(viewLifecycleOwner, Observer {
                binding.tvNoteTitle.text = it.title
                binding.tvNoteBody.text = it.body
            })
        binding.btnEdit.setOnClickListener {
            val action = NoteFragmentDirections.actionNoteFragmentToEditNoteFragment()
            findNavController().navigate(action)
        }
    }
}