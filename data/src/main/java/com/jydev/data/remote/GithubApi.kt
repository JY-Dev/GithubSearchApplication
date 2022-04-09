package com.jydev.data.remote

import com.jydev.data.model.remote.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users")
    suspend fun getSearchUsers(@Query("q") query: String, @Query("page") page: Int): SearchUserResponse
}