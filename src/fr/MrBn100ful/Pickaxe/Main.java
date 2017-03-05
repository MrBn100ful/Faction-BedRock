package fr.MrBn100ful.Pickaxe;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;


public class Main extends JavaPlugin implements Listener {

	public ArrayList<Player> cooldown = new ArrayList<Player>();
	FileConfiguration config;
	File cfile;

	@Override
	public void onDisable() {
		System.out.println("[Faction BedRock] unloaded");
	}

	@Override
	public void onEnable() {
		
		config = getConfig();
		
		String error = "FactionBedRock.Error.Message";
		
		config.addDefault(error, "&f&lError »&c you don't have the permission to do that !");
		
		String wildernessconfig = "FactionBedRock.Wilderness.Name";
		
		config.addDefault(wildernessconfig, "Wilderness");
		
		String safezoneconfig = "FactionBedRock.SafeZone.Name";
		
		config.addDefault(safezoneconfig, "Safezone");
		
		String warzoneconfig = "FactionBedRock.WarZone.Name";
		
		config.addDefault(warzoneconfig, "Warzone");
		
		config.addDefault("FactionBedRock.BanWorld", (Object)true);
		
		String bannetherconfig = "FactionBedRock.BanWorldNether.Name";
		
		config.addDefault(bannetherconfig, "world_nether");
		
		String banendconfig = "FactionBedRock.BanWorldEnd.Name";
		
		config.addDefault(banendconfig, "world_the_end");
		
		config.options().copyDefaults(true);
		
		saveConfig();
		
		cfile = new File(getDataFolder(), "config.yml");
		
		Bukkit.getServer().getPluginManager().registerEvents((Listener) this, this);
		
		System.out.println("[Faction BedRock] loaded");
	}

	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();

