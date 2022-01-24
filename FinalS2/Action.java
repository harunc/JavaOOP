package FinalS2;
import java.util.*;
public class Action {
	public static void main(String[] args) {
		Scanner scn =new Scanner(System.in);
		ArrayList<Weapons> rifleList =new ArrayList<Weapons>();//Oncelikle burada silah listeleri olusturup sonra hepsini topluca envantere ekliyoruz.
		ArrayList<Weapons> pistolList =new ArrayList<Weapons>();//Ayrıca çok biçimlilik örneği.
		boolean add = false;
		Soldier ahmet = new Soldier("Ahmet","Captain",8);//Askeri olusturuyoruz, adı,rütbesi ve sarjor limitiyle birlikte.
		while(add==false) {//Ekleme işlemi bitene kadar ekleme menusundeyiz.
		System.out.println("Lutfen eklenecek silah turunu seciniz.");
		System.out.println("1-) Otomatik Tufekler");
		System.out.println("2-) Tabancalar");
		System.out.println("0-)Aksiyon menusunu ac.");
		int decision;
		try {//Uygun olmayan değerler girilmesini engelliyoruz.
		decision=scn.nextInt();
		scn.nextLine();
		if (decision==1) {
			System.out.println("Lutfen sirasiyla silahin ismini giriniz.");
			String name = scn.nextLine();
			System.out.println("Sırayla silahın menzilini, seri atis sayisini ve sarjor kapasitesini giriniz.");
			int range = scn.nextInt();
			int serialShot=scn.nextInt();
			int capacity =scn.nextInt();
			while (serialShot>capacity) {
				System.out.println("Seri atis sayisi kapasiteden büyük olamaz. Sirayla seri atis sayisi ve sarjor kapasitesi giriniz.");
				serialShot=scn.nextInt();
				capacity=scn.nextInt();
			}
			Weapons rifle1 = new AutoRifle(name,range,serialShot,capacity);
			rifleList.add(rifle1);
			rifle1.setOwner(ahmet);//Tufeklerin sahibi olarak askeri atadık.Boylece tufege eklene sarjor dogrudan envantere eklenecek.
		}
		else if (decision==2) {
			System.out.println("Lutfen sirasiyla silahin ismini giriniz.");
			String name = scn.nextLine();
			System.out.println("Sırayla silahın menzilini ve sarjor kapasitesini giriniz");
			int range = scn.nextInt();
			int capacity =scn.nextInt();
			Weapons pistol1 = new Pistol(name,range,capacity);
			pistolList.add(pistol1);
			pistol1.setOwner(ahmet);
			ahmet.addSarjor(pistol1.getSarjor());//Tabancayla beraber hemen sarjor ekleniyor.Tufeklerin sarjor limitinin şimdiden belli olması için.
		}
		else if(decision==0) {//Tabanca ve otomatik tüfekler asker envanterine ekleniyor.
			if(pistolList.size()>0||rifleList.size()>0) {//Eklenen silah olup olmadığını kontrol eder.
			add=true;
			for(Weapons pistol:pistolList) {
				ahmet.addWeapon(pistol);
			}
			for(Weapons rifle:rifleList) {
				ahmet.addWeapon(rifle);
			}
			for(int i=0;i<rifleList.size();i++) {
				System.out.println(ahmet.getRifle(i).getName()+" added to inventory");
			}
			for(int i=0;i<pistolList.size();i++) {
				System.out.println(ahmet.getPistol(i).getName()+" added to inventory");
			}
			}
			else System.out.println("Hic silah eklemediniz.");
		}
		else {
			System.out.println("Gecersiz sayi girdiniz.");
		}
		}
		catch(InputMismatchException e) {
			System.out.println("Degerleri yanlis girdiniz. Silah eklenmedi.");
			scn.nextLine();
		}
		}
		boolean finishAction=false;
		while(finishAction==false) {//Aksiyon menümüz aciliyor.
			System.out.println("Lutfen listeden silah seciniz.");
			System.out.println("1-) Otomatik Tufekler");
			System.out.println("2-) Tabancalar");
			System.out.println("3-) Programi kapat");
			int decision;
			try{//Uygun olmayan değerler girilmesini engelliyoruz.
			decision = scn.nextInt();
			scn.nextLine();
			if(decision==1) {//Tufek menusu aciliyor.
				System.out.println("Birini seciniz. Cikmak icin menude olmayan herhangi bir karakter giriniz.");
				for(int i=0;i<rifleList.size();i++) {
					System.out.println(i+1+"-) "+ ahmet.getRifle(i).getName());
				}
				decision=scn.nextInt();
				boolean innerAction=false;
				if(decision>=1&&decision<=ahmet.getRifleListSize()) {
				innerAction=true;
				ahmet.getRifle(decision-1).introduceGunDetailed();//Aksiyona baslamadan silahi tanitir.
				}
				while(innerAction==true) {//Kullanıcı komutuna kadar silah aksiyonlari menüsü açık kalıyor.
					System.out.println("Ne yapacaksin?");
					System.out.println("1-) Shot");
					System.out.println("2-) Refill");
					System.out.println("3-) Sarjor ekle");
					System.out.println("4-) Back");
					int innerDecision;
					try {//Uygun olmayan değerler girilmesini engelliyoruz.
					innerDecision=scn.nextInt();
					if(innerDecision==1) {
						System.out.println("Lutfen atis uzakligini giriniz.");
						int uzaklik=scn.nextInt();
						System.out.println("Lutfen seri atis sayisini giriniz.");
						int seriAtis=scn.nextInt();
						if(ahmet.getRifle(decision-1).hasSarjor()==true) {//Eger tufeğe sarjor eklendiyse ateş ediyor.
						ahmet.getRifle(decision-1).shot(uzaklik, seriAtis);
						}
						else{
							System.out.println("Sarjor ekleyin.");
						}
					}
					else if(innerDecision==2) {
						if(ahmet.getRifle(decision-1).hasSarjor()==true) {//Eger sarjor varsa yeniden doldurabilirsiniz.
							ahmet.getRifle(decision-1).refill();;
							}
							else{
								System.out.println("Sarjor ekleyin.");
							}
					}
					else if(innerDecision==3) {
						if(ahmet.getSarjorCount()<ahmet.getMaxSarjor()){//Eğer askerin elinde varolan sarjor sayisi limitten azsa tüfek için sarjor ekleyebiliyor.
						ahmet.getRifle(decision-1).addSarjor();//Sırayla tüfeğe ve dolayısıyla sarjor envanterine sarjor ekliyor.
						}
						else {
							System.out.println("Sarjor limiti dolu. Sarjor sayisi "+ahmet.getMaxSarjor());
						}
					}
					else if(innerDecision==4) {
						innerAction=false;
					}
					}
					catch(InputMismatchException e) {
						System.out.println("Degerleri yanlis girdiniz. islem yapilamadi.");
						scn.nextLine();
					}
				}
			}
			else if(decision==2) {//Tabancalar menüsünü açar.
				System.out.println("Birini seciniz. Cikmak icin menude olmayan herhangi bir karakter giriniz.");
				for(int i=0;i<pistolList.size();i++) {
					System.out.println(i+1+"-)"+ahmet.getPistol(i).getName());
				}
				decision=scn.nextInt();
				boolean innerAction=false;
				if(decision>=1&&decision<=ahmet.getPistolListSize()) {
				innerAction=true;
				ahmet.getPistol(decision-1).introduceGunDetailed();//Aksiyona baslamadan silahi tanitir.
				}
				while(innerAction==true) {//Kullanıcı komutuna kadar silah aksiyonlari menüsü açık kalıyor.
					System.out.println("Ne yapacaksin?");
					System.out.println("1-) Shot");
					System.out.println("2-) Refill");
					System.out.println("3-) Back");
					int innerDecision;
					try {//Uygun olmayan değerler girilmesini engelliyoruz.
					innerDecision=scn.nextInt();
					if(innerDecision==1) {//Tabancalar sarjorle üretildiği için otomatik tüfekte yapilan kontrolleri burada yapmıyoruz.
						System.out.println("Lutfen atis uzakligini giriniz.");
						int uzaklik=scn.nextInt();
						ahmet.getPistol(decision-1).shot(uzaklik);
					}
					else if(innerDecision==2) {
						ahmet.getPistol(decision-1).refill();
					}
					else if(innerDecision==3) {
						innerAction=false;
					}
					else {
						System.out.println("Uygun bir deger giriniz.");
					}
				}
					catch(InputMismatchException e) {
						System.out.println("Degerleri yanlis girdiniz. Silah eklenmedi.");
						scn.nextLine();
					}
				}
			}
			else if(decision==3) {
				System.exit(0);
			}
			}
			catch(InputMismatchException e) {
				System.out.println("Degerleri yanlis girdiniz. Silah Secilemedi.");
				scn.nextLine();
			}
		}
	scn.close();
	}
}
