package com.example.jokeapp

class Joke(
    private val jokeText: String,
    private val id: String
) {

    fun getJokeString() = "$id\n$jokeText"

}