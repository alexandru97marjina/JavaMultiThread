package company.com;

import java.util.*;
public class Depozit {
    public int size;
    public  Queue<Character> depozit = new LinkedList<>();
    public Depozit(int size){
        this.size=size;
    }
    public synchronized void Stocheaza(char p1) throws InterruptedException {
        if(Liber()){
            System.out.println("In depozit adaugat- "+p1+ " spatiu liber ramas- "+(this.size-depozit.size()));
            depozit.add(p1);
            notify();
        }
        else{
            while (!Liber()){
                System.out.println("Depozitul este plin producatorii astepata");
                wait();
            }

        }

    }
    public synchronized Character Consuma() throws InterruptedException {
        if(!depozit.isEmpty()){
            Character temp=depozit.remove();
            System.out.println("A fost scos din depozit- "+temp);
            notify();
            return temp;
        }
        else{
            while (depozit.isEmpty())
            {
                System.out.println("Depozitul e gol Consumatorii astepata");
                wait();
            }
            return 0;
        }
    }

    public boolean Liber(){
        if(depozit.size()<=this.size)
            return true;
        else
            return false;
    }


}