		if (event.getAction().equals(org.bukkit.event.block.Action.LEFT_CLICK_BLOCK)) {
			
			if (player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
			
				if (event.getClickedBlock().getType().equals(Material.BEDROCK)) {
					
					if (!cooldown.contains(player)) {
						
						cooldown.add(player);
				
					
					

						Faction factionAt = Board.getInstance()
								.getFactionAt(new FLocation(event.getClickedBlock().getLocation()));

						int y = event.getClickedBlock().getLocation().getBlockY();
						String testloc = ChatColor.stripColor(factionAt.getTag());

						FPlayer fP = FPlayers.getInstance().getByPlayer(player);
						String testplayer = ChatColor.stripColor(fP.getTag());

						String wilderness = this.getConfig().getString("FactionBedRock.Wilderness.Name");

						String safezone = this.getConfig().getString("FactionBedRock.SafeZone.Name");
						
						String warzone = this.getConfig().getString("FactionBedRock.WarZone.Name");
						
						//debug
						//Bukkit.broadcastMessage(warzone + safezone + wilderness + this.getConfig().getString("FactionBedRock.BanWorldNether.Name") + this.getConfig().getString("FactionBedRock.BanWorldEnd.Name") + player.getLocation().getWorld().getName() );
						
						if (this.config.getBoolean("FactionBedRock.BanWorld")) {
						
						if (!player.getLocation().getWorld().getName().equals( this.getConfig().getString("FactionBedRock.BanWorldNether.Name")) && (!player.getLocation().getWorld().getName().equals( this.getConfig().getString("FactionBedRock.BanWorldEnd.Name"))) ) {
						
						if (!testloc.equals(safezone) && !testloc.equals(warzone)) {
							
							if (y != 0 && y != 1 && y != 2 && y != 3 && y != 4 && y != 255 && y != 254 && y != 253) {
							
								if (testloc.equals(wilderness)) {
									
									if (player.getLocation().getWorld().getName().equals( this.getConfig().getString("FactionBedRock.BanWorldNether.Name")))
									{
										if (y != 124 && y !=125 && y != 126 && y != 127){
											event.getClickedBlock().setType(Material.AIR);
											
											ItemStack bedrock = new ItemStack(Material.BEDROCK);
											
											player.getInventory().addItem(bedrock);
											
										}
									}else {
										
										event.getClickedBlock().setType(Material.AIR);
										
										ItemStack bedrock = new ItemStack(Material.BEDROCK);
										
										player.getInventory().addItem(bedrock);
										
									}

									

								} else if (testloc == testplayer) {

									if (player.getLocation().getWorld().getName().equals( this.getConfig().getString("FactionBedRock.BanWorldNether.Name")))
									{
										if (y != 124 && y !=125 && y != 126 && y != 127){
											event.getClickedBlock().setType(Material.AIR);
											
											ItemStack bedrock = new ItemStack(Material.BEDROCK);
											
											player.getInventory().addItem(bedrock);
											
										}else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
									}else {
										
										event.getClickedBlock().setType(Material.AIR);
										
										ItemStack bedrock = new ItemStack(Material.BEDROCK);
										
										player.getInventory().addItem(bedrock);
										
									}
									
							    }else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
						    }else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
						  }else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
					   }else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
				   }else {
						
						if (!testloc.equals(safezone) && !testloc.equals(warzone)) {
							
							if (y != 0 && y != 1 && y != 2 && y != 3 && y != 4 && y != 255 && y != 254 && y != 253) {
							
								if (testloc.equals(wilderness)) {

									if (player.getLocation().getWorld().getName().equals( this.getConfig().getString("FactionBedRock.BanWorldNether.Name")))
									{
										if (y != 124 && y !=125 && y != 126 && y != 127){
											event.getClickedBlock().setType(Material.AIR);
											
											ItemStack bedrock = new ItemStack(Material.BEDROCK);
											
											player.getInventory().addItem(bedrock);
											
										}else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
									}else {
										
										event.getClickedBlock().setType(Material.AIR);
										
										ItemStack bedrock = new ItemStack(Material.BEDROCK);
										
										player.getInventory().addItem(bedrock);
										
									}

								} else if (testloc == testplayer) {

									if (player.getLocation().getWorld().getName().equals( this.getConfig().getString("FactionBedRock.BanWorldNether.Name")))
									{
										if (y != 124 && y !=125 && y != 126 && y != 127){
											event.getClickedBlock().setType(Material.AIR);
											
											ItemStack bedrock = new ItemStack(Material.BEDROCK);
											
											player.getInventory().addItem(bedrock);
											
										}else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
									}else {
										
										event.getClickedBlock().setType(Material.AIR);
										
										ItemStack bedrock = new ItemStack(Material.BEDROCK);
										
										player.getInventory().addItem(bedrock);
										
									}
									
								}else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
							}else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
						}else player.sendMessage(String.valueOf(this.getConfig().getString("FactionBedRock.Error.Message").replaceAll("&0",new StringBuilder().append((Object) ChatColor.BLACK).toString()).replaceAll("&1",new StringBuilder().append((Object) ChatColor.DARK_BLUE).toString()).replaceAll("&2",new StringBuilder().append((Object) ChatColor.DARK_GREEN).toString()).replaceAll("&3",new StringBuilder().append((Object) ChatColor.DARK_AQUA).toString()).replaceAll("&4",new StringBuilder().append((Object) ChatColor.DARK_RED).toString()).replaceAll("&5",new StringBuilder().append((Object) ChatColor.DARK_PURPLE).toString()).replaceAll("&6",new StringBuilder().append((Object) ChatColor.GOLD).toString()).replaceAll("&7",new StringBuilder().append((Object) ChatColor.GRAY).toString()).replaceAll("&8",new StringBuilder().append((Object) ChatColor.DARK_GRAY).toString()).replaceAll("&9",new StringBuilder().append((Object) ChatColor.BLUE).toString()).replaceAll("&a",new StringBuilder().append((Object) ChatColor.GREEN).toString()).replaceAll("&b",new StringBuilder().append((Object) ChatColor.AQUA).toString()).replaceAll("&c",new StringBuilder().append((Object) ChatColor.RED).toString()).replaceAll("&d",new StringBuilder().append((Object) ChatColor.LIGHT_PURPLE).toString()).replaceAll("&e",new StringBuilder().append((Object) ChatColor.YELLOW).toString()).replaceAll("&f",new StringBuilder().append((Object) ChatColor.WHITE).toString()).replaceAll("&l",new StringBuilder().append((Object) ChatColor.BOLD).toString()).replaceAll("&o",new StringBuilder().append((Object) ChatColor.ITALIC).toString()).replaceAll("&r",new StringBuilder().append((Object) ChatColor.RESET).toString()).replaceAll("&n",new StringBuilder().append((Object) ChatColor.UNDERLINE).toString()).replaceAll("&m",new StringBuilder().append((Object) ChatColor.STRIKETHROUGH).toString()).replaceAll("&k",new StringBuilder().append((Object) ChatColor.MAGIC).toString())));
					   
				   }
			   }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						
						public void run() {
						
							cooldown.remove(player);
						
						}
					}, 5);
		  }
	   }
    }
}
}
