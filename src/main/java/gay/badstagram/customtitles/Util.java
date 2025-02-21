package gay.badstagram.customtitles;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.WorldSavePath;

import java.nio.file.Path;

public class Util {
    public static String formatTitle(String title) {
        MinecraftClient mc = MinecraftClient.getInstance();
        String version = SharedConstants.getGameVersion().getName();
        String username = mc.getSession().getUsername();
        IntegratedServer server = mc.getServer();
        ServerInfo serverEntry = mc.getCurrentServerEntry();

        String formattedTitle = title
                .replace("{{version}}", version)
                .replace("{{username}}", username);

        if (server != null) {
            formattedTitle = formattedTitle.replace("{{world.name}}", server.getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString());
        }

        if (serverEntry != null) {
            formattedTitle = formattedTitle
                    .replace("{{server.address}}", serverEntry.address)
                    .replace("{{server.name}}", serverEntry.name);
        }

        return formattedTitle;
    }
}
