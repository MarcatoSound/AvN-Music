package ca.avalonmc.avnmusic;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.HashMap;

import static ca.avalonmc.avnmusic.AvNMusic.plugin;

public class TrackManager {

    private HashMap<String, Track> tracks;
    private FileConfiguration config;

    public TrackManager() {
        tracks = new HashMap<>();
    }

    public void initTracks() {

        if (plugin.getTrackManager() == null) return;

        config = new YamlConfiguration();

        try {

            if (!plugin.getTracksFile().isFile()) plugin.getTracksFile().createNewFile();

            config.load(plugin.getTracksFile());

            for (String header : config.getKeys(false)) {
                ConfigurationSection section = config.getConfigurationSection(header);
                if (section == null) continue;
                new Track(section);
            }

        } catch (InvalidConfigurationException | IOException e) {
            System.out.println("ERROR: Could not load tracks.yml!");
            e.printStackTrace();
        }

    }

    public void put(Track track) {
        tracks.put(track.getNamespace(), track);
    }

    public Track get(String path) {
        return tracks.get(path);
    }

    public void remove(Track track) {
        tracks.remove(track.getNamespace());
    }
    public void remove(String path) {
        tracks.remove(path);
    }

}
