package gmail.anto5710.mcp.units.main;

import org.bukkit.Particle;

public enum KnightState {
	NORMAL(Particle.FIREWORKS_SPARK,Particle.CLOUD), 
	RELEASE(Particle.FIREWORKS_SPARK, Particle.CLOUD);
	
	private Particle a;
	private Particle h;
	
	private KnightState(Particle knight, Particle horse){
		this.a = knight;
		this.h = horse;
	}
	
	public Particle getAura(){
		return a;
	}
	public Particle getHorse(){
		return h;
	}
}
