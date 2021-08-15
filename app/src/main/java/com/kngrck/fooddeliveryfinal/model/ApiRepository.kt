package com.kngrck.fooddeliveryfinal.model

import com.kngrck.fooddeliveryfinal.model.local.LocalDataSource
import com.kngrck.fooddeliveryfinal.model.remote.RemoteDataSource
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {


}
