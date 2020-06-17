package ca.avalonmc.avnmusic;

import org.bukkit.entity.Player;

public class MusicPlayer {

    private Song song;
    private Player player;
    private boolean playing;

    public MusicPlayer(Player player) {
        this.player = player;
        this.playing = false;
    }

    public void playMusic(Song song) {
        stopMusic();
        this.song = song;
        this.song.play(player);
        setPlaying(true);
    }

    public void playTrack(Track track) {
        track.play(player);
    }

    public void stopMusic() {
        if (song != null && isPlaying()) {
            song.stop(player);
            setPlaying(false);
        }
    }


    public Song getSong() {
        return song;
    }
    public void setSong(Song song) {
        this.song = song;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
