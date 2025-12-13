package fr.snipertvmc.chatdefender;

import fr.snipertvmc.chatdefender.managers.FilesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {


	// -------------------------------------------------- //


	private static Main instance;

	private FilesManager filesManager;

	// -------------------------------------------------- //


	@Override
	public void onEnable() {


		// INSTANCE INITIALIZATION
		instance = this;
	}


	@Override
	public void onDisable() {

	}


	// -------------------------------------------------- //


	public File getPluginFile() {
		return getFile();
	}

	public static Main getInstance() {
		return instance;
	public FilesManager getFilesManager() {
		return filesManager;
	}
	}


	// -------------------------------------------------- //
}