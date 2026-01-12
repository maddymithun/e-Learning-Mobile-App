package com.example.composecleanarchitecture.data.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IApiService {
    @GET("{url}")
    suspend fun getRequest(
        @Path(value = "url", encoded = true) path: String,
        @QueryMap hashMap: Map<String, String>
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("{url}")
    suspend fun postRequest(
        @Path(value = "url", encoded = true) path: String,
        @FieldMap hashMap: Map<String, String>
    ): Response<ResponseBody>

    @Multipart
    @Headers("Content-Type:multipart/form-data")
    @POST("{url}")
    suspend fun sendDocuments(
        @Path(value = "url", encoded = true) path: String,
        @PartMap partMap: Map<String, RequestBody>
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("{url}")
    suspend fun pagingPostRequest(
        @Path(value = "url", encoded = true) path: String,
        @FieldMap hashMap: Map<String, String>
    ): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("{url}")
    suspend fun postRequestForRaw(
        @Path(value = "url", encoded = true) path: String,
        @Body requestBody: RequestBody
    ): Response<ResponseBody>

}