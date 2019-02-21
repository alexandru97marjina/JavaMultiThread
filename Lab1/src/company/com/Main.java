package company.com;

import java.lang.reflect.Array;
import java.util.Random;

class Counter extends Thread {
    //clasa care defineşte firul de execuţie
    private int from, to, step,direction; //1-in crestere, 2- in descrestere
    private int array[] = new int[1000];

    public Counter(int from, int to, int step, int[] array,int direction) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.array= array.clone();
        this.direction=direction;
    }

    public void run() {
        int sum1=0,sum2=0;
        if(direction==1) {
            int prod=0,sum=0,count=0,countP=0;
            int temp1=0,temp2=0;
            System.out.println();
            for (int i = from; i <= to; i += step) {
                if(array[i]%2 == 0){
                    count++;
                    if(count==1)temp1=array[i];
                    if(count==2)
                    {
                        temp2=array[i];
                        prod=temp1*temp2;
                        System.out.println(super.getName()+": "+prod+", ");
                        if(countP<=1){
                            sum1+=prod;
                            countP++;
                        }
                        else if(countP==2){
                            System.out.println(super.getName()+"Sum1 = "+sum1+". ");
                            sum1=0;
                            countP=0;
                        }
                        count=0;
                    }
                }
            }

        }
        else{
            int prod=0,sum=0,count=0,countP=0;
            int temp1=0,temp2=0;
            for (int i = from; i >= to; i -= step) {
                if (array[i] % 2 == 0) {
                    count++;
                    if (count == 1) temp1 = array[i];
                    if (count == 2) {
                        temp2 = array[i];
                        prod = temp1 * temp2;
                        System.out.print(super.getName()+": "+prod+", ");
                        if(countP<=1){
                            sum1+=prod;
                            countP++;
                        }
                        else if(countP==2){
                            System.out.println(super.getName()+"Sum1 = "+sum1+". ");
                            sum1=0;
                            countP=0;
                        }
                        count = 0;
                    }
                }
            }

        }
    }
}
class Dev extends Thread{
    private String text;
    Dev(String text){
        this.text=text;
    }
    public void run(){

            for (int i = 0; i < this.text.length(); i++) {
                System.out.print(text.charAt(i));
                try {
                    Thread.sleep(150);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

    }
}
public class Main {

    public static void main(String[] args) {
        Counter cnt1, cnt2;
        int array[];
        array=new int[100];
        System.out.println("Sirul generat random:");
        for(int i=0;i<=99;i++){
            array[i]=getRand(0,100);
            System.out.print(array[i]+", ");
            if(i==60)System.out.println();
        }
        System.out.println();
        System.out.println("__________________________________________");
        System.out.println();

        cnt1 = new Counter(0, 99, 1,array,1);
        //numară de la 0 la 100 cu pasul 5

        cnt2 = new Counter(99, 0, 1,array,2);
        //numară de la 100 la 200 cu pasul 10

        cnt1.start();
        cnt2.start();
        try {
            cnt1.join();
            cnt2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Firele de executie 1 si 2 s-au finisat");
        Dev d=new Dev("Marjina Alexandru C-161");
        d.start();

    }
    public static int getRand(int min, int max){
        if(min >= max){
            throw new IllegalArgumentException("interval gresit");
        }
        Random R =new Random();
        return R.nextInt((max-min)+1)+min;
    }
}

