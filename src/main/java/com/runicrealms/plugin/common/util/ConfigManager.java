package com.runicrealms.plugin.common.util;

import com.runicrealms.plugin.common.api.ConfigAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Helper class when working with configs / flat file
 */
public class ConfigManager implements ConfigAPI {

    @Override
    public File getSubFolder(File folder, String subfolder) {
        assert folder != null; // main folder
        for (File file : folder.listFiles()) {
            if (file.getName().equalsIgnoreCase(subfolder)) {
                return file;
            }
        }
        return null;
    }

    @Override
    public FileConfiguration getYamlConfigFromFile(String fileName, File folder) {
        return getYamlConfigFromFile(new File(folder, fileName));
    }

    @Override
    public FileConfiguration getYamlConfigFromFile(File file) {
        FileConfiguration config = new YamlConfiguration();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            config.load(file);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return config;
    }
}
