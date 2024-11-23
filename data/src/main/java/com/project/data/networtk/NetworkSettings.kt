package com.project.data.networtk

class NetworkSettings private constructor() {
    var token: String = ""

    companion object {
        const val INPUT_TOKEN = "INPUT_TOKEN"
        private var instance: NetworkSettings? = null
        @JvmStatic
        fun getInstance(): NetworkSettings {
            if (instance == null)
                instance = NetworkSettings()

            return instance!!
        }

    }


}