package gmail.anto5710.mcp.units.main;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class KnightTargeter implements Listener {
	private Knight k;
	private static final int WAIT_FOR_RETARGET = 50;

	public KnightTargeter(Knight k) {
		this.k = k;
	}

	public void updateTarget() {
		if(!k.hasTarget()|| k.stoppedFor(WAIT_FOR_RETARGET)) targetNearest(k);
	}
	
	private void targetNearest(Knight k){
		target(getNearestTarget(k.getZombie()));
	}
	
	private void target(LivingEntity le){
		Zombie z = k.getZombie();
		
		if(z.getTarget()!=le) z.setTarget(le);
	}
	
	private static LivingEntity getNearestTarget(Creature crt) {
		double maxDis = Util.getAttribute(crt, Attribute.GENERIC_FOLLOW_RANGE);
		
		List<Entity> mobs = crt.getNearbyEntities(maxDis, maxDis, maxDis);
		LivingEntity target = null;
		double d = maxDis, cur;
		
		for(Entity e : mobs){
			if(Knight.isFriendWith(e)) continue;
				
			cur =  Util.distance(e, crt);
			if(cur <= d){
				d = cur;
				target = (LivingEntity) e;
			}
		}
		return target;
	}
}
