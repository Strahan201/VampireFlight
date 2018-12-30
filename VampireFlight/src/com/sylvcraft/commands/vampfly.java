package com.sylvcraft.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import com.sylvcraft.VampireFlight;

public class vampfly implements TabExecutor {
  VampireFlight plugin;
  
  public vampfly(VampireFlight instance) {
    plugin = instance;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
  	if (!sender.hasPermission(plugin.perms.get("admin"))) return null;
  	return Arrays.asList("reload", "allowflight");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    try {
      if (args.length == 0) {
      	if (sender instanceof Player) {
      		Player p = (Player)sender;
      		if (plugin.allowVampFlightToggle() && !p.hasPermission(plugin.perms.get("admin")) && p.hasPermission(plugin.perms.get("flight"))) {
      			p.setAllowFlight(!p.getAllowFlight());
      			plugin.msg("vampfly-" + ((p.getAllowFlight())?"on":"off"), sender);
      			return true;
      		}
      	}
        plugin.msg("help", sender);
        return true;
      }
    	if (!sender.hasPermission(plugin.perms.get("admin"))) {
    		plugin.msg("access-denied", sender);
    		return true;
    	}
      
      switch (args[0].toLowerCase()) {
      case "allowflight":
      	plugin.allowVampFlightToggle(!plugin.allowVampFlightToggle());
      	plugin.msg("allow-flight-" + ((plugin.allowVampFlightToggle())?"on":"off"), sender);
      	break;
      case "reload":
      	plugin.reloadConfig();
      	plugin.msg("reloaded", sender);
      	break;
      }

      return true;
    } catch (Exception ex) {
      return false;
    }
  }
}
