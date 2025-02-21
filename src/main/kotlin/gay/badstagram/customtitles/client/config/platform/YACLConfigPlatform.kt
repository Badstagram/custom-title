package gay.badstagram.customtitles.client.config.platform

import dev.isxander.yacl3.api.ConfigCategory
import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.OptionDescription
import dev.isxander.yacl3.api.YetAnotherConfigLib
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder
import dev.isxander.yacl3.api.controller.StringControllerBuilder
import gay.badstagram.customtitles.client.config.ConfigManager
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

object YACLConfigPlatform {
    fun buildScreen(parent: Screen?): Screen {
        val builder =
            YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Custom Titles")).save(ConfigManager::saveToFile)


        val enabledOption = Option.createBuilder<Boolean>()
            .name(Text.literal("Enabled"))
            .description(OptionDescription.of(Text.of("Enable the mod")))
            .binding(
                true,
                { ConfigManager.configOrException.enabled },
                { ConfigManager.configOrException.enabled = it }
            )
            .controller { opt -> BooleanControllerBuilder.create(opt).coloured(true).yesNoFormatter() }
            .build()

        val singlePlayerOption = Option.createBuilder<String>()
            .name(Text.literal("Single Player Title"))
            .description(OptionDescription.of(Text.of("The title to use when on a singleplayer world")))
            .binding(
                "Minecraft* {{version}} - Singleplayer",
                { ConfigManager.configOrException.singlePlayer },
                { ConfigManager.configOrException.singlePlayer = it },
            )
            .controller { opt -> StringControllerBuilder.create(opt) }
            .build()

        val multiPlayerOption = Option.createBuilder<String>()
            .name(Text.literal("Multi Player Title"))
            .description(OptionDescription.of(Text.of("The title to use when on a multiplayer server")))
            .binding(
                "Minecraft* {{version}} - Multiplayer (3rd-party Server)",
                { ConfigManager.configOrException.multiPlayer },
                { ConfigManager.configOrException.multiPlayer = it },
            )
            .controller { opt -> StringControllerBuilder.create(opt) }
            .build()

        val realmsOption = Option.createBuilder<String>()
            .name(Text.literal("Realms Title"))
            .description(OptionDescription.of(Text.of("The title to use when on a realms server")))
            .binding(
                "Minecraft* {{version}} - Multiplayer (Realms)",
                { ConfigManager.configOrException.realms },
                { ConfigManager.configOrException.realms = it },
            )
            .controller { opt -> StringControllerBuilder.create(opt) }
            .build()

        val lanOption = Option.createBuilder<String>()
            .name(Text.literal("LAN Title"))
            .description(OptionDescription.of(Text.of("The title to use when on a LAN server")))
            .binding(
                "Minecraft* {{version}} - Multiplayer (LAN)",
                { ConfigManager.configOrException.lan },
                { ConfigManager.configOrException.lan = it },
            )
            .controller { opt -> StringControllerBuilder.create(opt) }
            .build()

        val baseCategory = ConfigCategory.createBuilder()
            .name(Text.literal("Custom Titles"))
            .tooltip(Text.literal("Custom Titles"))
            .options(
                listOf(
                    enabledOption,
                    singlePlayerOption,
                    multiPlayerOption,
                    realmsOption,
                    lanOption,
                )
            )
            .build();

        builder.categories(listOf(baseCategory))
        return builder.build().generateScreen(parent)
    }
}