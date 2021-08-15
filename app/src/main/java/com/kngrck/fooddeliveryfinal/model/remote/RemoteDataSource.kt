package com.kngrck.fooddeliveryfinal.model.remote


import com.kngrck.fooddeliveryfinal.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

}

