package com.kngrck.fooddeliveryfinal.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentCartBinding
import com.kngrck.fooddeliveryfinal.model.entity.cart.UpdateCartOrderCountRequest
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), ICountChangeListener {
    private lateinit var _binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()
    private var adapter: OrdersAdapter = OrdersAdapter()
    private var cartOrders = ArrayList<Order>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        _binding.ordersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        getCartAndSetViews()
    }

    private fun initListeners() {
        _binding.orderButton.setOnClickListener {
            confirmCartAndNavigateToHome()
        }

    }

    private fun getCartAndSetViews() {
        viewModel.getCart().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

                    val orders = it.data?.data!!
                    cartOrders = orders
                    adapter.setOrders(orders)
                    adapter.setListener(this)
                    with(_binding) {
                        ordersRecyclerView.adapter = adapter
                        val totalText = String.format("%.2f", getTotal()) + " TL"
                        totalTextView.text = totalText

                        if (orders.size == 0) {
                            orderButton.isClickable = false
                            orderButton.alpha = 0.5f
                            noDataTextView.show()
                            totalLinearLayout.gone()
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    showErrorToast(requireContext())
                }
            }
        })
    }

    private fun confirmCartAndNavigateToHome() {
        viewModel.confirmCart().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

                    findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
                }
                Resource.Status.ERROR -> {
                    setLoading(false)

                    showErrorToast(requireContext(), "Failed order operation.")
                }
            }
        })
    }


    override fun countChanged(cartOrderId: String, count: Int, updatedOrders: ArrayList<Order>) {

        val updateCartOrderCountRequest = UpdateCartOrderCountRequest(count)

        if (count == 0) {
            viewModel.deleteCartOrder(cartOrderId).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        with(_binding) {
                            val totalText = String.format("%.2f", getTotal()) + " TL"
                            totalTextView.text = totalText

                            if (updatedOrders.size == 0) {
                                orderButton.isClickable = false
                                orderButton.alpha = 0.5f
                                noDataTextView.show()
                                totalLinearLayout.gone()
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        showErrorToast(
                            requireContext(),
                            "Failed to delete order.Please try again later."
                        )
                    }
                }
            })
        } else {
            viewModel.updateCartOrderCount(cartOrderId, updateCartOrderCountRequest)
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                        }
                        Resource.Status.SUCCESS -> {
                            val totalText = String.format("%.2f", getTotal()) + " TL"
                            _binding.totalTextView.text = totalText

                        }
                        Resource.Status.ERROR -> {
                            showErrorToast(
                                requireContext(),
                                "Failed to change quantity.Please try again later."
                            )

                        }
                    }
                }
                )
        }

    }

    private fun getTotal(): Double {
        var sum = 0.0
        for (order in cartOrders) {
            sum += (order.count * order.mealPrice)
        }
        return sum
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

    override fun onDestroy() {
        super.onDestroy()
        adapter.removeListeners()
    }

}