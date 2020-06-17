package ca.avalonmc.avnmusic;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {

    public static String colorize (String s) {

        return s == null ? null : ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean hasPermission (CommandSender sender, String node) {

        if (sender.hasPermission("*") || sender.hasPermission(node))
            return true;
        sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
        return false;
    }

    public static double round (double value, int places) {

        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;

		/*if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();*/
    }

}
