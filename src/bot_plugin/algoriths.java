package bot_plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class algoriths {
	
	@SuppressWarnings("deprecation")
	static void move(String name, String side) {
		
		Vector vector = new Vector(0, 0, 0);
		float rotate = (float)Bukkit.getPlayer(name).getEyeLocation().getYaw();
		double speed = 0.382;
		
		if(side == "forward") {
			
			if(rotate > 45 && 135 >= rotate) {
				
				vector = new Vector(-speed, 0, 0);
				
			} else if(rotate > 135 && 225 >= rotate) {
				
				vector = new Vector(0, 0, -speed);
				
			} else if(rotate > 225 && 315 >= rotate) {
				
				vector = new Vector(speed, 0, 0);
				
			} else if((rotate > 315 && 360 >= rotate) || (rotate > 0 && 45 >= rotate)) {
				
				vector = new Vector(0, 0, speed);
				
			}
			
		} else if(side == "back") {
			
			if(rotate > 45 && 135 >= rotate) {
				
				vector = new Vector(speed, 0, 0);
				
			} else if(rotate > 135 && 225 >= rotate) {
				
				vector = new Vector(0, 0, speed);
				
			} else if(rotate > 225 && 315 >= rotate) {
				
				vector = new Vector(-speed, 0, 0);
				
			} else if((rotate > 315 && 360 >= rotate) || (rotate > 0 && 45 >= rotate)) {
				
				vector = new Vector(0, 0, -speed);
				
			}
			
		} else if(side == "left") {
			
			if(rotate > 45 && 135 >= rotate) {
				
				vector = new Vector(0, 0, speed);
				
			} else if(rotate > 135 && 225 >= rotate) {
				
				vector = new Vector(-speed, 0, 0);
				
			} else if(rotate > 225 && 315 >= rotate) {
				
				vector = new Vector(0, 0, -speed);
				
			} else if((rotate > 315 && 360 >= rotate) || (rotate > 0 && 45 >= rotate)) {
				
				vector = new Vector(speed, 0, 0);
				
			}
			
		} else if(side == "right") {
			
			if(rotate > 45 && 135 >= rotate) {
				
				vector = new Vector(0, 0, -speed);
				
			} else if(rotate > 135 && 225 >= rotate) {
				
				vector = new Vector(speed, 0, 0);
				
			} else if(rotate > 225 && 315 >= rotate) {
				
				vector = new Vector(0, 0, speed);
				
			} else if((rotate > 315 && 360 >= rotate) || (rotate > 0 && 45 >= rotate)) {
				
				vector = new Vector(-speed, 0, 0);
				
			}
			
		} else if(side == "jump") {
			
			vector = new Vector(0, 0.37, 0);
			
		} else if(side == "run") {
			
			if(rotate > 45 && 135 >= rotate) {
				
				vector = new Vector(-speed*2, 0, 0);
				
			} else if(rotate > 135 && 225 >= rotate) {
				
				vector = new Vector(0, 0, -speed*2);
				
			} else if(rotate > 225 && 315 >= rotate) {
				
				vector = new Vector(speed*2, 0, 0);
				
			} else if((rotate > 315 && 360 >= rotate) || (rotate > 0 && 45 >= rotate)) {
				
				vector = new Vector(0, 0, speed*2);
				
			}
			
		}
		
		
		Bukkit.getPlayer(name).setVelocity(vector);
		
	}
	
	
	@SuppressWarnings("deprecation")
	static boolean condition(String name, String cond, String tagPlugin, int line, BukkitTask time) {
		
		boolean isCond = false;
		
		try {
			
			cond = cond.replace(" ", "");
			if(cond.indexOf(">") > -1) {
				
				if(Integer.parseInt(cond.split(">")[0]) > Integer.parseInt(cond.split(">")[1])) {
					
					isCond = true;
					
				} else {
					
					isCond = false;
					
				}
				
			} else if(cond.indexOf("<") > -1) {
				
				if(Integer.parseInt(cond.split("<")[0]) < Integer.parseInt(cond.split("<")[1])) {
					
					isCond = true;
					
				} else {
					
					isCond = false;
					
				}
				
			} else if(cond.indexOf("==") > -1) {
				
				if(cond.split("==")[0].equalsIgnoreCase(cond.split("==")[1])) {
					
					isCond = true;
					
				} else {
					
					isCond = false;
					
				}
				
			}
			
		} catch (Exception e) {
			
			Bukkit.getPlayer(name).sendMessage(tagPlugin + "Что-то пошло не так на " + line + " строке.");
			time.cancel();
			return false;
			
		}
		
		return isCond;
		
	}
	
	@SuppressWarnings("deprecation")
	static void rotate(String str, String name) {
		
		int x = Integer.parseInt(str.split(", ")[0]);
		int y = Integer.parseInt(str.split(", ")[1]);
		Location loc = Bukkit.getPlayer(name).getLocation();
		Bukkit.getPlayer(name).teleport(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw() + x, loc.getPitch() + y));
		
	}


	@SuppressWarnings("deprecation")
	static String lookToBlock(String name) {
		
		return Bukkit.getPlayer(name).getTargetBlock(null, 4).getType().toString();
		
	}
	
}
