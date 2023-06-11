package com.runicrealms.plugin.common.util;

import com.runicrealms.plugin.common.RunicCommon;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class OfflinePlayerUtil {

    public static CompletableFuture<String> getName(UUID player) {
        CompletableFuture<String> future = new CompletableFuture<>();
        LuckPermsProvider.get().getUserManager().loadUser(player).thenAcceptAsync(user -> future.complete(user.getUsername()));
        return future;
    }

    public static CompletableFuture<@Nullable UUID> getUUID(String name) {
        CompletableFuture<UUID> future = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(RunicCommon.getInstance(), () -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream()));
                String uuid = (((JSONObject) new JSONParser().parse(in)).get("id")).toString().replaceAll("\"", "");
                uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
                in.close();
                future.complete(UUID.fromString(uuid));
            } catch (Exception e) {
                future.complete(null);
            }
        });
        return future;
    }

}
