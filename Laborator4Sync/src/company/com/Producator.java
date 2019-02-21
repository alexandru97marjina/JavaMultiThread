package company.com;

import java.util.Random;

public class Producator extends Thread {
    private char[] vowel = { 'a', 'e', 'i', 'o', 'u' };
    private Random random = new Random();
    private String Nume="";
    private Depozit d;
    public boolean valabil=false;
    private int count=0;
    private int capacity=10;

    public Producator(String Nume,Depozit d){
        this.Nume=Nume;
        this.d=d;
    }

    public void run(){
        int rN1,rN2;
        while (count<=capacity) {
            rN1=random.nextInt(vowel.length)+0;
            //rN2=random.nextInt(vowel.length)+0;
            System.out.println(this.Nume+" a produs="+vowel[rN1]);
//            System.out.println(this.Nume+" a produs="+vowel[rN2]);
            try {
                d.Stocheaza(vowel[rN1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //if(valabil) this.stop();
            count++;
        }

    }


}
