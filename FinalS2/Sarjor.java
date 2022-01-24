package FinalS2;

public class Sarjor implements SarjorInterface {
	private int capacity;
	private int bulletRemained;
	public Sarjor(int capacity1) {
		this.capacity=capacity1;
	}
	public void refill() {
		this.bulletRemained=this.capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getBulletsRemained() {
		return bulletRemained;
	}
	public boolean canShot() {//Eger kurşun varsa true.
		if(bulletRemained>0) {
		return true;
		}
		else {
		return false;
		}
	}
	public void shot() {
		this.bulletRemained--;
	}
}
