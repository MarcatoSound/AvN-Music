package ca.avalonmc.avnmusic;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static ca.avalonmc.avnmusic.AvNMusic.plugin;

public class Song extends Loopable {

    private String namespace;
    private MusicTimer timer;
    // TODO implement functionality for multiple tracks playing at the same time (individual instruments of the same song?)
    //private ArrayList<Track> tracks;

    public Song(ConfigurationSection config) {
        super(plugin.getTrackManager().get(config.getString("Intro")), plugin.getTrackManager().get(config.getString("Loop")));
        this.namespace = config.getName();
        plugin.getMusicManager().put(this);
    }

    public void play(Player player) {
        playIntro(player);
        timer = new MusicTimer(getLoop(), player);
    }

    public void stop(Player player) {
        getIntro().stop(player);
        getLoop().stop(player);
        timer.cancel();
    }

    public void playIntro(Player player) {
        getIntro().play(player);
        player.sendMessage(Util.colorize("&2[DEBUG] &aPlaying intro!"));
    }

    public void playLoop(Player player) {
        getLoop().play(player);
        player.sendMessage(Util.colorize("&2[DEBUG] &aPlaying loop!"));
    }

    public String getName() {
        return namespace;
    }


    private class MusicTimer extends BukkitRunnable {

        Track track;
        int loopTime;
        Player player;

        MusicTimer(Track track, Player player) {
            this.track = track;
            this.loopTime = track.getLength();
            this.player = player;
            this.runTaskTimerAsynchronously(plugin, getIntro().getLength(), loopTime);
        }

        @Override
        public void run() {
            track.stop(player);
            playLoop(player);
        }
    }

}
