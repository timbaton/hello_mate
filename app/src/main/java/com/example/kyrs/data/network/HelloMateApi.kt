package com.example.kyrs.data.network

import com.example.kyrs.data.entity.request.LoginRequest
import com.example.kyrs.data.entity.response.LoginResponse
import com.example.kyrs.data.entity.response.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Project Kyrs
 * Package com.example.kyrs.data
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-01
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
interface HelloMateApi {

    @GET("/rest/users")
    fun getUsers(): Single<User>

    @POST("/rest/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}