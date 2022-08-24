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
import com.example.notas.databinding.FragmentSummaryBinding
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
        binding.btnDone.setOnClickListener{
            noteViewModel.currentNote.observe(viewLifecycleOwner, Observer {
                it.title = binding.edNoteTitle.text.toString()
                it.body = binding.edNoteBody.text.toString()
            })
            val action = EditNoteFragmentDirections.actionEditNoteFragmentToNoteFragment()
            findNavController().navigate(action)
        }
        noteViewModel.currentNote.observe(viewLifecycleOwner, Observer {
            binding.edNoteTitle.setText(it.title)
            binding.edNoteBody.setText(it.body)
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}