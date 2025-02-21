package gay.badstagram.customtitles.client.config

import gay.badstagram.customtitles.client.CustomTitlesClient
import gay.badstagram.customtitles.client.json
import gay.badstagram.customtitles.client.logger
import net.fabricmc.loader.api.FabricLoader
import java.io.File


object ConfigManager {

    fun saveToFile() {
        configFile.writeText(json.encodeToString(config))
    }

    private val configFile = File(FabricLoader.getInstance().configDir.toString() + "/customtitles.json")
    private var config: CustomTitlesConfig? = null;

    val configOrException: CustomTitlesConfig
        get() = config ?: throw RuntimeException("Config is somehow null")

    init {
        if(!configFile.parentFile.exists()) {
            configFile.parentFile.mkdirs()
        }
        if(!configFile.exists()) {
            configFile.createNewFile()
            configFile.writeText(json.encodeToString(CustomTitlesConfig.DEFAULT))
        }
        runCatching {
            config = json.decodeFromString<CustomTitlesConfig>(configFile.readText())
        }
    }
}