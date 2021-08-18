package com.kngrck.fooddeliveryfinal.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kngrck.fooddeliveryfinal.model.ApiRepository
import com.kngrck.fooddeliveryfinal.model.entity.cart.UpdateCartOrderCountRequest
import com.kngrck.fooddeliveryfinal.model.entity.common.BaseResponse
import com.kngrck.fooddeliveryfinal.model.entity.order.OrderListResponse
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getCart(): LiveData<Resource<OrderListResponse>> =
        apiRepository.getCart()

    fun confirmCart(): LiveData<Resource<BaseResponse>> =
        apiRepository.confirmCart()

    fun updateCartOrderCount(cartOrderId: String, count: UpdateCartOrderCountRequest): LiveData<Resource<BaseResponse>> =
        apiRepository.updateCartOrderCount(cartOrderId,count)

    fun deleteCartOrder(cartOrderId: String) : LiveData<Resource<BaseResponse>> =
        apiRepository.deleteCartOrder(cartOrderId)
}