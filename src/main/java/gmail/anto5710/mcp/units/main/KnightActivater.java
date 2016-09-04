package gmail.anto5710.mcp.units.main;

import org.bukkit.scheduler.BukkitRunnable;

public class KnightActivater extends BukkitRunnable{
	public KnightActivater(Units main){
		runTaskTimer(main, 0, 1);
	}
	
	@Override
	public void run(){
		for(Knight k: Units.getKnights()){
			k.tick();
		}
	}
}
