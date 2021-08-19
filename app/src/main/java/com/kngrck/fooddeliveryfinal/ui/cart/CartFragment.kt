package com.kngrck.fooddeliveryfinal.ui.cart

import android.os.Bundle
import android.util.Log
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
                    _binding.mainLayout.gone()
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {

                    _binding.mainLayout.show()
                    _binding.progressBar.gone()

                    val orders = it.data?.data!!
                    cartOrders = orders
                    adapter.setOrders(orders)
                    adapter.setListener(this)
                    with(_binding) {
                        ordersRecyclerView.adapter = adapter
                        totalTextView.text = String.format("%.2f", getTotal()) + " TL"

                        if (orders.size == 0) {
                            orderButton.isClickable = false
                            orderButton.alpha = 0.5f
                            noDataTextView.show()
                            totalLinearLayout.gone()
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    Log.v("Cart", "error ${it.message}")
                    _binding.mainLayout.show()
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun confirmCartAndNavigateToHome() {
        viewModel.confirmCart().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.mainLayout.gone()
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {

                    _binding.mainLayout.show()
                    _binding.progressBar.gone()

                    findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
                }
                Resource.Status.ERROR -> {
                    Log.v("Cart", "error ${it.message}")
                    _binding.mainLayout.show()
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun getTotal(): Double {
        var sum = 0.0
        for (order in cartOrders) {
            sum += (order.count * order.mealPrice)
        }
        return sum
    }

    override fun countChanged(cartOrderId: String, count: Int, updatedOrders: ArrayList<Order>) {

        val updateCartOrderCountRequest = UpdateCartOrderCountRequest(count)

        if (count == 0) {
            viewModel.deleteCartOrder(cartOrderId).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.v("Cart", "loading ")
                    }
                    Resource.Status.SUCCESS -> {
                        _binding.totalTextView.text = String.format("%.2f", getTotal()) + " TL"
                        with(_binding) {
                            if (updatedOrders.size == 0) {
                                orderButton.isClickable = false
                                orderButton.alpha = 0.5f
                                noDataTextView.show()
                                totalLinearLayout.gone()
                            }

                        }

                        Log.v("Cart", "success ")
                    }
                    Resource.Status.ERROR -> {
                        Log.v("Cart", "error ${it}")

                    }
                }

            })
        } else {
            viewModel.updateCartOrderCount(cartOrderId, updateCartOrderCountRequest)
                .observe(viewLifecycleOwner,
                    {
                        when (it.status) {
                            Resource.Status.LOADING -> {
                                Log.v("Cart", "loading ")
                            }
                            Resource.Status.SUCCESS -> {
                                _binding.totalTextView.text =
                                    String.format("%.2f", getTotal()) + " TL"
                                Log.v("Cart", "success ")
                            }
                            Resource.Status.ERROR -> {
                                Log.v("Cart", "error $it")

                            }
                        }

                    }

                )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.removeListeners()
    }

}