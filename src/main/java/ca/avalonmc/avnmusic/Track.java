package ca.avalonmc.avnmusic;

import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import static ca.avalonmc.avnmusic.AvNMusic.plugin;

public class Track {

    private ConfigurationSection config;
    private String namespace;
    private String soundAddress;
    private double seconds;
    private int length;

    public Track(ConfigurationSection config) {
        this.config = config;
        this.namespace = config.getName();
        System.out.println("Loading track: " + namespace);
        this.soundAddress = config.getString("SoundPath");
        System.out.println("| SoundPath: " + soundAddress);
        this.seconds = config.getDouble("Length");
        System.out.println("| Length in seconds: " + seconds);
        this.length = ticksFromSeconds(seconds);
        System.out.println("| Length in ticks: " + length);
        plugin.getTrackManager().put(this);
    }

    public void play(Player player) {
        player.playSound(player.getLocation(), soundAddress, 1000F, 1.0F);
    }

    public void stop(Player player) {
        player.stopSound(soundAddress);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getSoundAddress() {
        return soundAddress;
    }

    public void setSoundAddress(String soundAddress) {
        this.soundAddress = soundAddress;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public int ticksFromSeconds(double seconds) {
        return (int) Util.round((seconds*20), 0);
    }
}
