package com.example.jokeapp

data class JokeDTO(
    private val categories : List<String>,
    private val created_at: String,
    private val icon: String,
    private val id: String,
    private val updated_at: String,
    private val url: String,
    private val value: String
) {
    fun toJoke() = Joke(value, id)
}
