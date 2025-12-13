package fr.snipertvmc.chatdefender.infrastructure.models.files;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigurationFile {


	// -------------------------------------------------- //


	private final YamlConfiguration yamlConfiguration;


	// -------------------------------------------------- //


	public ConfigurationFile(YamlConfiguration yamlConfiguration) {
		this.yamlConfiguration = yamlConfiguration;
	}


	// -------------------------------------------------- //


	public String getConfigVersion() {
		return yamlConfiguration.getString("general.configVersion", "Config version not found");
	}

	public boolean isDetailedLoading() {
		return yamlConfiguration.getBoolean("general.detailedLoading", true);
	}

	public boolean checkForUpdates() {
		return yamlConfiguration.getBoolean("general.checkForUpdates", true);
	}


	// -------------------------------------------------- //


	public boolean canReceiveUpdateAlert(Player player) {
		return player.hasPermission(yamlConfiguration.getString("permissions.admin.updateAlert", "unknownPermission"));
	}


	public boolean canBypassMessagesAnalysis(Player player) {
		return player.hasPermission(yamlConfiguration.getString("permissions.admin.bypassMessagesAnalysis", "unknownPermission"));
	}


	// -------------------------------------------------- //
}
