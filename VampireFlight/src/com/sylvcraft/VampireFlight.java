package com.sylvcraft;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import com.sylvcraft.events.PlayerToggleFlight;

import com.sylvcraft.commands.vampfly;


public class VampireFlight extends JavaPlugin {
	public HashMap<String, Permission> perms = new HashMap<String, Permission>();
	
  @Override
  public void onEnable() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new PlayerToggleFlight(this), this);
    getCommand("vampfly").setExecutor(new vampfly(this));
    saveDefaultConfig();
    perms.put("admin", new Permission("vampfly.admin"));
    perms.put("vampire", new Permission("vampfly.vampire", PermissionDefault.FALSE));
    perms.put("phantom", new Permission("vampfly.vampire.phantom", PermissionDefault.FALSE));
    perms.put("flight", new Permission("vampfly.flight", PermissionDefault.FALSE));
  }
  
  public Boolean allowVampFlightToggle() {
  	return getConfig().getBoolean("config.allow-flight", false);
  }
  
  public void allowVampFlightToggle(Boolean setting) {
  	getConfig().set("config.allow-flight", setting);
  	saveConfig();
  }

  public void msg(String msgCode, CommandSender sender) {
  	String tmp = getConfig().getString("messages." + msgCode, msgCode);
  	for (String m : tmp.split("%br%")) {
  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
  	}
  }

  public void msg(String msgCode, CommandSender sender, HashMap<String, String> data) {
  	String tmp = getConfig().getString("messages." + msgCode, msgCode);
  	for (Map.Entry<String, String> mapData : data.entrySet()) {
  	  tmp = tmp.replace(mapData.getKey(), mapData.getValue());
  	}
  	msg(tmp, sender);
  }
}