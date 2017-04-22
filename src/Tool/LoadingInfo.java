package Tool;

public class LoadingInfo {

	
	private int next;
	private int done;
	
	
	public LoadingInfo () {
		next = 0;
		done = 0;
	}
	
	public void setNext (int i) {
		next = i;
	}
	
	public void addDone() {
		done = done + 1;
		
	}
	
	public  int  getNext() {
		return next;
	}
	
	public int getDone() {
		return done;
	}
	
	public boolean isDone() {
		if (done - next == 0)
			return true;
		return false;
	}
}
