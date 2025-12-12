package fr.snipertvmc.chatdefender.utilities;

import fr.snipertvmc.chatdefender.Main;
import fr.snipertvmc.chatdefender.infrastructure.annotations.ChatDefenderCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;

public class RegisterUtils {


	// -------------------------------------------------- //


	public static int registerCommands(String packageName) {

		try {
			int counter = 0;
			for(Class<?> instance : getClasses(packageName)) {
				if(!CommandExecutor.class.isAssignableFrom(instance)) continue;
				if(instance.getAnnotation(ChatDefenderCommand.class) == null) continue;

				ChatDefenderCommand command = instance.getAnnotation(ChatDefenderCommand.class);
				CommandExecutor commandExecutor = (CommandExecutor) instance.getDeclaredConstructor().newInstance();

				PluginCommand pluginCommand = Main.getInstance().getCommand(command.NAME());
				if(pluginCommand == null) {
					ConsoleLogger.error("The command '" + command.NAME() + "' has not been registered in the plugin.yml file.");
					continue;
				}

				pluginCommand.setExecutor(commandExecutor);
				counter++;
			}

			return counter;

		} catch(Exception exception) {
			ConsoleLogger.error("An error has occurred while registering commands : " + exception.getMessage());
		}

		return 0;
	}


	public static int registerListeners(String packageName) {

		try {

			int counter = 0;
			for(Class<?> instance : getClasses(packageName)) {

				if(Listener.class.isAssignableFrom(instance)) {
					counter++;
					Listener listener = (Listener) instance.getDeclaredConstructor().newInstance();
					Main.getInstance().getServer().getPluginManager().registerEvents(listener, Main.getInstance());
				}
			}

			return counter;

		} catch(Exception exception) {
			ConsoleLogger.error("An error has occurred while registering listeners : " + exception.getMessage());
		}

		return 0;
	}

	// -------------------------------------------------- //


	private static Set<Class<?>> getClasses(String packageName) throws Exception {
		Set<Class<?>> classes = new HashSet<>();

		String path = packageName.replace('.', '/');
		try (JarFile jarFile = new JarFile(Main.getInstance().getPluginFile())) {
			var entries = jarFile.entries();

			while (entries.hasMoreElements()) {
				var entry = entries.nextElement();
				String name = entry.getName();

				if (name.startsWith(path) && name.endsWith(".class") && !entry.isDirectory()) {
					String className = name.replace('/', '.').replace(".class", "");
					classes.add(Class.forName(className));
				}
			}
		}

		return classes;
	}


	// -------------------------------------------------- //
}
