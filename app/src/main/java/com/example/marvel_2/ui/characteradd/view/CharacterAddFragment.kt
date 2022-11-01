package com.example.marvel_2.ui.characteradd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.marvelheroes.ui.viewstate.ViewState
import com.example.marvel_2.R
import com.example.marvel_2.databinding.FragmentCharacterAddBinding
import com.example.marvel_2.ui.characteradd.viewmodel.CharacterAddViewModel
import com.example.marvel_2.ui.home.view.MainActivity

class CharacterAddFragment : Fragment() {
    private lateinit var binding: FragmentCharacterAddBinding
    private val viewModel: CharacterAddViewModel by lazy {
        ViewModelProvider(this)[CharacterAddViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.main_title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            viewModel.verifyCharacter(
                name = binding.etCharacterName.text.toString(),
                description = binding.etCharacterDescription.text.toString(),
            )
            goToCharactersList()
        }
        initObserver()
    }

    private fun goToCharactersList() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_characterAddFragment_to_characterListFragment)
    }

    private fun initObserver() {
        viewModel.personagemAddState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    cleanEditText()
                    Toast.makeText(context, "Filme cadastrado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun cleanEditText() {
        binding.etCharacterName.text.clear()
        binding.etCharacterDescription.text.clear()
        binding.etImageHere.text.clear()
    }

}