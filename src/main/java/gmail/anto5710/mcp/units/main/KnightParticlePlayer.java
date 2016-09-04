//package gmail.anto5710.mcp.units.main;
//
//import java.awt.Color;
//import java.util.HashMap;
//
//import org.bukkit.Location;
//import org.bukkit.Particle;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.Horse;
//import org.bukkit.entity.Zombie;
//import org.bukkit.scheduler.BukkitRunnable;
//import org.bukkit.util.Vector;
//
//public class KnightParticlePlayer extends BukkitRunnable{
//	private Units main;
//	private KnightMovUpdater movUpdater;
//	
//	private HashMap<Zombie, Double> hMap = new HashMap<>();
//	private HashMap<Zombie, Double> yMap = new HashMap<>();
//
//	private static final double r = 1.3D;
// 	private static final double hadd = 0.15;
//	private static final double ymax = 2;
//	private static double yadd = -ymax/30;
//	
// 	
//	public KnightParticlePlayer(Units main){
//		this.main = main;
//		this.movUpdater = main.getMovUpdater();
//		
//		runTaskTimer(main, 0, 2);
//	}
//	
//	@Override
//	public void run() {
//		for(Knight k: main.getKnights()){
//			Zombie z = k.getZombie();
//			
//			if(!hMap.containsKey(z)) put(z);
//			
//			if(movUpdater.stoppedFor(k, 5)){
//				playAura(k, 2);
//			}else if(k.isRidingHorse()){
//				playHorse(k); 
//			}
//		}
//	}
//
//	private void playHorse(Knight k) {
//		Entity h = k.getZombie().getVehicle();
//		Particle p = k.getState().getHorse();
//		Util.spawnParticle(h.getLocation(), p, 8, 0);
//	}
//
//	private void put(Zombie z){
//		hMap.put(z, 0D);
//		yMap.put(z, 0D);
//	}
//	
//	private void playAura(Knight k, int count){
//		Zombie z = k.getZombie();
//		Location center = z.getLocation();
//		Location cur1 = center, cur2 = center;
//		Particle p = k.getState().getAura();
//		
//		for(int c = 0; c < count; c++){
//			Vector add = getNextVec(z);
//			cur1 = center.clone().add(add);
//			cur2 = center.clone().add(reverseOnYaxis(add));
//			
//			Util.spawnParticle(cur1, p);
//			updateVariations(z);
//			
//			Util.spawnParticle(cur2, p);
//		}
//	}
//	
//	private Vector reverseOnYaxis(Vector v){
//		double y = v.getY();
//		
//		return v.clone().multiply(-1).setY(y);
//	}
//
//	private void updateVariations(Zombie k){
//		double y = yMap.get(k);
//		if(y<0 || ymax <= y) yadd *= -1;
//
//		Util.increaseVal(yMap, k, yadd);
//		Util.increaseVal(hMap, k, hadd);
//	}
//	
//	private Vector getNextVec(Zombie k) {
//		Vector v;
//		double h = hMap.get(k);
//		double y = yMap.get(k);
//		
//		v =new Vector(Math.cos(h*r), y, Math.sin(h*r));
//		return v;
//	}
//}
