package com.kngrck.fooddeliveryfinal.ui.editrestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentAddRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.AddRestaurantRequest
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRestaurantFragment : Fragment() {
    private lateinit var _binding: FragmentAddRestaurantBinding
    private val viewModel: AddRestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRestaurantBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }


    private fun initViews() {
        val categories = resources.getStringArray(R.array.categories)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        _binding.categorySpinner.adapter = adapter
    }

    private fun initListeners() {
        _binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        _binding.applyButton.setOnClickListener {
            checkInputsAndAddRestaurant()
        }
    }

    private fun checkInputsAndAddRestaurant() {
        with(_binding) {
            val name = restaurantName.editText?.text.toString()
            val description = restaurantDescription.editText?.text.toString()
            val deliveryTime = restaurantDeliveryTime.editText?.text.toString()
            val minimumFee = restaurantMinimumFee.editText?.text.toString().toDoubleOrNull()
            val imageUrl = restaurantImage.editText?.text.toString()
            val category = categorySpinner.selectedItem.toString()

            if (name.isNotEmpty() &&
                description.isNotEmpty() &&
                deliveryTime.isNotEmpty() &&
                minimumFee != null &&
                imageUrl.isNotEmpty() &&
                category.isNotEmpty()
            ) {

                val addRestaurantRequest = AddRestaurantRequest(
                    name, minimumFee, deliveryTime, description, category, imageUrl
                )


                viewModel.addRestaurant(addRestaurantRequest).observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            setLoading(true)
                        }
                        Resource.Status.SUCCESS -> {
                            setLoading(false)
                            findNavController().navigate(R.id.action_addRestaurantFragment_to_profileFragment)

                        }
                        Resource.Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(
                                requireContext(),
                                "Restaurant could not added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            } else {
                if (name.isEmpty()) restaurantName.error = "Do not leave blank."
                if (description.isEmpty()) restaurantDescription.error = "Do not leave blank."
                if (deliveryTime.isEmpty()) restaurantDeliveryTime.error = "Do not leave blank."
                if (minimumFee == null) restaurantMinimumFee.error = "Do not leave blank."
                if (imageUrl.isEmpty()) restaurantImage.error = "Do not leave blank."
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        with(_binding) {
            if (isLoading) {
                mainLayout.gone()
                progressBar.show()
            } else {
                mainLayout.show()
                progressBar.gone()
            }
        }

    }


}