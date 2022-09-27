package me.tntvlogs.shiftclickplugin;

import org.bukkit.plugin.java.JavaPlugin;

import me.tntvlogs.shiftclickplugin.listeners.ShiftClickListener;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		
		saveDefaultConfig();
		new ShiftClickListener(this);
	}
	
}
