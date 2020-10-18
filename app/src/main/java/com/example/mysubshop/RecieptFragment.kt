package com.example.mysubshop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mysubshop.databinding.FragmentRecieptBinding

/**
 * The receipt fragment for displaying the receipt after ordering.
 */
class RecieptFragment : Fragment() {

    /**
     * Inflating and Returning the View with DataBindingUtil
     * gets the arguments returned from the order fragment and puts them into the layout.
     *
     * @param LayoutInflater is used for reading xml and using the layout
     * @param ViewGroup a container used for containing other views
     * @param Bundle used for passing data between activities
     * @return view as binding.root
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRecieptBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reciept, container, false)

        val args = RecieptFragmentArgs.fromBundle(arguments!!)
        //Toast.makeText(context, "TotalCost: ${args.sandwich.totalCost}",  Toast.LENGTH_LONG).show()
        binding.sandwich = args.sandwich

        val listView: ListView = binding.toppings
        listView.adapter = ArrayAdapter<String>(
            activity as Context,
            android.R.layout.simple_list_item_1, args.sandwich.toppings
        )

        return binding.root
    }
}