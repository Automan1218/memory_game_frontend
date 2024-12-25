package com.team8.memory_game_frontend.data.model.response

data class RegisterResponse(
    val userId: String,
    val username: String,
    val isPaidUser: Boolean
)
