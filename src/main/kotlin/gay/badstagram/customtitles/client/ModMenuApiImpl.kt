package gay.badstagram.customtitles.client

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.terraformersmc.modmenu.gui.ModsScreen
import gay.badstagram.customtitles.client.config.platform.YACLConfigPlatform
import java.io.ObjectInputFilter.Config

class ModMenuApiImpl : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory {
            YACLConfigPlatform.buildScreen(ModsScreen(null))
        }
    }
}