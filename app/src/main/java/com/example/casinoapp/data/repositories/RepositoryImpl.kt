package com.example.casinoapp.data.repositories

import android.util.Log
import com.example.casinoapp.Utils
import com.example.casinoapp.data.local.dao.HintDao
import com.example.casinoapp.domain.Hint
import com.example.casinoapp.presentation.repositories.Repository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val hintDao: HintDao
) : Repository {

    private val db = Firebase.database.getReference(Utils.LINK)
    private var config: FirebaseRemoteConfig

    init {
        config = initConfig()
    }

    override suspend fun getHints() = withContext(Dispatchers.IO) {
        hintDao.getHints()
    }

    override suspend fun insertLink(vararg hint: Hint) = withContext(Dispatchers.IO) {
        hintDao.insertHint(*hint)
    }

    override fun setLink(link: String) {
        db.setValue(link)
    }

    override fun getLink(): String {
        return config.getString("web_link")
    }

    override fun getMode(): Boolean {
        return config.getBoolean("game_pass")
    }

    private fun initConfig(): FirebaseRemoteConfig {
        val config = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0

        }
        config.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(Utils.DEFAULTS)

            fetchAndActivate().addOnCompleteListener {
                Log.e("AAA", "Remote config fetch complete")
            }
        }
        return config
    }
}