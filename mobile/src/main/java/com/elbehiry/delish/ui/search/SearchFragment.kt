package com.elbehiry.delish.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchRecipesViewModel by viewModels()
    private val searchArgs: SearchFragmentArgs by navArgs()

    @ExperimentalAnimationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                DelishComposeTheme {
                    SearchScreen(
                        searchViewModel,
                        findNavController(),
                        searchArgs.query, searchArgs.type
                    ) { recipeId ->
                        val action = SearchFragmentDirections.goToRecipesDetails(recipeId ?: 0)
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }
}