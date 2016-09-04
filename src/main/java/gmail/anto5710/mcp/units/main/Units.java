package gmail.anto5710.mcp.units.main;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Units extends JavaPlugin{
	private static HashMap<Zombie, Knight> knights = new HashMap<>();
	
//	private KnightSkillPerformer performer = new KnightSkillPerformer(this);
	private KnightListener listener;
	private KnightActivater activater;
//	private KnightParticlePlayer player ;
	
	@Override
	public void onEnable() {
		listener = new KnightListener();
		activater = new KnightActivater(this);
//		player = new KnightParticlePlayer(this);
		
		this.getServer().getPluginManager().registerEvents(listener, this);
	
		super.onEnable();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		Player p = Bukkit.getPlayer(sender.getName());
		int cnt = args.length == 0 ? 0 : Integer.parseInt(args[0]);
		
		spawn(p, cnt);
		return super.onCommand(sender, command, label, args);
	}
	
	private void spawn(Player p, int count) {
		Location loc = p.getLocation();
		
		for(int c = 0; c< count; c++){
			new Knight(loc);
		}
	}
	
	public static Set<Knight> getKnights(){
		return new HashSet<>(knights.values());
	}

	public static void addKnight(Knight k) {
		knights.put(k.getZombie(), k);
	}
	
	public static void removeKnight(Zombie k){
		knights.remove(k);
	}
}
