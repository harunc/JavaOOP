package FinalS2;

public abstract class Weapons {//Silah sınıfından nesne oluşmaması için abstract yaptık.
	private String name;
	private int range;
	public Weapons(String name1, int range1) {
		this.name=name1;
		this.range=range1;
	}
	public int getRange() {
		return range;
	}
	public String getName() {
		return name;
	}
	public abstract void refill();
	public abstract void introduceGun();
	public abstract void introduceGunDetailed();
	public abstract boolean hasSarjor();
	public Sarjor getSarjor() {	//Override edilecek bir metod.
		Sarjor sarjor = new Sarjor(10);
		return sarjor;
	}
	public Sarjor getSarjor(int i) { //Override edilecek bir metod.	
		Sarjor sarjor = new Sarjor(10);
		return sarjor;
	}
	public void addSarjor() {	//Override edilecek bir metod.
	}
	public abstract int getSarjorListSize();
	public void shot(int distance) { //Override edilecek bir metod.
	}
	public void shot(int distance,int serialShot) {	//Override edilecek bir metod.
	}
	public abstract void setOwner(Soldier soldier);
}

