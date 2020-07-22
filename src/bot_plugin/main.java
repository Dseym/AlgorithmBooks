package bot_plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;



public class main extends JavaPlugin {
	
	String tagPlugin = ChatColor.RESET + "[" + ChatColor.BLUE + "AlgorithmBooks" + ChatColor.RESET + "] ";
	
	public void onEnable() {
		
		this.getCommand("bot").setExecutor((CommandExecutor)new commands(this, this.tagPlugin));
		this.getLogger().info("Started!");
		
	}
	
}
