package gmail.anto5710.mcp.units.main;

public class Timer {
	private long startMsec;
	private long endMsec;
	private final int limit;
	private boolean elapsed;
	
	public Timer(int limit){
		this.limit = limit*50;
	}
	
	public boolean timeElapsed(){
		elapsed = endMsec <= System.currentTimeMillis();
		if(elapsed) reset();
		
		return elapsed;
	}
	
	public void reset(){
		startMsec = System.currentTimeMillis();
		endMsec = startMsec + limit;
	}
}
