package gmail.anto5710.mcp.units.main;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

public class Util {
	
	static final Set<Material>trsps = new HashSet<>();
	static{ setTrsps(); }
	
	private static void setTrsps(){
		for(Material m : Material.values()){
			if(m.isBlock() && m.isTransparent()) trsps.add(m);
		}
	}
	
	public static Location getTargetBlock(LivingEntity le, int maxDis){
		return le.getTargetBlock(trsps, maxDis).getLocation();
	}
	
	public static Entity getDamager(EntityDamageByEntityEvent ev){
		Entity damager = ev.getDamager();
		if(damager instanceof Projectile) damager = (Entity) ((Projectile) damager).getShooter(); 
		
		return damager;
	}
	
	public static double distance(Entity e1, Entity e2){
		return distance(e1, e2.getLocation());
	}
	
	public static double distance(Entity e, Location loc){
		return e.getLocation().distance(loc);
	}
	
	public static void createExplosion(Location loc, float power, boolean setFire, boolean breakBlocks) {
		World w = loc.getWorld();
		w.createExplosion(loc.getX(), loc.getY(), loc.getZ(), power, setFire, breakBlocks);
	}
	
	public static double random (double r){
		return Math.random()*2*r - r;
	}
	
	public static Location randomLoc (Location loc, double r){
		return loc.add(random(r), random(r), random(r));
	}
	
	public static void spawnParticle(Location loc, Particle p){
		World w = loc.getWorld();
		w.spawnParticle(p, loc.getX(), loc.getY(), loc.getZ(), 0, 0, 0, 0, 0);
	}

	public static void spawnParticle(Location loc, Particle p, int ticks, int speed){
		World w = loc.getWorld();
		w.spawnParticle(p, loc.getX(), loc.getY(), loc.getZ(), ticks, 0, 0, 0, speed);
	}
	
	public static void addPotionEffect(LivingEntity le, PotionEffect pef, boolean invisible){
		le.addPotionEffect(pef, invisible);
	}

	public static Vector unitVector(Location loc1, Location loc2){
		return loc2.clone().subtract(loc1).toVector().normalize();
	}

	public static void setAttribute(Attributable att, Attribute at, double val){
		att.getAttribute(at).setBaseValue(val);
	}
	
	public static double getAttribute(Attributable att, Attribute at) {
		return att.getAttribute(at).getBaseValue();
	}

}
