package company.com;
import java.util.Random;

class Counter extends Thread {
    //clasa care defineşte firul de execuţie
    private int from, to, step,direction; //1-in crestere, 2- in descrestere
    private int array[];
    private String text;
    private boolean running=false;

    public Counter(int from, int to, int step, int[] array,int direction,String text) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.array= array.clone();
        this.direction=direction;
        this.text=text;
    }

    public void run() {
        int sum1=0,prod,count=0,countP=0;
        int temp1=0,temp2;
        switch (direction){
            case 1:
                    System.out.println();
                    for (int i = from; i <= to; i += step) {
                        if (array[i] % 2 == 0) {
                            count++;
                            if (count == 1) temp1 = array[i];
                            if (count == 2) {
                                temp2 = array[i];
                                prod = temp1 * temp2;
                                System.out.println(super.getName() + ": " + prod + ", ");
                                if (countP <= 1) {
                                    sum1 += prod;
                                    countP++;
                                } else if (countP == 2) {
                                    System.out.println(super.getName() + "Sum1 = " + sum1 + ". ");
                                    sum1 = 0;
                                    countP = 0;
                                }
                                count = 0;
                            }
                        }
                    }
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                    //SuspendThread();
                    while (!running)
                        //this.yield();
                this.setPriority(3);

                    System.out.print(this.getName() + "-");
                    for (int i = 0; i < this.text.length(); i++) {
                        System.out.print(text.charAt(i));
                        try {
                            Thread.sleep(150);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println();

                    break;
                    case 2:
                        for (int i = from; i >= to; i -= step) {
                            if (array[i] % 2 == 0) {
                                count++;
                                if (count == 1) temp1 = array[i];
                                if (count == 2) {
                                    temp2 = array[i];
                                    prod = temp1 * temp2;
                                    System.out.print(super.getName() + ": " + prod + ", ");
                                    if (countP <= 1) {
                                        sum1 += prod;
                                        countP++;
                                    } else if (countP == 2) {
                                        System.out.println(super.getName() + "Sum1 = " + sum1 + ". ");
                                        sum1 = 0;
                                        countP = 0;
                                    }
                                    count = 0;
                                }
                            }
                        }
                       // SuspendThread();
                        while (!running)
                         //   this.yield();
                        this.setPriority(7);


                            System.out.print(this.getName() + "-");
                            for (int i = 0; i < this.text.length(); i++) {
                                System.out.print(text.charAt(i));
                                try {
                                    Thread.sleep(150);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println();

                            break;
                            case 3:
                                for (int i = from; i <= to; i += step) {
                                    System.out.println(super.getName() + " " + array[i] + ", ");
                                }

//                                SuspendThread();
                                while (!running)
                                    //this.yield();
                                this.setPriority(1);

                                    System.out.print(this.getName() + "-");
                                    for (int i = 0; i < this.text.length(); i++) {
                                        System.out.print(text.charAt(i));
                                        try {
                                            Thread.sleep(150);

                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    System.out.println();

                                    break;
                                    case 4:
                                        for (int i = from; i >= to; i -= step) {
                                            System.out.println(super.getName() + " " + array[i] + ", ");
                                        }
                                        //SuspendThread()
                                        while (!running)
                                            this.setPriority(4);

                                            System.out.print(this.getName() + "-");
                                            for (int i = 0; i < this.text.length(); i++) {
                                                System.out.print(text.charAt(i));
                                                try {
                                                    Thread.sleep(150);

                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            System.out.println();

                                        break;

                                }
                        }
    public void ResumeThread() {
        running=true;

    }
    public void SuspendThread(){
        running=false;
    }
}
class Dev extends Thread{
    private String text;
    Dev(String text){
        this.text=text;
    }
    public void run(){
        System.out.print(this.getName()+"-");
                for (int i = 0; i < this.text.length(); i++) {
                    System.out.print(text.charAt(i));
                    try {
                        Thread.sleep(150);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
    }

}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter cnt1, cnt2,cnt3,cnt4;
        int array[];
        array=new int[2000];
        System.out.println("Sirul generat random:");
        for(int i=0;i<=1999;i++){
            array[i]=getRand(0,100);
            System.out.print(array[i]+", ");
            if(i==60)System.out.println();
        }
        System.out.println();
        System.out.println("__________________________________________");
        System.out.println();

        cnt1 = new Counter(200, 300, 1,array,1,"Alexandru");
        //numară de la 0 la 100 cu pasul 5

        cnt2 = new Counter(106, 6, 1,array,2,"Marjina");
        //numară de la 100 la 200 cu pasul 10
        cnt3 = new Counter(234, 1000, 1,array,3,"PCD");
        //numară de la 0 la 100 cu pasul 5

        cnt4 = new Counter(1234, 456, 1,array,4,"C-161");

        cnt1.start();
        cnt2.start();
        cnt3.start();
        cnt4.start();
            Thread.sleep(100);
            cnt2.ResumeThread();
            try {
                cnt2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cnt4.ResumeThread();
            try {
                cnt2.join();
                cnt4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cnt1.ResumeThread();
            try {
                cnt2.join();
                cnt4.join();
                cnt1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cnt3.ResumeThread();
        try {
            cnt2.join();
            cnt4.join();
            cnt1.join();
            cnt3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


            cnt3.ResumeThread();


            System.out.println("Firele de executie 1-4 s-au finisat");


    }
    public static int getRand(int min, int max){
        if(min >= max){
            throw new IllegalArgumentException("interval gresit");
        }
        Random R =new Random();
        return R.nextInt((max-min)+1)+min;
    }
}
