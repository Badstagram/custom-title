package gay.badstagram.customtitles.client.config

import kotlinx.serialization.Serializable

@Serializable
data class CustomTitlesConfig(
    var enabled: Boolean,
    var singlePlayer: String,
    var multiPlayer: String,
    var lan: String,
    var realms: String,
) {
    companion object {
        val DEFAULT = CustomTitlesConfig(
            enabled = true,
            singlePlayer = "Minecraft* {{version}} - Singleplayer",
            multiPlayer = "Minecraft* {{version}} - Multiplayer (3rd-party Server)",
            realms = "Minecraft* {{version}} - Multiplayer (Realms)",
            lan = "Minecraft* {{version}} - Multiplayer (LAN)",
        )
    }
}
