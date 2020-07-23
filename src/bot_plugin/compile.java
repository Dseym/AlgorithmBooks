package bot_plugin;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;



public class compile implements Runnable {
	
	int i = 1;
	String[] lines;
	String name;
	String tagPlugin;
	String code;
	main plugin;
	static BukkitTask time;
	HashMap<String, String> variables = new HashMap<>();
	HashMap<String, Integer> labels = new HashMap<>();
	boolean isElse = false;
	
	
	
	public static void setTimeout(Runnable runnable, int delay){
	    new Thread(() -> {
	        try {
	            Thread.sleep(delay);
	            runnable.run();
	        }
	        catch (Exception e){
	            System.err.println(e);
	        }
	    }).start();
	}
	
	
	
	@SuppressWarnings("deprecation")
	void start(String code, String name, main plugin, String tagPlugin) {
		
		this.code = code.replace("\n", "").replace(ChatColor.BLACK + "", "");
		lines = this.code.replace("\n", "").split(";");
		this.name = name;
		this.plugin = plugin;
		this.tagPlugin = tagPlugin;
		
		
		if(!lines[0].equalsIgnoreCase("start")) {
			
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Отсуствует 'start'");
			return;
			
		} else if(code.indexOf("end") < 0) {
			
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Отсуствует 'end'");
			return;
			
		}
		
		Bukkit.getPlayer(name).sendMessage(tagPlugin + "Выполнение началось!");
		time = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 5);
		
	}
	
	
	String transVar(String str) {
		
		Pattern pattern = Pattern.compile("&(.*?)&");
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
	    	
	    	for(String var: variables.keySet()) {
	    		
	    		str = str.replace(matcher.group(0), variables.get(var));
	    		
	    	}
	    
	    }
		
		if(str.indexOf("lookToBlock") > -1) {
			
			str = str.replace("lookToBlock", algoriths.lookToBlock(name));
			
		}
		
		return str;
		
	}
	
	
	@SuppressWarnings("deprecation")
	String operation(String value) {
		
		try {
			
			if(value.indexOf(" + ") > -1) {
				
				value = Integer.toString(Integer.parseInt(value.split(" \\+ ")[0]) + Integer.parseInt(value.split(" \\+ ")[1]));
				
			} else if(value.indexOf(" - ") > -1) {
				
				value = Integer.toString(Integer.parseInt(value.split(" \\- ")[0]) - Integer.parseInt(value.split(" \\- ")[1]));
				
			} else if(value.indexOf(" * ") > -1) {
				
				value = Integer.toString(Integer.parseInt(value.split(" \\* ")[0]) * Integer.parseInt(value.split(" \\* ")[1]));
				
			} else if(value.indexOf(" / ") > -1) {
				
				value = Integer.toString(Integer.parseInt(value.split(" \\/ ")[0]) / Integer.parseInt(value.split(" \\/ ")[1]));
				
			} else if(value.indexOf(" % ") > -1) {
				
				value = Integer.toString(Integer.parseInt(value.split(" \\% ")[0]) % Integer.parseInt(value.split(" \\% ")[1]));
				
			} else if(value.indexOf(" ** ") > -1) {
				
				value = Double.toString(Math.pow(Double.parseDouble(value.split(" \\*\\* ")[0]), Double.parseDouble(value.split(" \\*\\* ")[1])));
				
			}
			
			return value;
			
		} catch (Exception e) {
			
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Что-то пошло не так на " + (i+1) + " строке.");
			i = 1;
			time.cancel();
			return "";
			
		}
		
	}
	
	
	void condition(String[] lines, String name) {
		
		String cond = lines[i].split("if ")[1];
		cond = transVar(cond);
		
		int i2 = 1;
		while(lines[i+i2].indexOf(lines[i].split("if ")[0] + "  ") > -1) {
			
			i2++;
			
		}
		
		
		if(!algoriths.condition(name, cond, tagPlugin, i+1, time)) {
			
			i += i2-1;
			isElse = true;
			
		}
		
	}
	
	
	@SuppressWarnings("deprecation")
	void code() {
		
		try {
			
			if(lines[i].replace("  ", "").equalsIgnoreCase("end")) {
				
				i = 1;
				time.cancel();
				Bukkit.getPlayer(name).sendMessage(tagPlugin + "Алгоритм успешно выполнился!");
				
			} else if(lines[i].replace("  ", "").equalsIgnoreCase("forward")) {
				
				algoriths.move(name, "forward");
				
			} else if(lines[i].replace("  ", "").equalsIgnoreCase("back")) {
				
				algoriths.move(name, "back");
				
			} else if(lines[i].replace("  ", "").equalsIgnoreCase("left")) {
				
				algoriths.move(name, "left");
				
			} else if(lines[i].replace("  ", "").equalsIgnoreCase("right")) {
				
				algoriths.move(name, "right");
				
			} else if(lines[i].replace("  ", "").equalsIgnoreCase("jump")) {
				
				algoriths.move(name, "jump");
				
			} else if(lines[i].replace("  ", "").equalsIgnoreCase("run")) {
				
				algoriths.move(name, "run");
				
			} else if(lines[i].replace("  ", "").split(" = ")[0].equalsIgnoreCase("rotate")) {
				
				String str = lines[i].replace("  ", "").split(" = ")[1];
				algoriths.rotate(transVar(str), name);
				
			} else if(lines[i].replace("  ", "").split(" ")[0].equalsIgnoreCase("if")) {
				
				condition(lines, name);
				
			} else if(lines[i].replace("  ", "").equalsIgnoreCase("else")) {
				
				if(!isElse) {
					
					int i2 = 1;
					while(lines[i+i2].indexOf(lines[i].split("else")[0] + "  ") > -1) {
						
						lines[i+i2] = lines[i+i2].replace("  ", "");
						i2++;
						
					}
					i += i2-1;
					
				}
				isElse = false;
				
			} else if(lines[i].replace("  ", "").toCharArray()[0] == '&') {
				
				String key = lines[i].replace("  ", "").split("&")[0].split(" = ")[0];
				String value = lines[i].replace("  ", "").split(" = ")[1];
				value = transVar(value);
				
				value = operation(value);
				
				if(variables.containsKey(key)) {
					
					variables.replace(key, value);
					
				} else {
					
					variables.put(key, value);
					
				}
				
			} else if(lines[i].replace("  ", "").toCharArray()[0] == '#') {
				
				String key = lines[i].replace("  ", "").split("#")[1];
				int value = i;
				
				if(labels.containsKey(key)) {
					
					labels.replace(key, value);
					
				} else {
					
					labels.put(key, value);
					
				}
				
			} else if(lines[i].replace("  ", "").split(" ")[0].equalsIgnoreCase("goto")) {
				
				if(labels.containsKey(lines[i].replace("  ", "").split(" ")[1])) {
					
					i = labels.get(lines[i].replace("  ", "").split(" ")[1]);
					
				}
				
			} else if(lines[i].replace("  ", "").split(" ")[0].equalsIgnoreCase("write")) {
				
				String str = lines[i].replace("  ", "").split(" = ")[1];
				str = operation(transVar(str));
				Bukkit.getPlayer(name).sendMessage(tagPlugin + "Вывод: " + str);
				
			} else if(lines[i].replace("  ", "").split(" ")[0].equalsIgnoreCase("timeout")) {
				
				time.cancel();
				setTimeout(() -> time = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 5), Integer.parseInt(operation(transVar(lines[i].replace("  ", "").split(" ")[1]))));
				
			} else if(lines[i].replace("  ", "").split(" ")[0].equalsIgnoreCase("slot")) {
				
				Bukkit.getPlayer(name).getInventory().setHeldItemSlot(Integer.parseInt(operation(transVar(lines[i].replace("  ", "").split(" ")[1]))));
				
			} else if(lines[i].replace("  ", "").split(" ")[0].equalsIgnoreCase("click")) {
				
				if(lines[i].replace("  ", "").split(" ")[1].equalsIgnoreCase("left")) {
					
					Bukkit.getPlayer(name).getTargetBlock(null, 4).breakNaturally();
					
				} else if(lines[i].replace("  ", "").split(" ")[1].equalsIgnoreCase("right")) {
					
					Bukkit.getPlayer(name).getTargetBlock(null, 4).setType(Bukkit.getPlayer(name).getItemInHand().getType());
					
				}
				
			} else if(lines[i].replace("  ", "").split(" = ")[0].equalsIgnoreCase("send")) {
				
				if(lines[i].replace("  ", "").split(" = ")[1].equalsIgnoreCase("/bot start")) {
					
					time.cancel();
					
				}
				Bukkit.getPlayer(name).chat(lines[i].replace("  ", "").split(" = ")[1]);
				
			} else if(lines[i].replace("  ", "").split(" ")[0].equalsIgnoreCase("drop")) {
				
				if(Bukkit.getPlayer(name).getItemInHand().getType() != Material.AIR) {
					
					Bukkit.getPlayer(name).getLocation().getWorld().dropItem(Bukkit.getPlayer(name).getLocation(), Bukkit.getPlayer(name).getItemInHand());
					Bukkit.getPlayer(name).setItemInHand(null);
					
				}
				
			} else {
				
				Bukkit.getPlayer(name).sendMessage(tagPlugin + "Неизвестная команда на " + (i+1) + " строке.");
				time.cancel();
				
			}
			
			i++;
			
		} catch (Exception e) {
			
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Что-то пошло не так на " + (i+1) + " строке.");
			
		}
		
	}



	public void run() {
		
		code();
		
	}
	
}
