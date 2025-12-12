package fr.snipertvmc.chatdefender.utilities;

import fr.snipertvmc.chatdefender.Main;

public class ConsoleLogger {


	// -------------------------------------------------- //


	public static void console(String message) {
		Main.getInstance().getServer().getConsoleSender().sendMessage(message);
	}


	public static void info(String message) {
		Main.getInstance().getComponentLogger().info(message);
	}


	public static void warn(String message) {
		Main.getInstance().getComponentLogger().warn(message);
	}


	public static void error(String message) {
		Main.getInstance().getComponentLogger().error(message);
	}


	// -------------------------------------------------- //
}
