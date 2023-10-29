package lotsu.alfred.triviaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
 import androidx.navigation.ui.NavigationUI
import lotsu.alfred.triviaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        drawerLayout = binding.drawerLayout
//         val toggle = ActionBarDrawerToggle(this, drawerLayout, binding.toolbar,R.string.share_success_text, R.string.share)
//         toggle.syncState()
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.triviaAppNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
         NavigationUI.setupWithNavController(binding.navView, navController)
         navController.addOnDestinationChangedListener {nc: NavController, nd: NavDestination, _: Bundle? ->
             if (nd.id == nc.graph.startDestinationId){
                 drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
             }else {
                 drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
             }
         }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.triviaAppNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}