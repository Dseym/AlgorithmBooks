package bot_plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;



public class commands implements CommandExecutor {

	main plugin;
	String tagPlugin;
	
	public commands(final main main, String tagPlugin) {
        
		this.plugin = main;
		this.tagPlugin = tagPlugin;
		
    }
	
	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String name = (String)sender.getName();
		
		if(args.length != 1) {
			
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Команды:\n/bot start\n/bot stop");
			return true;
			
		}	
		
		if(args[0].equalsIgnoreCase("start")) {		
			
			ItemStack item = Bukkit.getPlayer(name).getInventory().getItemInHand();
			if(item.getType() == Material.BOOK_AND_QUILL) {
				
				BookMeta book = (BookMeta)item.getItemMeta();
				
				if(book.getPageCount() > 0) {
					
					compile compile = new compile();
					compile.start(String.join("", book.getPages()), name, plugin, tagPlugin);
					
				} else {
					
					Bukkit.getPlayer(name).sendMessage(tagPlugin + "В книге пусто!");
					
				}
				
				
			} else {
				
				Bukkit.getPlayer(name).sendMessage(tagPlugin + "Держите книгу с алгоритмом в руках!");
				
			}
			
		} else if(args[0].equalsIgnoreCase("stop")) {
			
			compile.time.cancel();
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Вы принудительно завершили алгоритм!");
			
		} else {
			
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Команды:\n/bot start\n/bot stop");
			
		}
		
		return true;
		
	}

}
