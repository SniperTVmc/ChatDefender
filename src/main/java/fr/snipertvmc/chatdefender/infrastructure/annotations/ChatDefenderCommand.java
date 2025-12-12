package fr.snipertvmc.chatdefender.infrastructure.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ChatDefenderCommand {
	String NAME();
}
