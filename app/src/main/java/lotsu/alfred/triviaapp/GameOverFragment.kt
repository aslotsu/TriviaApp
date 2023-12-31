package lotsu.alfred.triviaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import lotsu.alfred.triviaapp.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_over, container, false)
        binding.tryAgainButton.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment)
        }
        return binding.root
    }

}