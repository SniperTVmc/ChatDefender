package fr.snipertvmc.chatdefender.managers;

import fr.snipertvmc.chatdefender.Main;
import net.byteflux.libby.BukkitLibraryManager;
import net.byteflux.libby.Library;

public class LibraryManager {


	// -------------------------------------------------- //


	public LibraryManager() {
		BukkitLibraryManager bukkitLibraryManager = Main.getInstance().getBukkitLibraryManager();
		bukkitLibraryManager.addMavenCentral();
		loadEssentialLibraries();
	}


	// -------------------------------------------------- //


	public void loadEssentialLibraries() {

		BukkitLibraryManager bukkitLibraryManager = Main.getInstance().getBukkitLibraryManager();

		bukkitLibraryManager.loadLibrary(Library.builder()
				.groupId("com.squareup.moshi")
				.artifactId("moshi")
				.version("1.15.2")
				.build());

		bukkitLibraryManager.loadLibrary(Library.builder()
				.groupId("com.squareup.okio")
				.artifactId("okio")
				.version("3.16.4")
				.build());

		bukkitLibraryManager.loadLibrary(Library.builder()
				.groupId("com.squareup.okhttp3")
				.artifactId("okhttp")
				.version("5.3.2")
				.build());

		bukkitLibraryManager.loadLibrary(Library.builder()
				.groupId("com.squareup.okio")
				.artifactId("okio-jvm")
				.version("3.16.4")
				.build());

		bukkitLibraryManager.loadLibrary(Library.builder()
				.groupId("org.jetbrains.kotlin")
				.artifactId("kotlin-stdlib")
				.version("2.2.21")
				.build());
	}


	// -------------------------------------------------- //
}
