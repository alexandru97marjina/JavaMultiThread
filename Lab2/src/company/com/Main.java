package company.com;

public class Main {

    public static void main(String[] args) {
	        ThreadGroup sys= Thread.currentThread().getThreadGroup();
	        Thread curr =Thread.currentThread();
	        curr.setPriority(7);
	        Thread Th1 =new Thread("Th1");
	        Th1.setPriority(6);
	        Th1.start();
	        sys.list();
	        Thread Th2 = new Thread("Th2");
	        Th2.setPriority(3);
	        Th2.start();
	        sys.list();
	        ThreadGroup G7 = new ThreadGroup("G7");
	        Thread ThA = new Thread(G7,"ThA");
	        ThA.setPriority(7);
	        ThA.start();
	        G7.list();
	        ThreadGroup G3 = new ThreadGroup(G7,"G3");
	        Thread Tha = new Thread(G3,"Tha");
	        Tha.start();
	        Tha.setPriority(6);
	        Thread Thb= new Thread(G3,"Thb");
	        Thb.setPriority(6);
	        Thb.start();
	        Thread Thc= new Thread(G3,"Thc");
            Thc.setPriority(6);
            Thc.start();
            Thread Thd= new Thread(G3,"Thd");
            Thd.setPriority(7);
            Thd.start();
            G3.list();
            ThreadGroup G2 = new ThreadGroup(sys,"G2");
            Thread Th11 = new Thread(G2,"Th11");
            Th11.setPriority(7);
            Th11.start();
			Thread Th22 = new Thread(G2,"Th22");
			Th22.setPriority(3);
			Th22.start();
			Thread Th33 = new Thread(G2,"Th33");
			Th33.setPriority(3);
			Th33.start();
			G2.list();

	}
}
