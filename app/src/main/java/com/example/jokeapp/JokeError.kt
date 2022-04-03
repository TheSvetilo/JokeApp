package com.example.jokeapp

interface JokeError {
    fun getMessage() : String
}

class NoConnection(private val res: ResourceManager) : JokeError {
    override fun getMessage(): String = res.getString(R.string.no_connection)
}

class ServiceUnavailable(private val res: ResourceManager) : JokeError {
    override fun getMessage(): String = res.getString(R.string.service_unavailable)
}
