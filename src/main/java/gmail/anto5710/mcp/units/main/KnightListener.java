package gmail.anto5710.mcp.units.main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class KnightListener implements Listener{
	@EventHandler
	public void onTargetedByFriend(EntityTargetEvent ev){
		Entity e = ev.getEntity();
		
		if(!Knight.isKnight(e)) return;
		if(Knight.isFriendWith(e)) ev.setCancelled(true);
	}
	
	@EventHandler
	public void onDamageByFriend(EntityDamageByEntityEvent ev){
		Entity e = ev.getEntity();
		
		if(!Knight.isKnight(e)) return;
		if(Knight.isFriendWith(Util.getDamager(ev))) ev.setCancelled(true);
	}
	
	@EventHandler
	public void onTargetedFriend(EntityTargetEvent ev){
		Entity e = ev.getEntity();
		
		if(!Knight.isKnight(e)) return;
		if(Knight.isFriendWith(ev.getTarget())) ev.setCancelled(true);
	}
	
	@EventHandler
	public void onFriendDamaged(EntityDamageByEntityEvent ev){
		if(!Knight.isFriendWith(ev.getEntity())) return;
		Entity dmg = ev.getDamager();
		
		if(dmg instanceof LivingEntity){
		}
 	}

	@EventHandler
	public void onKnightDeath(EntityDeathEvent ev){
		Entity e = ev.getEntity();
		if(Knight.isKnight(e)) Units.removeKnight((Zombie)e);
	}
}
