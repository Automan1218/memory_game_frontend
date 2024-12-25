package com.team8.memory_game_frontend.data.api

import com.team8.memory_game_frontend.data.model.request.LoginRequest
import com.team8.memory_game_frontend.data.model.request.RegisterRequest
import com.team8.memory_game_frontend.data.model.response.LoginResponse
import com.team8.memory_game_frontend.data.model.request.ScoreRequest
import com.team8.memory_game_frontend.data.model.response.RegisterResponse
import com.team8.memory_game_frontend.data.model.response.ScoreResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST("api/users/{userId}/premium")
    suspend fun purchasePremium(@Path("userId") userId: String): Response<Unit>

    @POST("api/scores/create")
    suspend fun createScore(@Body request: ScoreRequest): ScoreResponse

    @GET("api/scores/get")
    suspend fun getScores(): List<ScoreResponse>


}
