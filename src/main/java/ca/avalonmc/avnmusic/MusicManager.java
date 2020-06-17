package ca.avalonmc.avnmusic;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.HashMap;

import static ca.avalonmc.avnmusic.AvNMusic.plugin;

public class MusicManager {

    private HashMap<String, Song> music;
    private FileConfiguration config;

    public MusicManager() {
        music = new HashMap<>();
    }

    public void initMusic() {
        if (plugin.getMusicManager() == null) return;

        config = new YamlConfiguration();

        try {

            if (!plugin.getMusicFile().isFile()) plugin.getMusicFile().createNewFile();

            config.load(plugin.getMusicFile());

            for (String header : config.getKeys(false)) {
                ConfigurationSection section = config.getConfigurationSection(header);
                if (section == null) continue;
                new Song(section);
            }

        } catch (InvalidConfigurationException | IOException e) {
            System.out.println("ERROR: Could not load music.yml!");
            e.printStackTrace();
        }

    }

    public void put(Song song) {
        this.music.put(song.getName(), song);
    }

    public Song get(String namespace) {
        return music.get(namespace);
    }

    public void remove(Song song) {
        this.music.remove(song.getName());
    }
    public void remove(String namespace) {
        this.music.remove(namespace);
    }

}
