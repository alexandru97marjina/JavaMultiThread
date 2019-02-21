//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.*;
//import java.nio.ByteBuffer;
//import java.util.*;
//
//
//
//public class Client {
//    public static void main(String[] args) throws Exception {
//        System.out.println("Client Start");
//        Socket socket = new Socket("localhost", 9991);
//        OutputStream outputStream = socket.getOutputStream();
//        PrintWriter out =new PrintWriter(socket.getOutputStream(),true);
//        BufferedReader in =new BufferedReader(
//                new InputStreamReader(socket.getInputStream()));
//        Scanner sc=new Scanner(System.in);
//        String text=in.readLine();
//        System.out.println(text);
//        String response=sc.nextLine();
//        out.println(response);
//        while (true){
//            text=in.readLine();
//            System.out.println(text);
//            response=sc.nextLine();
//            out.println(response);
//
//            if(text.equals("add")){
//                text=in.readLine();
//                System.out.println(text);
//                response=sc.nextLine();
//                out.println(response);
//                BufferedImage image = ImageIO.read(new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\FileSend\\2.jpg"));
//
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                ImageIO.write(image, "jpg", byteArrayOutputStream);
//
//                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//                outputStream.write(size);
//                outputStream.write(byteArrayOutputStream.toByteArray());
//                outputStream.flush();
//                System.out.println("Flushed: " + System.currentTimeMillis());
//
//                Thread.sleep(500);
//                System.out.println("Closing: " + System.currentTimeMillis());
//                text=in.readLine();
//                System.out.println(text);
//                response=sc.nextLine();
//                out.println(response);
//            }
//            //out.write(response);
//
//        }
//
//        //socket.close();
//}
//}
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Client{
    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    Client(){}
    void run()
    {
        try{
            //1. creating a socket to connect to the server
            requestSocket = new Socket("localhost", 9991);
            System.out.println("Connected to localhost in port 9991");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            OutputStream outputStream = requestSocket.getOutputStream();
            Scanner sc=new Scanner(System.in);
            String ConMess="";
            //3: Communicating with the server
            try {
                message = (String)in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("server>" + message);
            do{
                try{
                    message = (String)in.readObject();
                    System.out.println("server>" + message);
                    if(message.equals("show")){
                        do{
                            message = (String)in.readObject();
                            System.out.println(message);
                        } while (!(message.equals("showed")));
                    }
                    else if(message.equals("add")){
                        message = (String)in.readObject();
                        System.out.println("server>" + message);
                        message = sc.nextLine();
                        sendMessage(message);
                        BufferedImage image = ImageIO.read(new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\FileSend\\2.jpg"));

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ImageIO.write(image, "jpg", byteArrayOutputStream);

                        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                        outputStream.write(size);
                        outputStream.write(byteArrayOutputStream.toByteArray());
                        outputStream.flush();
                        System.out.println("Flushed: " + System.currentTimeMillis());

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Closing: " + System.currentTimeMillis());

                    }
                    else {
                        message = sc.nextLine();
                        sendMessage(message);
                    }
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println( msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        Client client = new Client();
        client.run();
    }
}