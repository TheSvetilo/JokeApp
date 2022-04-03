package com.example.jokeapp

class MainViewModel(
    private val model: Model
) {
    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) {
                callback.provideText(data.getJokeString())
            }
            override fun provideError(error: JokeError) {
                callback.provideText(error.getMessage())
            }
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        callback = null
        model.clear()
    }

}

interface TextCallback {
    fun provideText(text: String)
}