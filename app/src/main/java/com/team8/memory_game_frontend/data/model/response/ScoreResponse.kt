package com.team8.memory_game_frontend.data.model.response

data class ScoreResponse(
    val username: String,
    val points: Int,
    val totalMoves: Int,
    val totalSeconds: Int,
    val createdAt: String
)
