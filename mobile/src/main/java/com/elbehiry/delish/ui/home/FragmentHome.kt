package com.elbehiry.delish.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elbehiry.delish.ui.graph.NavGraph
import com.elbehiry.delish.ui.main.MainContent
import com.elbehiry.delish.ui.main.MainViewModel
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class FragmentHome : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("VisibleForTests")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {

            setContent {
                DelishComposeTheme {
                    ProvideWindowInsets {
                        MainContent(
                            viewModel = mainViewModel,
                        ){}
                        NavGraph(mainViewModel)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getRandomRecipes()
    }
}