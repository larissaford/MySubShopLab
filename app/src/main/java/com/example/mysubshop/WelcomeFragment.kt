package com.example.mysubshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.mysubshop.databinding.FragmentWelcomeBinding
import timber.log.Timber

/**
 * a welcome page for the subpage. has a button to navigate to the order page.
 *
 * uses binding for more efficient running, makes a listener for the button, and
 * sets option menu to true. inflates the layout for this fragment
 *
 *
 * @param LayoutInflater is used for reading xml and using the layout
 * @param ViewGroup a container used for containing other views
 * @param Bundle used for passing data between activities
 * @return View
 */
class WelcomeFragment : Fragment() {

        /**
     * Inflating and Returning the View with DataBindingUtil
     * sets the listener for going to the order fragment page.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        //The complete onClickListener with Navigation using createNavigateOnClickListener
        binding.orderNowButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_orderFragment))


        return binding.root
    }
}