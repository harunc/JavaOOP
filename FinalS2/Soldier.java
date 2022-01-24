package FinalS2;

import java.util.ArrayList;

public class Soldier {
private String name;
private String rank;
private int maxSarjor;//Askerin sahip olduğu maksimum sarjor sayını belirlemek için kullanılan değişken. Asker kısmında bahsedilen ilave şarjör olayından dolayı oluşturdum.
private ArrayList<Weapons> rifleList;//Envanteri kontrol etmek için 3 farklı liste oluşturdum.
private ArrayList<Weapons> pistolList; 
private ArrayList<Sarjor> sarjorList; 

private int count2=0;
public Soldier(String name1,String rank1,int maxSarjor1) {
	this.name=name1;
	this.rank=rank1;
	this.maxSarjor=maxSarjor1;
	rifleList =new ArrayList<Weapons>();
	pistolList =new ArrayList<Weapons>();
	sarjorList=new ArrayList<Sarjor>();
}
public String getName() {
	return this.name;
}public String getRank() {
	return this.rank;
}
public void addSarjor(Sarjor sarjor) {
	if(sarjorList.size()<maxSarjor) {//Sarjor kapasitesi yeterliyse sarjor ekliyoruz.
		sarjorList.add(sarjor);
		count2++;
	}
	else {
		System.out.println("Sarjor limiti doldu.");
	}
}
public int getSarjorCount() {
	return count2;
}
public int getMaxSarjor() {
	return maxSarjor;
}
public void addWeapon(Weapons weapon) {//Silahları listeye eklerken hangi türe ait oldugunu ayrıştırıp envanter listesine ekliyoruz.
	if(weapon instanceof Pistol) {
	pistolList.add(weapon);

	}
	else if(weapon instanceof AutoRifle) {
	rifleList.add(weapon);
}
}
public Weapons getRifle(int i) {
	return rifleList.get(i);
}
public Weapons getPistol(int i) {
	return pistolList.get(i);
}
public int getPistolListSize() {
	return pistolList.size();
}
public int getRifleListSize() {
	return rifleList.size();
}
}