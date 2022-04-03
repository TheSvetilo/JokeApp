package com.example.jokeapp

interface Model {

    fun getJoke()
    fun init(callback: ResultCallback)
    fun clear()
}

interface ResultCallback {
    fun provideSuccess(data: Joke)
    fun provideError(error: JokeError)
}

class TestModel(resourceManager: ResourceManager) : Model {

    private var callback: ResultCallback? = null
    private var count = 0

    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provideSuccess(Joke("When Chuck Norris goes out to eat, waiters and waitresses give him 15% tips.", "h8C1AcbwSOSu_BuvOn5RIw"))
                1 -> callback?.provideError(noConnection)
                2 -> callback?.provideError(serviceUnavailable)
            }
            count++
            if (count == 3) count = 0
        }.start()
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}

class BaseModel(
    private val service: JokeService,
    resourceManager: ResourceManager
) : Model {

    private var callback: ResultCallback? = null
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

    override fun getJoke() {
        service.getJoke(object : ServiceCallback {
            override fun returnSuccess(data: JokeDTO) {
                callback?.provideSuccess(data.toJoke())
            }

            override fun returnError(errorType: ErrorType) {
                when (errorType) {
                    ErrorType.NO_CONNECTION -> callback?.provideError(noConnection)
                    ErrorType.OTHER -> callback?.provideError(serviceUnavailable)
                }
            }
        })
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}