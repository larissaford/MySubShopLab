package com.example.mysubshop

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.mysubshop.databinding.ActivityMainBinding
import com.example.mysubshop.databinding.FragmentOrderBinding
import timber.log.Timber

/**
 * The Sub Shop main activity. A interface where you enter the store, order a specific sub, and get
 * a receipt with that sub.
 *
 * @property appBarConfiguration sets up the app bar
 */
class MainActivity : AppCompatActivity(), OrderFragment.OnOrderSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    //private lateinit var callback: OrderFragment.OnOrderSelectedListener
    private lateinit var subShopTimer: SubShopTimer

    /**
     * sets up the app with navigation and app bar configuration.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subShopTimer = SubShopTimer(this.lifecycle)

        Timber.i("onCreate called")

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    /**
     * sets up the nav Controller and the back button.
     * @return Boolean
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        //return NavigationUI.navigateUp(drawerLayout, navController)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


    /*
    /**
     * hypothetically connects order fragment and main activity
     *
     * @param fragment is the order fragment
     */
    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is OrderFragment)
            fragment.setOnOrderSelectedListener(this)
    }
     */

    /**
     * redirects the listener to the Order fragment where the function actually is
     *
     * @param view is the checkbox clicked
     */
    override fun onAdditionsAddedCheckboxClicked(view: View) {
        val orderFragment= getOrderFragment()
        orderFragment?.onAdditionsAddedCheckboxClicked(view, orderFragment.binding)


    }


    /**
     * redirects the listener to the Order fragment where the function actually is
     *
     * @param view the radio button clicked
     */
    override fun onSandwichRadioButtonClicked(view: View) {
        val orderFragment = getOrderFragment()
        orderFragment?.onSandwichRadioButtonClicked(view,orderFragment.binding)
    }

    /**
     * redirects the listener to the Order fragment where the function actually is
     *
     * @param view the radio button clicked
     * @return Int a value returned for unit testing
     */
    override fun onBreadRadioButtonClicked(view: View) {
        val orderFragment = getOrderFragment()
        orderFragment?.onBreadRadioButtonClicked(view)
    }

    /**
     * gets the order fragment from the navigation fragment's children
     *
     * QUESTION: see context options
     *
     * @return the order fragment for use in redirection.
     */
    fun getOrderFragment(): OrderFragment?{
        val navFrag = supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        return navFrag?.childFragmentManager?.fragments?.get(0) as OrderFragment?
    }

    /**
     * starting the program
     */
    override fun onStart() {
        super.onStart()
        Timber.i("onStart Called")
    }

    /**
     * resuming the program
     */
    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    /**
     * pausing the program
     */
    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    /**
     * stopping the program
     */
    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    /**
     * destroying the program
     */
    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }

    /**
     * restarting the program
     */
    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart Called")
    }
}
