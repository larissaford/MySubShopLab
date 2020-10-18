package com.example.mysubshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysubshop.databinding.FragmentOrderBinding
import timber.log.Timber

const val KEY_SANDWICH = "key_sandwich"

/**
 * the order page for ordering a sandwich with options for bread type, sandwich type, and
 * additional toppings.
 *
 * @property sandwich the sandwich being made
 * @property callback hypothetically for attaching main activity and order fragment
 * @property binding more efficiently call things from the layout.
 *
 */
class OrderFragment : Fragment() {

    private var sandwich: Sandwich = Sandwich(
        "click a sandwich",
        "0.0",
        0.0f,
        "0.0",
        0.0f,
        "none",
        ArrayList()
    )

    //outside of the oncreate to be used in the functions.
    lateinit var binding: FragmentOrderBinding

    /*
    private lateinit var callback: OnOrderSelectedListener

    /**
     * hypothetically for attaching main activity and fragment
     *
     */
    fun setOnOrderSelectedListener(callback: OnOrderSelectedListener) {
        this.callback = callback
    }
     */

    //hypothetically used for connecting order fragment and main activity
    interface OnOrderSelectedListener {
        fun onSandwichRadioButtonClicked(view: View)
        fun onBreadRadioButtonClicked(view: View)
        fun onAdditionsAddedCheckboxClicked(view: View)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Restore the fragment's state here
        if (savedInstanceState != null) sandwich = savedInstanceState.getParcelable<Sandwich>(KEY_SANDWICH) as Sandwich
        binding.sandwichData = sandwich

        when (sandwich.name) {
            "Hoagie" -> binding.hoagie.visibility = View.VISIBLE
            "Panini" -> binding.panini.visibility = View.VISIBLE
            else -> binding.sandwich.visibility = View.VISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState called")
        //save fragment state here
        outState.putParcelable(KEY_SANDWICH, this.sandwich)
    }



    /**
     * inflates the layout for the fragment. connects the sandwich to the layout and all of the
     * buttons for making the sandwich.
     *
     * @param LayoutInflater is used for reading xml and using the layout
     * @param ViewGroup a container used for containing other views
     * @param Bundle used for passing data between activities
     * @return View
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentOrderBinding>(
            inflater, R.layout.fragment_order, container, false
        )
        binding.sandwichData = sandwich
        binding.apply {
            panini.visibility = View.GONE
            hoagie.visibility = View.GONE
            sandwich.visibility = View.GONE
        }
        binding.submitOrderButton.setOnClickListener { view: View ->
            binding.invalidateAll()

            when (true) {
                sandwich.toppings.isEmpty() -> sandwich.toppings.add("no additional toppings")
                sandwich.breadType == "none" ->
                    Toast.makeText(context, "No bread was chosen. Please choose a bread type.", Toast.LENGTH_SHORT).show()
                sandwich.name == "click a sandwich" ->
                    Toast.makeText(context, "No sandwich was chosen. Please choose a sandwich", Toast.LENGTH_SHORT).show()
                else -> view.findNavController().navigate(
                    OrderFragmentDirections.actionOrderFragmentToRecieptFragment(sandwich)
                )
            }
            // Bind this fragment class to the layout
            //call on setOnClickListener {put the functions for checkboxes and radio buttons here if things break when doing it through the xml}
            //binding.game = this
        }

        return binding.root
    }

    /**
     * handles the checkboxes for the additional toppings for the ordered sandwich.
     *
     * @param view the checkbox view
     * @param binding the binding for the order fragment to use the layout views
     */
    fun onAdditionsAddedCheckboxClicked(view: View, binding: FragmentOrderBinding) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            sandwich.toppings.remove("no additional toppings")

            when (view.id) {
                R.id.lettuce_check ->
                    if (checked) {
                        sandwich.extraCost += 0.5f
                        sandwich.toppings.add("Lettuce")
                    } else {
                        sandwich.extraCost -= 0.5f
                        sandwich.toppings.remove("Lettuce")
                    }
                R.id.bacon_check ->
                    if (checked) {
                        sandwich.extraCost += 0.5f
                        sandwich.toppings.add("Bacon")
                    } else {
                        sandwich.extraCost -= 0.5f
                        sandwich.toppings.remove("Bacon")
                    }
                R.id.tomatoes_check ->
                    if (checked) {
                        sandwich.extraCost += 0.5f
                        sandwich.toppings.add("Tomatoes")
                    } else {
                        sandwich.extraCost -= 0.5f
                        sandwich.toppings.remove("Tomatoes")
                    }
                R.id.mayo_check ->
                    if (checked) {
                        sandwich.extraCost += 0.5f
                        sandwich.toppings.add("Mayo")
                    } else {
                        sandwich.extraCost -= 0.5f
                        sandwich.toppings.remove("Mayo")
                    }
            }
        }
        sandwich.totalCost = (sandwich.extraCost + sandwich.sandwichCost).toString()
        binding.invalidateAll()
        Toast.makeText(
            activity, "Total Cost :" +
                    " ${sandwich.totalCost}",
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * handles the radio buttons for choosing the sandwich
     *
     * @param view the radiobutton view
     * @param binding for the order fragment to use the layout
     */
    fun onSandwichRadioButtonClicked(view: View, binding: FragmentOrderBinding) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.panini_button ->
                    if (checked) {
                        sandwich.sandwichCost = 7.0f
                        sandwich.sandwichCostText = "7.0"
                        sandwich.name = "Panini"
                        binding.panini.visibility = View.VISIBLE
                        binding.hoagie.visibility = View.GONE
                        binding.sandwich.visibility = View.GONE
                    }
                R.id.hoagie_button ->
                    if (checked) {
                        sandwich.sandwichCost = 10.0f
                        sandwich.sandwichCostText = "10.0"
                        sandwich.name = "Hoagie"
                        binding.panini.visibility = View.GONE
                        binding.hoagie.visibility = View.VISIBLE
                        binding.sandwich.visibility = View.GONE
                    }
                R.id.sandwich_button ->
                    if (checked) {
                        sandwich.sandwichCost = 5.0f
                        sandwich.sandwichCostText = "5.0"
                        sandwich.name = "Sandwich"
                        binding.panini.visibility = View.GONE
                        binding.hoagie.visibility = View.GONE
                        binding.sandwich.visibility = View.VISIBLE
                    }
            }
            sandwich.totalCost = (sandwich.extraCost + sandwich.sandwichCost).toString()
            binding.invalidateAll()
            Toast.makeText(
                activity, "Total Cost :" +
                        " ${sandwich.totalCost}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * handles the bread type radio buttons for choosing the bread type.
     *
     * @param view is for the radio buttons
     */
    fun onBreadRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.gluten_free_radio ->
                    if (checked) {
                        sandwich.breadType = "Gluten-Free"
                    }
                R.id.wheat_radio ->
                    if (checked) {
                        sandwich.breadType = "Whole Wheat"
                    }
            }
        }
    }

} //end of class




