package com.arslan.home.datasource.remote

import com.arslan.data.DataState
import com.arslan.home.model.TopHeadlines
import com.arslan.network.RequestExecutor
import com.arslan.network.model.ApiError
import com.arslan.network.model.ApiException
import com.arslan.network.model.ApiSuccess
import com.arslan.network.request.ApiRequest
import com.arslan.network.request.RequestBuilder
import com.arslan.utils.Dispatcher
import com.arslan.utils.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException


class HomeRemoteDataSource @Inject constructor(
    @Dispatcher(CoroutineDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val requestExecutor: RequestExecutor
) {

    suspend fun getHeadlines(): Flow<DataState<TopHeadlines>> = withContext(ioDispatcher) {
        flow {
            when (val response = requestExecutor.execute<TopHeadlines>(newsHeadlinesRequest())) {
                is ApiSuccess -> emit(DataState.success(response.data))
                is ApiError -> emit(DataState.error(response.message))
                is ApiException -> {
                    if (response.e is SocketTimeoutException) emit(DataState.error("No Network Available"))
                    else emit(DataState.error("Something went wrong"))
                }
            }
        }
    }

    private fun newsHeadlinesRequest(): ApiRequest {
        val newsHeadlinesRequest = RequestBuilder.createBasicGetRequest(
            "top-headlines/", object : TypeToken<TopHeadlines?>() {}.type
        )
        newsHeadlinesRequest.queryParams?.set("country", "us")
        return newsHeadlinesRequest
    }

}