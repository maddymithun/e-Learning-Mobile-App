package com.example.composecleanarchitecture.base

import com.apollographql.apollo3.ApolloClient
import com.example.composecleanarchitecture.data.network.AppNetworkState
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.data.network.resolveError
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.utils.makeJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject


abstract class BaseRepository : IBaseRepository {
    abstract var apiService: IApiService
    abstract var apolloClient: ApolloClient
    abstract var preferencesHelper: SharedPreferencesHelper
    abstract var roomHelper: RoomHelper

    protected suspend fun <T : Any> apiCallingState(callback: suspend () -> T): Flow<AppNetworkState<T>> =
        flow {
            emit(AppNetworkState.Loading)
            try {
                val networkCall = AppNetworkState.Data(callback())
                emit(networkCall)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(e.resolveError())
            }
        }

    override fun destroyCache() {
        preferencesHelper.clearAll()
    }

    override fun cacheResources(resourcesResponse: JSONObject) {
        preferencesHelper.putString("resources", resourcesResponse.makeJson())
    }

    override fun getCachedResources(): String =
        preferencesHelper["resources", ""].makeJson(isPretty = false)

}