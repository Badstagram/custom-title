package gay.badstagram.customtitles.client

import kotlinx.serialization.json.Json
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient
import org.slf4j.LoggerFactory


val json = Json {
    prettyPrint = true
    encodeDefaults = true
}

val logger = LoggerFactory.getLogger("customtitles")

val minecraft: MinecraftClient
    get() = MinecraftClient.getInstance()

class CustomTitlesClient : ClientModInitializer {

    override fun onInitializeClient() {
        logger.info("Initializing custom titles client")
    }
}
