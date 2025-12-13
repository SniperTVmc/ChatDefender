package fr.snipertvmc.chatdefender.managers;

import fr.snipertvmc.chatdefender.Main;
import fr.snipertvmc.chatdefender.infrastructure.models.files.ConfigurationFile;
import fr.snipertvmc.chatdefender.infrastructure.models.files.MessagesFile;
import fr.snipertvmc.chatdefender.utilities.ConsoleLogger;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FilesManager {


	// -------------------------------------------------- //


	private ConfigurationFile configurationFile;
	private MessagesFile messagesFile;


	private final Map<String, String> filesPaths = new HashMap<>() {{
		put("configuration", "configuration");
		put("messages", "messages");
	}};


	private final Map<String, String> filesVersions = new HashMap<>() {{
		put("configuration", "1.5"); // Updated for version: 1.0.0
		put("messages", "1.4"); // Updated for version: 1.0.0
	}};


	// -------------------------------------------------- //


	public void loadFiles() {
		ConsoleLogger.console("\t§4ChatDefender: §7Loading files...");

		loadAndCheckConfiguration(false);
		loadAndCheckMessages(false);

		ConsoleLogger.console("\t§4ChatDefender: §7Files loading §fcompleted§7.");
	}


	public void reloadFiles() {

		long startTime = System.currentTimeMillis();

		ConsoleLogger.console("");
		ConsoleLogger.console("\t§4ChatDefender: §7Reloading files...");

		loadAndCheckConfiguration(true);
		loadAndCheckMessages(true);

		long endTime = System.currentTimeMillis();
		long loadingTime = endTime - startTime;

		ConsoleLogger.console("\t§4ChatDefender: §7Files reloading §fcompleted§7 in §f" + loadingTime + "ms§7.");
		ConsoleLogger.console("");
	}


	// -------------------------------------------------- //


	private void loadYAMLFile(String fileName) {

		String filePath = filesPaths.get(fileName);
		File file = new File(Main.getInstance().getDataFolder(), filePath + ".yml");

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			Main.getInstance().saveResource(filePath + ".yml", false);
		}

		YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(file);

		switch (fileName) {
			case "configuration" -> configurationFile = new ConfigurationFile(yamlFile);
			case "messages" -> messagesFile = new MessagesFile(yamlFile);
		}
	}


	private void createBackupYAMLFile(String fileName) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String formattedDate = dateFormat.format(currentDate);

		String filePath = filesPaths.get(fileName);
		File fileToBackup = new File(Main.getInstance().getDataFolder(), filePath + ".yml");
		if (fileToBackup.exists()) {

			File backupFile = new File(fileToBackup.getParent(), formattedDate + "_" + fileName + ".yml");
			if (!backupFile.exists()) {

				try {
					boolean success = fileToBackup.renameTo(backupFile);

					if (success) {
						loadYAMLFile(fileName);
					}

				} catch (SecurityException e) {
					throw new RuntimeException("Error while creating backup file: " + e.getMessage());
				}
			}

		} else {
			loadYAMLFile(fileName);
		}
	}


	// -------------------------------------------------- //


	public void checkUpdateForFile(String fileName) {

		String latestVersion = filesVersions.get(fileName);

		String fileVersion = switch (fileName) {
			case "configuration" -> getConfiguration().getConfigVersion();
			case "messages" -> getMessages().getMessagesVersion();
			default -> throw new IllegalStateException("Unexpected value: " + fileName);
		};

		if (!latestVersion.equals(fileVersion)) {
			createBackupYAMLFile(fileName);
		}
	}


	// -------------------------------------------------- //


	public void loadAndCheckConfiguration(boolean reload) {
		loadYAMLFile("configuration");
		checkUpdateForFile("configuration");
		String label = reload ? "Reloaded" : "Loaded";
		ConsoleLogger.console("\t§4ChatDefender: §8- §fconfiguration.yml: §a" + label);
	}


	public void loadAndCheckMessages(boolean reload) {
		loadYAMLFile("messages");
		checkUpdateForFile("messages");
		String label = reload ? "Reloaded" : "Loaded";
		ConsoleLogger.console("\t§4ChatDefender: §8- §fmessages.yml: §a" + label);
	}


	// -------------------------------------------------- //


	public ConfigurationFile getConfiguration() {
		return configurationFile;
	}
	public MessagesFile getMessages() {
		return messagesFile;
	}


	// -------------------------------------------------- //
}
