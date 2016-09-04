package gmail.anto5710.mcp.units.main;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Knight implements Listener {
	private Zombie z;
	private Horse h;

	private KnightTargeter targeter = new KnightTargeter(this);
	
	private Release release = new Release(this);
	
	private KnightState state = KnightState.NORMAL;
	private Location last;
	
	public static final String knightID = "기사";
	private static final double maxDisSqd = 0.3 * 0.3; 
	
	private int stoppedTicks = 0;
	private Timer targetTimer = new Timer(10);
	
	public Knight(Location loc) {
		this.last = loc;
		
		spawn(loc);
		Units.addKnight(this);
	}
	
	private void spawn(Location loc) {
		World w = loc.getWorld();
		z = w.spawn(loc, Zombie.class);
		z.setCustomName(knightID);
		z.setCustomNameVisible(true);
		
		z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
		z.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		z.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		z.getEquipment().setBoots(new ItemStack(Material.IRON_HELMET));
		z.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
		
		h = w.spawn(loc, Horse.class);
		h.setAdult();
		h.setTamed(true);
		h.setPassenger(z);
		h.setMaxHealth(20);
		
		h.getInventory().setArmor(new ItemStack(Material.IRON_SPADE));
		h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
		h.setJumpStrength(2);
		Util.setAttribute(z, Attribute.GENERIC_FOLLOW_RANGE, 30);
	}
	
	public Zombie getZombie(){
		return z;
	}
	
	public void setState(KnightState state) {
		this.state = state;
	}
	
	public void tick() {
		if(release.canPerform()) release.perform();
		if(targetTimer.timeElapsed()) targeter.updateTarget();
		
		updateMovement();
	}
	
	private void updateMovement() {
		if(isMoving()) stoppedTicks = 0;
		else{	
			stoppedTicks++;
		}
	}

	public boolean hasTarget(){
		return z.getTarget()!=null;
	}
	
	public Location getLastLoc(){
		Location last = this.last;
		this.last = z.getLocation();
		
		return last;
	}
	
	public KnightState getState() {
		return state;
	}
	
	public boolean isMoving(){
		return maxDisSqd < getLastLoc().distanceSquared(z.getLocation());
	}

	public boolean isRidingHorse() {
		return z.getVehicle() == h;
	}

	public boolean stoppedFor(int i) {
		return i <= stoppedTicks;
	}

	public static boolean isKnight(Entity e) {
		return e instanceof Monster && e.getName().equals(knightID);
	}

	public static boolean isFriendWith(Entity e){
		return e instanceof Monster == false || isKnight(e);
	}
}
