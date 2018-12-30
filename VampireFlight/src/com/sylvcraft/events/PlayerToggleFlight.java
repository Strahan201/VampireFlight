package com.sylvcraft.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.sylvcraft.VampireFlight;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

import org.bukkit.event.player.PlayerToggleFlightEvent;


public class PlayerToggleFlight implements Listener {
  VampireFlight plugin;
  
  public PlayerToggleFlight(VampireFlight instance) {
    plugin = instance;
  }

	@EventHandler
  public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
		if (e.isFlying() && e.getPlayer().hasPermission(plugin.perms.get("vampire"))) {
  		MobDisguise mobDisguise = new MobDisguise((e.getPlayer().hasPermission(plugin.perms.get("phantom")))?DisguiseType.PHANTOM:DisguiseType.BAT);
  		DisguiseAPI.disguiseToAll(e.getPlayer(), mobDisguise);
  		plugin.msg((e.getPlayer().hasPermission(plugin.perms.get("phantom")))?"phantom-on":"bat-on", e.getPlayer());
		}
		if (!e.isFlying() && e.getPlayer().hasPermission(plugin.perms.get("vampire"))) {
			DisguiseAPI.undisguiseToAll(e.getPlayer());
  		plugin.msg((e.getPlayer().hasPermission(plugin.perms.get("phantom")))?"phantom-off":"bat-off", e.getPlayer());
		}
  }
}