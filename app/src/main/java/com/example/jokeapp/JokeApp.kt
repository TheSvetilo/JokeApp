package com.example.jokeapp

import android.app.Application
import com.google.gson.Gson

class JokeApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(BaseModel(BaseJokeService(Gson()), BaseResourceManager(this)))
    }
}