package com.example.marvel_2.ui.characterlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.marvelheroes.ui.viewstate.ViewState
import com.example.marvel_2.CHARACTER_KEY
import com.example.marvel_2.R
import com.example.marvel_2.databinding.FragmentCharacterListBinding
import com.example.marvel_2.domain.model.Personagem
import com.example.marvel_2.ui.characterlist.viewmodel.CharacterViewModel
import com.example.marvel_2.ui.home.view.MainActivity

class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding

    private val characterAdapter: AdapterCharacter by lazy {
        AdapterCharacter(arrayListOf(), this::goToCharacterDetail)
    }
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.character_title_menu)

        viewModel.getAllCharacters()

        binding.rvHeroes.layoutManager = LinearLayoutManager(context)
        binding.rvHeroes.setHasFixedSize(true)

        showRecyclerView()
    }

    private fun showRecyclerView() {
        initObserver()
        binding.rvHeroes.adapter = characterAdapter
        binding.rvHeroes.layoutManager = LinearLayoutManager(context)
    }

    private fun goToCharacterDetail(char: Personagem) {
        val bundle = bundleOf(CHARACTER_KEY to char)

        NavHostFragment.findNavController(this)
            .navigate(R.id.action_characterListFragment_to_characterDetailFragment, bundle)

    }

    private fun initObserver() {
        viewModel.personagemListState.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    characterAdapter.refreshList(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

}