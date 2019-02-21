//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.*;
//import java.nio.ByteBuffer;
//import java.util.*;
//import java.io.IOException;
//
//
//public class Server {
//    public static void main(String[] args) throws Exception {
//        System.out.println("Server start");
//        int clientNumber=0;
//        ServerSocket linstner =new ServerSocket(9991);
//        try {
//            while (true){
//                new User(linstner.accept(),clientNumber).start();
//                clientNumber++;
//            }
//        }
//        finally {
//            linstner.close();
//        }
//    }
//    private static class User extends Thread{
//        private Socket socket;
//        private int clientNumber;
//
//        public User(Socket socket,int clientNumber){
//            this.socket=socket;
//            this.clientNumber=clientNumber;
//            log("O noua conexiune cu client#"+clientNumber+ " at "+socket);
//
//        }
//        public void run(){
//            try {
//                BufferedReader in =new BufferedReader(
//                        new InputStreamReader(socket.getInputStream()));
//                PrintWriter out =new PrintWriter(socket.getOutputStream(),true);
//
//                //Mesaj intial pentru client
//                out.println("Buna, esti clientul cu numarul #"+ clientNumber);
//                String log="";
//                String pass="";
//                String tempLog="";
//                String tempPass="";
//
//                //primeste mesaje de la client cite o linie
//                while (true){
//                    Boolean exit=true;
//                    String client="";
//                    out.println("Care directorie accesati?");
//                    //String input=in.readLine();
//                    client=in.readLine();
//                    switch (client){
//                        case "client1":
//                            log="client1";
//                            pass="client1";
//                            tempLog="";
//                            tempPass="";
//                            out.println("Introduceti login pentru "+client+" : ");
//                            tempLog=in.readLine();
//                            out.println("Introduceti parola pentru "+client+" :");
//                            tempPass=in.readLine();
//                            if(tempLog.equals(log)&&tempPass.equals(pass)){
//                                out.println("Autentificare reusita\n Ce doriti sa faceti");
//                                while (true && exit) {
//                                    out.println("Introduceti una din variante : show||add||delete||exit");
//                                    String com = in.readLine();
//                                    switch (com){
//                                        case "show":
//                                            List<String> results = new ArrayList<String>();
//                                            File[] files = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client1").listFiles();
//                                            for (File file : files) {
//                                                if (file.isFile()) {
//                                                    out.println(file.getName());
//                                                    results.add(file.getName());
//                                                }
//                                            }
//                                            break;
//                                        case "add":
//                                            out.println("add");
//                                            System.out.println("Reading: " + System.currentTimeMillis());
//                                            InputStream inputStream = socket.getInputStream();
//                                            out.println("Introduceti denumirea fisierului:");
//                                            String fileName=in.readLine();
//                                            byte[] sizeAr = new byte[4];
//                                            inputStream.read(sizeAr);
//                                            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
//
//                                            byte[] imageAr = new byte[size];
//                                            inputStream.read(imageAr);
//
//                                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
//                                            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
//                                            ImageIO.write(image, "jpg", new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client3\\"+fileName));
//                                            out.println("Receptionat");
//                                           break;
//                                        case "delete":
//                                            out.println("Introdu denumirea fiserului care doresti sa-l stergi:");
//                                            String name=in.readLine();
//                                            File file=new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client1\\"+name);
//                                            if(file.delete()){
//                                                out.println("Fisierul "+name+" a fost sters");
//                                            }
//                                            else{
//                                                out.println("Fisierul "+name+" a fost sters");
//                                            }
//                                            break;
//                                        case "exit":
//                                            exit =false;
//                                            out.println("O zi buna");
//                                            break;
//                                            default:
//                                                out.println("Comanda Gresita");
//                                                break;
//
//                                    }
//                                }
//
//                            }
//                            else {
//                                out.println("Eroare de conexinue!!!");
//                                break;
//                            }
//                        case "client2":
//                            log="client2";
//                            pass="client2";
//                            tempLog="";
//                            tempPass="";
//                            out.println("Introduceti login pentru "+client+" : ");
//                            tempLog=in.readLine();
//                            out.println("Introduceti parola pentru "+client+" :");
//                            tempPass=in.readLine();
//                            if(tempLog.equals(log)&&tempPass.equals(pass)){
//                                out.println("Autentificare reusita");
//                                out.println("Ce doriti sa faceti?");
//                                while (true && exit) {
//                                    out.println("Introduceti una din variante : show||add||delete||exit");
//                                    String com = in.readLine();
//                                    switch (com){
//                                        case "show":
//                                            List<String> results = new ArrayList<String>();
//                                            File[] files = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client2").listFiles();
//                                            for (File file : files) {
//                                                if (file.isFile()) {
//                                                    out.println(file.getName());
//                                                    results.add(file.getName());
//                                                }
//                                            }
//                                            break;
//                                        case "add":
//                                            System.out.println("Reading: " + System.currentTimeMillis());
//                                            InputStream inputStream = socket.getInputStream();
//                                            out.println("Introduceti denumirea fisierului:");
//                                            String fileName=in.readLine();
//
//                                            byte[] sizeAr = new byte[4];
//                                            inputStream.read(sizeAr);
//                                            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
//
//                                            byte[] imageAr = new byte[size];
//                                            inputStream.read(imageAr);
//
//                                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
//                                            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
//                                            ImageIO.write(image, "jpg", new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client3\\"+fileName));
//                                            break;
//                                        case "delete":
//                                            out.println("Introdu denumirea fiserului care doresti sa-l stergi:");
//                                            String name=in.readLine();
//                                            File file=new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client2\\"+name);
//                                            if(file.delete()){
//                                                out.println("Fisierul "+name+" a fost sters");
//                                            }
//                                            else{
//                                                out.println("Fisierul "+name+" a fost sters");
//                                            }
//                                            break;
//                                        case "exit":
//                                            exit=false;
//                                            out.println("O zi buna");
//                                            break;
//                                        default:
//                                            out.println("Comanda Gresita");
//                                            break;
//
//                                    }
//                                }
//
//                            }
//                            else {
//                                out.println("Eroare de conexinue!!!");
//                                break;
//                            }
//                    }
//
//                    }
//
//
//                }
//             catch(IOException e){
//                log("Error handling client# " + clientNumber + ": " + e);
//            }
//            finally {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    log("Couldn't close a socket, what's going on?");
//                }
//                log("Connection with client# " + clientNumber + " closed");
//            }
//        }
//
//        private void log(String message){
//            System.out.println(message);
//        }
//    }}
//
//
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server{
    ServerSocket ServerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    int clientNumber=0;
    String conMess="";
    String client="";
    String log="";
    String pass="";
    String tempLog="";
    String tempPass="";
    String com="";
    String fileName="";
    boolean exit=false;

    Server(){}
    void run()
    {
        try{
            ServerSocket = new ServerSocket(9991, 10);
            System.out.println("Waiting for connection");
            connection = ServerSocket.accept();
            clientNumber++;
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            InputStream inputStream = connection.getInputStream();
            sendMessage("Connection successful esti client numar="+clientNumber);
            //Scanner sc=new Scanner(System.in);
            do{
                try{
                    sendMessage("Ce directorie accesati?");
                    message = (String)in.readObject();
                    //System.out.println("client>" + message);
                    client=message;
                    switch (client){
                        case "client1":
                            log = "client1";
                            pass = "client1";
                            sendMessage("Introdu login pentru " + client + " : ");
                            tempLog = (String) in.readObject();
                            //System.out.println("client>" + message);
                            sendMessage("Introduceti parola pentru " + client + " :");
                            tempPass = (String) in.readObject();
                            if (tempLog.equals(log) && tempPass.equals(pass)) {
                                sendMessage("Autentificare Reusita");
                                while(!exit) {
                                    sendMessage("\"Introduceti una din variante : show||add||delete||exit\"");
                                    com = (String) in.readObject();
                                    switch (com) {
                                        case "show":
                                            sendMessage("show");
                                            List<String> results = new ArrayList<String>();
                                            File[] files = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client1").listFiles();
                                            for (File file : files) {
                                                if (file.isFile()) {
                                                    sendMessage(file.getName());
                                                    results.add(file.getName());
                                                }
                                            }
                                            sendMessage("showed");
                                            break;
                                        case "add":
                                            sendMessage("add");
                                            sendMessage("Introdu denumirea imaginii:");
                                            fileName = (String) in.readObject();
                                            byte[] sizeAr = new byte[4];
                                            inputStream.read(sizeAr);
                                            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                                            byte[] imageAr = new byte[size];
                                            inputStream.read(imageAr);

                                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
                                            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
                                            ImageIO.write(image, "jpg", new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client3\\" + fileName));
                                            sendMessage("Receptionat");
                                            break;
                                        case "delete":
                                            sendMessage("Introdu denumirea fiserului care doresti sa-l stergi:");
                                            fileName = (String) in.readObject();
                                            File file = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client1\\" + fileName);
                                            if (file.delete()) {
                                                sendMessage("Fisierul " + fileName + " a fost sters");
                                            } else {
                                                sendMessage("Fisierul " + fileName + " nu a fost sters");
                                            }
                                            break;
                                        case "exit":
                                            sendMessage("O zi buna");
                                            exit = true;
                                            break;
                                         default:
                                             sendMessage("Mesaj gresit");
                                    }
                                }

                            } else {
                                sendMessage("Eroare de conexinue!!!");
                                break;
                            }
                            break;
                        case "client2":
                            log = "client2";
                            pass = "client2";
                            sendMessage("Introdu login pentru " + client + " : ");
                            tempLog = (String) in.readObject();
                            //System.out.println("client>" + message);
                            sendMessage("Introduceti parola pentru " + client + " :");
                            tempPass = (String) in.readObject();
                            if (tempLog.equals(log) && tempPass.equals(pass)) {
                                sendMessage("Autentificare Reusita");
                                while(!exit) {
                                    sendMessage("\"Introduceti una din variante : show||add||delete||exit\"");
                                    com = (String) in.readObject();
                                    switch (com) {
                                        case "show":
                                            sendMessage("show");
                                            List<String> results = new ArrayList<String>();
                                            File[] files = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client2").listFiles();
                                            for (File file : files) {
                                                if (file.isFile()) {
                                                    sendMessage(file.getName());
                                                    results.add(file.getName());
                                                }
                                            }
                                            sendMessage("showed");
                                            break;
                                        case "add":
                                            sendMessage("add");
                                            sendMessage("Introdu denumirea imaginii:");
                                            fileName = (String) in.readObject();
                                            byte[] sizeAr = new byte[4];
                                            inputStream.read(sizeAr);
                                            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                                            byte[] imageAr = new byte[size];
                                            inputStream.read(imageAr);

                                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
                                            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
                                            ImageIO.write(image, "jpg", new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client2\\" + fileName));
                                            sendMessage("Receptionat");
                                            break;
                                        case "delete":
                                            sendMessage("Introdu denumirea fiserului care doresti sa-l stergi:");
                                            fileName = (String) in.readObject();
                                            File file = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client2\\" + fileName);
                                            if (file.delete()) {
                                                sendMessage("Fisierul " + fileName + " a fost sters");
                                            } else {
                                                sendMessage("Fisierul " + fileName + " nu a fost sters");
                                            }
                                            break;
                                        case "exit":
                                            sendMessage("O zi buna");
                                            exit = true;
                                            break;
                                        default:
                                            sendMessage("Mesaj gresit");
                                    }
                                }

                            } else {
                                sendMessage("Eroare de conexinue!!!");
                                break;
                            }
                            break;
                        case "client3":
                            log = "client3";
                            pass = "client3";
                            sendMessage("Introdu login pentru " + client + " : ");
                            tempLog = (String) in.readObject();
                            //System.out.println("client>" + message);
                            sendMessage("Introduceti parola pentru " + client + " :");
                            tempPass = (String) in.readObject();
                            if (tempLog.equals(log) && tempPass.equals(pass)) {
                                sendMessage("Autentificare Reusita");
                                while(!exit) {
                                    sendMessage("\"Introduceti una din variante : show||add||delete||exit\"");
                                    com = (String) in.readObject();
                                    switch (com) {
                                        case "show":
                                            sendMessage("show");
                                            List<String> results = new ArrayList<String>();
                                            File[] files = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client3").listFiles();
                                            for (File file : files) {
                                                if (file.isFile()) {
                                                    sendMessage(file.getName());
                                                    results.add(file.getName());
                                                }
                                            }
                                            sendMessage("showed");
                                            break;
                                        case "add":
                                            sendMessage("add");
                                            sendMessage("Introdu denumirea imaginii:");
                                            fileName = (String) in.readObject();
                                            byte[] sizeAr = new byte[4];
                                            inputStream.read(sizeAr);
                                            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                                            byte[] imageAr = new byte[size];
                                            inputStream.read(imageAr);

                                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
                                            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
                                            ImageIO.write(image, "jpg", new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client3\\" + fileName));
                                            sendMessage("Receptionat");
                                            break;
                                        case "delete":
                                            sendMessage("Introdu denumirea fiserului care doresti sa-l stergi:");
                                            fileName = (String) in.readObject();
                                            File file = new File("E:\\univer\\Anul III\\Laboratoare\\PCD\\Laborator6ClientServerSoft\\src\\Client3\\" + fileName);
                                            if (file.delete()) {
                                                sendMessage("Fisierul " + fileName + " a fost sters");
                                            } else {
                                                sendMessage("Fisierul " + fileName + " nu a fost sters");
                                            }
                                            break;
                                        case "exit":
                                            sendMessage("O zi buna");
                                            exit = true;
                                            break;
                                        default:
                                            sendMessage("Mesaj gresit");
                                    }
                                }

                            } else {
                                sendMessage("Eroare de conexinue!!!");
                                break;
                            }
                            break;
                        default:

                    }
                    if (message.equals("bye")){
                        sendMessage("bye");
                        conMess="bye";
                        sendMessage(conMess);
                    }

//                    System.out.println("Mesaj catre client:");
//                    conMess=sc.nextLine();
                    //sendMessage(conMess);
                }
                catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                ServerSocket.close();
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
            System.out.println("server>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        Server server = new Server();
        while(true){
            server.run();
        }
    }
}