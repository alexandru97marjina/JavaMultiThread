package company.com;

public class Main {


    public static void main(String[] args) throws InterruptedException {
		ResourceLock lock = new ResourceLock();

	    Depozit d=new Depozit(6);
	    Producator p1=new Producator("P1",d);
	    Consumator c1=new Consumator("C1",d,5,lock);
	    Producator p2=new Producator("P2",d);
	    Consumator c2=new Consumator("C2",d,5,lock);
	    Producator p3=new Producator("P3",d);
	    Consumator c3=new Consumator("C3",d,5,lock);
	    p1.start();
	    p2.start();
	    p3.start();
		c1.start();
		c2.start();
	    c3.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		if(!c1.isAlive()&&!c2.isAlive()&&!c3.isAlive()){
//			p1.valabil=true;
//			p2.valabil=true;
//			p3.valabil=true;
			p1.stop();
			p2.stop();
			p3.stop();
		}
		System.out.println(c1.isAlive());
		System.out.println(c2.isAlive());
		System.out.println(c3.isAlive());
		System.out.println(p1.isAlive());
		System.out.println(p2.isAlive());
		System.out.println(p3.isAlive());
		c1.ConsumAfisat();
		c2.ConsumAfisat();
		c3.ConsumAfisat();
    }
}
