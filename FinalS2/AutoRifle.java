package FinalS2;
import java.util.ArrayList;

public class AutoRifle extends Weapons{
	private final int serialShot;//Sonradan değiştirilemeyeceği için final tanımladık.
	private final int capacity;//Sonradan değiştirilemeyeceği için final tanımladık.
	private ArrayList<Sarjor> sarjorList;//Her otomatik silahın ayrı bir sarjor listesi var.
	private Soldier owner;
	private int count=0;
	private int currentSarjor=0;
public AutoRifle(String name1,int range1,int serialShot1,int capacity1) {
	super(name1,range1);
	this.serialShot=serialShot1;
	this.capacity=capacity1;
	sarjorList=new ArrayList<Sarjor>();
}
public void setOwner(Soldier soldier) {
	this.owner=soldier;
}
public void introduceGun() {
	System.out.println(this.getName());
	System.out.println("Kursun kaldi : "+this.getSarjor().getBulletsRemained());
}
public void introduceGunDetailed() {
	System.out.println("Isim: "+this.getName());
	System.out.println("Menzil: "+super.getRange());
	System.out.println("Sarjor kapasitesi: "+this.capacity);
	System.out.println("Seri atis kapasitesi: "+this.serialShot);
}
public void addSarjor() {//Weapons sınıfından override edilen bir metod.Listeye sarjor ekleyip içini dolduruyor.
	Sarjor sarjor = new Sarjor(capacity);
	sarjorList.add(sarjor);
	sarjorList.get(count).refill();
	count++;
	owner.addSarjor(sarjor);
}
public Sarjor getSarjor() {//Alttaki metodlala birlikte overloading örneği.Eldeki sarjorü döndürüyor.
	return sarjorList.get(currentSarjor);
}
public Sarjor getSarjor(int i) {//Üstteki metodla birlikte overloading örneği.Listedeki istenilen şarjörü döndürüyor. Girilen değer index disindaysa eldeki sarjörü döndürür.
	if(sarjorList.size()-1>=i&&i>=0) {
	return sarjorList.get(i);
	}
	else return sarjorList.get(currentSarjor);
}
public int getSarjorListSize(){
	return sarjorList.size();
}
public void refill() {
	sarjorList.get(currentSarjor).refill();
	introduceGun();
}
public boolean hasSarjor(){
	if(sarjorList.size()>0) {
		return true;
	}
	else {
		return false;
	}
}
public void shot(int distance,int times){
	if(times>serialShot) {//maksimum seri atis sayisi girilen değerden kücükse,seri atis sayisi kendi maksimum değerini alır. 
		times=serialShot;
	}
		for(int i=0;i<times;i++) {
		if(sarjorList.get(currentSarjor).canShot()==true) {
		sarjorList.get(currentSarjor).shot();	
		}
		else if(currentSarjor+1<=count-1) {//Eğer bir önceki şarjör bittiyse ve başka bir sarjör varsa atışa devam eder.
			if(sarjorList.get(currentSarjor+1).canShot()==true) {
			sarjorList.get(currentSarjor+1).shot();
			currentSarjor=currentSarjor+1;
			System.out.println("Diger sarjore gecildi. Yeni sarjorden "+(times-i)+" kursun atildi.");
			System.out.println("Toplamda "+times+" kursun atildi.");
			}
		}
		else {
			System.out.println("Refill your gun");
			i=times;
		}
	}
		if(distance>super.getRange()){//Eğer menzilin disindaysa basarisiz olduğunu bildirir.
			System.out.println("Hedef menzil icinde degil. Atislar basarisiz. "+times+" kursun atildi.");
		}
	introduceGun();
}
}
