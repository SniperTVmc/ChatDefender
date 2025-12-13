package fr.snipertvmc.chatdefender;

import fr.snipertvmc.chatdefender.managers.FilesManager;
import fr.snipertvmc.chatdefender.managers.LibraryManager;
import net.byteflux.libby.BukkitLibraryManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {


	// -------------------------------------------------- //


	private static Main instance;

	private BukkitLibraryManager bukkitLibraryManager;

	private FilesManager filesManager;
	private LibraryManager libraryManager;


	// -------------------------------------------------- //


	@Override
	public void onEnable() {


		// INSTANCE INITIALIZATION
		instance = this;


		// LIBRARY MANAGER INITIALIZATION
		// Must be done at the beginning to load essential libraries
		bukkitLibraryManager = new BukkitLibraryManager(this);
		libraryManager = new LibraryManager();
	}


	@Override
	public void onDisable() {

	}


	// -------------------------------------------------- //


	public static Main getInstance() {
		return instance;
	}

	public BukkitLibraryManager getBukkitLibraryManager() {
		return bukkitLibraryManager;
	}
	public File getPluginFile() {
		return getFile();
	}

	public FilesManager getFilesManager() {
		return filesManager;
	}
	public LibraryManager getLibraryManager() {
		return libraryManager;
	}


	// -------------------------------------------------- //
}