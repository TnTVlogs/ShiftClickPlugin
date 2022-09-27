package me.tntvlogs.shiftclickplugin.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.tntvlogs.shiftclickplugin.Main;
import me.tntvlogs.shiftclickplugin.utils.Chat;

public class ShiftClickListener implements Listener {

	private Main plugin;

	public ShiftClickListener(Main plugin) {

		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);

	}

	@EventHandler
	public void onShiftRightClick(PlayerInteractEntityEvent e) {

		Boolean cond1 = !(e.getRightClicked() instanceof Player);
		Boolean cond2 = !(!e.getPlayer().hasPermission("ShiftClick.use"));
		Boolean cond3 = !(plugin.getConfig().getStringList("config.enabled_worlds")
				.contains(e.getRightClicked().getWorld().getName()));
		
		if (cond1 || cond3) {
			return;
		}

		if (cond2) {
			e.getPlayer().sendMessage(Chat.ChatColor(plugin.getConfig().getString("messages.prefix")
					+ plugin.getConfig().getString("messages.no_permission")));
		}
		
		Player clicker = e.getPlayer();
		Player clicked = (Player) e.getRightClicked();

		List<String> command = plugin.getConfig().getStringList("config.commands");

		for (String s : command) {

			clicker.performCommand(s.replace("%player_clicked%", clicked.getDisplayName()).replace("%player_clicker%",
					clicker.getDisplayName()));

		}
		return;
	}

}
