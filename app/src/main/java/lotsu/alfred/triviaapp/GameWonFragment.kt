package lotsu.alfred.triviaapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import lotsu.alfred.triviaapp.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }

//        Toast.makeText(context, "Correct Answers: ${args.numCorrect} Questions: ${args.numQuestions}", Toast.LENGTH_SHORT).show()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.winner_menu, menu)
                if (null == getShareIntent().resolveActivity(activity!!.packageManager)){
                    menu.findItem(R.id.share).isVisible = false
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                NavigationUI.onNavDestinationSelected(menuItem, view!!.findNavController())
                // Handle the menu selection
                when (menuItem.itemId) {
                    R.id.share -> shareSuccess()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
                return binding.root
    }

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,
            getString(R.string.share_success_text,args.numCorrect, args.numQuestions))
         return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }



 }