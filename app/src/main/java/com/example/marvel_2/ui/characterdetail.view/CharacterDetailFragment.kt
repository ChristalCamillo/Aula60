package com.example.marvel_2.ui.characterdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvel_2.CHARACTER_KEY
import com.example.marvel_2.R
import com.example.marvel_2.databinding.FragmentCharacterDetailBinding
import com.example.marvel_2.domain.model.Personagem
import com.example.marvel_2.ui.home.view.MainActivity

class CharacterDetailFragment(
) : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.character_detail_title_menu)

        getCharacterDetails()

    }

    private fun getCharacterDetails() {
        val char = arguments?.getParcelable<Personagem>(CHARACTER_KEY)

        char?.let {
            binding.ivDetalhePersonagem.setImageResource(it.characterPicture)
            binding.tvDetalheNomePersonagem.text = it.characterName
            binding.tvDetalheDescricaoPersonagem.text = it.characterDescripion
            (activity as MainActivity).supportActionBar?.title = it.characterName
        }

    }

}