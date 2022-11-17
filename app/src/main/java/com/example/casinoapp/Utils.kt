package com.example.casinoapp

class Utils {
    companion object {
        const val DBNAME = "local-db"
        const val LINK = "link"
        const val FILE_NAME = "sharedscore"
        const val UPDATE_SCORE = "score"
        const val WIN_REWARD = "reward"
        const val DEFAULT_LINK = "https://webqr.com/"
        const val BROWSER_CONF = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36"
        const val SELECT_FILE = 100
        val DEFAULTS = hashMapOf<String, Any>(
            "game_pass" to true,
            "web_link" to "https://webqr.com/"
        )
    }
}