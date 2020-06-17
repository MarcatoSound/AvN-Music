package ca.avalonmc.avnmusic;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public final class AvNMusic extends JavaPlugin {

    public static AvNMusic plugin;
    public static Logger log;
    private TrackManager trackManager;
    private MusicManager musicManager;
    private HashMap<UUID, MusicPlayer> players;

    private File tracksFile;
    private File musicFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        log = this.getLogger();
        saveDefaultConfig();
        tracksFile = new File(getDataFolder(), "tracks.yml");
        musicFile = new File(getDataFolder(), "music.yml");
        players = new HashMap<>();


        trackManager = new TrackManager();
        trackManager.initTracks();
        musicManager = new MusicManager();
        musicManager.initMusic();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName().toLowerCase()) {
            case "avnm":
                if (args.length == 0) {
                    sender.sendMessage(Util.colorize("&bAvalon Music - Version 1.0 Beta"));
                    return false;
                }

                Player target;
                Song song;
                Track track;
                MusicPlayer mPlayer;

                switch (args[0]) {
                    case "reload":
                        if (!Util.hasPermission(sender, "avnm.reload")) return false;
                        sender.sendMessage(Util.colorize("&bReloaded songs and tracks!"));
                        break;
                    case "playsong":
                        if (!Util.hasPermission(sender, "avnm.playsong") || args.length != 3) return false;
                        target = Bukkit.getPlayer(args[1]);
                        song = musicManager.get(args[2]);

                        if (target == null) {
                            sender.sendMessage(Util.colorize("&cThere is no player with this name online!"));
                            return false;
                        }

                        if (song == null) {
                            sender.sendMessage(Util.colorize("&cThere is no song with that namespace!"));
                            return false;
                        }

                        if (!players.containsKey(target.getUniqueId())) {
                            mPlayer = new MusicPlayer(target);
                            players.put(target.getUniqueId(), mPlayer);
                        } else {
                            mPlayer = players.get(target.getUniqueId());
                        }

                        mPlayer.playMusic(song);

                        break;

                    case "playtrack":
                        if (!Util.hasPermission(sender, "avnm.playtrack") || args.length != 3) return false;
                        target = Bukkit.getPlayer(args[1]);
                        track = trackManager.get(args[2]);

                        if (target == null) {
                            sender.sendMessage(Util.colorize("&cThere is no player with this name online!"));
                            return false;
                        }

                        if (track == null) {
                            sender.sendMessage(Util.colorize("&cThere is no track with that namespace!"));
                            return false;
                        }

                        if (!players.containsKey(target.getUniqueId())) {
                            mPlayer = new MusicPlayer(target);
                            players.put(target.getUniqueId(), mPlayer);
                        } else {
                            mPlayer = players.get(target.getUniqueId());
                        }

                        mPlayer.playTrack(track);
                        break;

                    case "stop":
                        if (!(sender instanceof Player)) return false;
                        target = (Player) sender;
                        if (players.containsKey(target.getUniqueId()) && players.get(target.getUniqueId()).isPlaying()) {
                            players.get(target.getUniqueId()).stopMusic();
                        } else {
                            sender.sendMessage("There is no music playing!");
                        }
                        break;
                }

                break;
        }
        return false;
    }



    public TrackManager getTrackManager() {
        return trackManager;
    }

    public MusicManager getMusicManager() {
        return musicManager;
    }

    public static AvNMusic getPluginInstance() {
         return plugin;
    }

    public File getTracksFile() {
        return tracksFile;
    }

    public File getMusicFile() {
        return musicFile;
    }
}
