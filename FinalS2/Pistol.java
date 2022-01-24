package FinalS2;

public class Pistol extends Weapons{
	private Sarjor sarjor;
	private Soldier owner;
	public Pistol(String name1,int range1,int capacity) {
		super(name1,range1);
		this.sarjor = new Sarjor(capacity);
		sarjor.refill();
	}
	public void introduceGun() {
		System.out.println(this.getName());
		System.out.println("Bullet remained : "+this.getSarjor().getBulletsRemained());
	}
	public void introduceGunDetailed() {
		System.out.println("Isim: "+this.getName());
		System.out.println("Menzil: "+super.getRange());
		System.out.println("Sarjor kapasitesi: "+this.getSarjor().getCapacity());
	}
	public void refill() {
		sarjor.refill();
		introduceGun();
	}
	public void shot(int distance) {//Atış ihtimallerini inceleyip sonuç verir.
		if(sarjor.canShot()==true&&distance<=super.getRange()) {
			System.out.println("Atis basarili.");
			sarjor.shot();
		}
		else if(distance>this.getRange()&&sarjor.canShot()==true) {
			System.out.println("Hedef menzil icinde degil. Atis basarisiz.");
			sarjor.shot();
		}
		else if(sarjor.canShot()==false) {
			System.out.println("Yeterli mermi yok, sarjor doldurun.");
		}
		introduceGun();
	}
	public Sarjor getSarjor() {
		return sarjor;
	}
	public boolean hasSarjor(){//Sarjorle beraber üretildiği için hep true döner.
		return true;
	}
	public int getSarjorListSize(){
		return 1;
	}
	public void setOwner(Soldier soldier) {
		this.owner=soldier;
	}
}
