

public class PhilThread extends Thread{
	private PhilMonitor m;
	private int id;
	private PhilosophersController p;
	
	
	public PhilThread (int id, PhilMonitor m, PhilosophersController p) {
		this.id = id;
		this.m = m;
		this.p = p;
	}
	
	public void run(){
		super.run();
		while(!p.getStop()) {
			m.getTwoSticks(id);
			p.eat(id);
			try {
				sleep(2000);//eating
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.notEat(id);
			m.finished(id);
			try {
				sleep(2000);//thinking
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
