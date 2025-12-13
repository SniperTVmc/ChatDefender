package fr.snipertvmc.chatdefender.infrastructure.models.files;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesFile {


	// -------------------------------------------------- //


	private final YamlConfiguration yamlConfiguration;


	// -------------------------------------------------- //


	public MessagesFile(YamlConfiguration yamlConfiguration) {
		this.yamlConfiguration = yamlConfiguration;
	}


	// -------------------------------------------------- //


	public String getMessagesVersion() {
		String version = yamlConfiguration.getString("messages-version");
		if (version != null) {
			return version;
		}
		return "0.0";
	}


	public String getPrefix() {
		return yamlConfiguration.getString("prefix");
	}


	// -------------------------------------------------- //


	public String getString(String path) {
		return yamlConfiguration.getString(path);
	}
	public String getString(String path, String defaultValue) {
		return yamlConfiguration.getString(path, defaultValue);
	}


	// -------------------------------------------------- //
}
