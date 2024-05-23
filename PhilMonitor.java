

public class PhilMonitor {
	private boolean[] freeSticks ;
	private int[] eatCount;
	private int minEatCount=0;//the minimum times a philosopher ate;
	
	public PhilMonitor (boolean[] freeSticks, int[] eatCount) {
		this.freeSticks = freeSticks;
		this.eatCount = eatCount;
	}
	
	public synchronized void getTwoSticks(int id) {
		while(eatCount[id]!=minEatCount) {// to prevent starvation.
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int first, second;
		if(id==4) {//the fifth philosopher
			first = 0;
			second = 4;
		}
		else {
			first =id;
			second= id+1;
		}
		
		while(!freeSticks[first]||!freeSticks[second]) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		freeSticks[first] = false;
		freeSticks[second] = false;
		
		
	}
	
	
	public synchronized void finished(int id) {
		int first, second;
		if(id==4) {//the fifth philosopher
			first = 0;
			second = 4;
		}
		else {
			first =id;
			second= id+1;
		}
		eatCount[id]++;
		setMinEatCount();
		freeSticks[first] = true;
		freeSticks[second] = true;
		notifyAll();
	}
	
	private void setMinEatCount() {
		minEatCount = eatCount[0];
		for(int i=1; i<eatCount.length; i++) {
			if(eatCount[i]< minEatCount)
				minEatCount = eatCount[i];
		}
	}
}
