package company.com;

import java.util.LinkedList;
import java.util.Queue;

public class Consumator extends Thread {
    private String Nume="";
    public Queue<Character> consumari = new LinkedList<>();
    private int size;
    private Depozit d;
    ResourceLock lock;

    public Consumator(String Nume,Depozit d,int consum,ResourceLock lock){
        this.Nume=Nume;
        this.d=d;
        this.size=consum;
        this.lock=lock;
    }
    public void run(){
        //while (!Indestulat()) {
            try{
                synchronized (lock) {
                    while (!this.Indestulat()) {
                        if (this.Nume.equals("C1")) {
                            cons(1);
                        } else if (this.Nume.equals("C2")) {
                            cons(2);
                        } else if (this.Nume.equals("C3")) {
                            cons(3);
                        }
                    }
                }
            }catch (Exception e) {
                System.out.println("+");
            }


        }

    public Boolean Indestulat(){
        if(consumari.size()==this.size){
            return  true;
        }
        else
            return false;
    }
    public void ConsumAfisat(){
        System.out.print(this.Nume+" a consumat in final= ");
        while (!consumari.isEmpty()){
            System.out.print(consumari.remove());
        }
        System.out.println();
    }

    public void cons(int test){
        if(lock.flag!=test){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(lock.flag==test){
            Character c = null;
            try {
                c = d.Consuma();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumari.add(c);
            System.out.println(this.Nume + " a consumat " + c);
            lock.flag++;
            if(lock.flag>3){
                lock.flag=1;
            }
            lock.notifyAll();
        }

    }

}
