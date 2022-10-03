package com.edgeburnmedia.spectatornightvision;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class SpectatorNightVision extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode() == GameMode.SPECTATOR) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
			player.sendMessage("§aYou have been given night vision because you are in spectator mode.");
		}
	}

	@EventHandler
	public void onPlayerChangeGamemode(PlayerGameModeChangeEvent event) {
		Player player = event.getPlayer();
		GameMode oldGameMode = player.getGameMode();
		GameMode newGameMode = event.getNewGameMode();
		if (oldGameMode == GameMode.SPECTATOR) {
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			player.sendMessage("§cNight vision disabled because you are no longer in spectator mode.");
		}
		if (newGameMode == GameMode.SPECTATOR) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
			player.sendMessage("§aNight vision enabled because you are in spectator mode.");
		}
	}
}
