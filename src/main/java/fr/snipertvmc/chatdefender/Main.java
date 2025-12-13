package fr.snipertvmc.chatdefender;

import fr.snipertvmc.chatdefender.managers.FilesManager;
import fr.snipertvmc.chatdefender.managers.LibraryManager;
import fr.snipertvmc.chatdefender.utilities.ConsoleLogger;
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


		// PLUGIN LOADING
		long startTime = System.currentTimeMillis();

		ConsoleLogger.console("");
		ConsoleLogger.console("\t§4ChatDefender: §7Plugin loading...");


		// INSTANCE INITIALIZATION
		instance = this;


		// LIBRARY MANAGER INITIALIZATION
		// Must be done at the beginning to load essential libraries
		bukkitLibraryManager = new BukkitLibraryManager(this);
		libraryManager = new LibraryManager();


		// MANAGERS INITIALIZATION
		filesManager = new FilesManager();


		// FILES LOADING
		filesManager.loadFiles();


		// LOAD PLUGIN
		boolean cancelLoading = false;
//		if (!loadingManager.loadPlugin(filesManager.getConfiguration().isDetailedLoading())) {
//			cancelLoading = true;
//		}


		// SERVER INITIALIZATION
//		if (!cancelLoading) {
//			serverManager = new ServerManager();
//		}


		// PLUGIN LOADING COMPLETED
		long endTime = System.currentTimeMillis();
		long loadingTime = endTime - startTime;

		if (cancelLoading) {
			ConsoleLogger.console("\t§4ChatDefender: §cPlugin will be disabled due to loading errors.");
			ConsoleLogger.console("");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		ConsoleLogger.console("\t§4ChatDefender: §7The plugin has been §floaded §7correctly in §f" + loadingTime + "ms§7.");
		ConsoleLogger.console("");
	}


	// -------------------------------------------------- //


	@Override
	public void onDisable() {


		// PLUGIN UNLOADING
		long startTime = System.currentTimeMillis();

		ConsoleLogger.console("");
		ConsoleLogger.console("\t§4ChatDefender: §7Plugin unloading...");


//		// UNLOAD PLUGIN
//		loadingManager.unloadPlugin(filesManager.getConfiguration().isDetailedLoading());


		// PLUGIN UNLOADING COMPLETED
		long endTime = System.currentTimeMillis();
		long unloadingTime = endTime - startTime;

		ConsoleLogger.console("\t§4ChatDefender: §7The plugin has been §funloaded §7correctly in §f" + unloadingTime + "ms§7.");
		ConsoleLogger.console("");
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