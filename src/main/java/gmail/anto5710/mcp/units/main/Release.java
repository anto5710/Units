package gmail.anto5710.mcp.units.main;

import java.util.List;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;

public class Release {
	private Knight k ;
	private final int CHARGE = 10*20;
	private final int ENEMY_LIMIT = 3;
	
	private Timer timer = new Timer(CHARGE);
	
	public Release(Knight k) {
		this.k = k;
	}

	public boolean canPerform(){
		Zombie z = k.getZombie();
		return excessiveEnemies(z) && timer.timeElapsed() && k.getState()==KnightState.NORMAL;
	}
	
	public void perform(){
		k.setState(KnightState.RELEASE);
		
		Zombie z= k.getZombie();
		List<Entity> mobs = z.getNearbyEntities(3, 3, 3);
		
		for(Entity e : mobs){
			if(!Knight.isFriendWith(e) && e instanceof Damageable){
				release(z, (Damageable)e);
			}
		}
		k.setState(KnightState.NORMAL);
	}
	
	private boolean excessiveEnemies(Zombie k){
		List<Entity> mobs = k.getNearbyEntities(3, 3, 3);
		int cnt = 0; 
		
		for(Entity e : mobs){
			if(Knight.isFriendWith(e)) continue;
			cnt++;
		}
		return cnt > ENEMY_LIMIT;
	}
	
	private void release(Zombie k, Damageable e) {
		Vector dif = e.getLocation().subtract(k.getLocation()).toVector();
		dif.setY(dif.getY()+1);
		dif.multiply(4/dif.length());
		
		e.setVelocity(dif);
		e.damage(dif.length(), k);
	}
}
