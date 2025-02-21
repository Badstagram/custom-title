package gay.badstagram.customtitles.mixin;

import gay.badstagram.customtitles.Util;
import gay.badstagram.customtitles.client.CustomTitlesClient;
import gay.badstagram.customtitles.client.config.ConfigManager;
import gay.badstagram.customtitles.client.config.CustomTitlesConfig;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Unique
    public String getDefaultTitle() {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayNetworkHandler networkHandler = mc.getNetworkHandler();

        StringBuilder stringBuilder = new StringBuilder("Minecraft");
        if (MinecraftClient.getModStatus().isModded()) {
            stringBuilder.append("*");
        }

        stringBuilder.append(" ");
        stringBuilder.append(SharedConstants.getGameVersion().getName());
        ClientPlayNetworkHandler clientPlayNetworkHandler = mc.getNetworkHandler();
        if (clientPlayNetworkHandler != null && clientPlayNetworkHandler.getConnection().isOpen()) {
            stringBuilder.append(" - ");
            ServerInfo serverInfo = mc.getCurrentServerEntry();
            if (mc.getServer() != null && !mc.getServer().isRemote()) {
                stringBuilder.append(I18n.translate("title.singleplayer"));
            } else if (serverInfo != null && serverInfo.isRealm()) {
                stringBuilder.append(I18n.translate("title.multiplayer.realms"));
            } else if (networkHandler == null && (serverInfo == null || !serverInfo.isLocal())) {
                stringBuilder.append(I18n.translate("title.multiplayer.other"));
            } else {
                stringBuilder.append(I18n.translate("title.multiplayer.lan"));
            }
        }

        return stringBuilder.toString();
    }


    @Inject(
            cancellable = true,
            method = "getWindowTitle",
            at = @At("HEAD")
    )
    public void getWindowTitle(CallbackInfoReturnable<String> cir) {
        MinecraftClient mc = (MinecraftClient) (Object) this;

        CustomTitlesConfig cfg = ConfigManager.INSTANCE.getConfigOrException();

        if (!cfg.getEnabled()) {
            cir.setReturnValue(getDefaultTitle());
            return;
        }
        ClientPlayNetworkHandler clientPlayNetworkHandler = mc.getNetworkHandler();

        if (clientPlayNetworkHandler != null && clientPlayNetworkHandler.getConnection().isOpen()) {
            ServerInfo serverInfo = mc.getCurrentServerEntry();
            if (mc.getServer() != null && !mc.getServer().isRemote()) {
                cir.setReturnValue(Util.formatTitle(cfg.getSinglePlayer()));
            } else if (serverInfo != null && serverInfo.isRealm()) {
                cir.setReturnValue(Util.formatTitle(cfg.getRealms()));
            } else if (mc.getServer() == null && (serverInfo == null || !serverInfo.isLocal())) {
                cir.setReturnValue(Util.formatTitle(cfg.getMultiPlayer()));
            } else {
                cir.setReturnValue(Util.formatTitle(cfg.getLan()));
            }
        }
    }
}
