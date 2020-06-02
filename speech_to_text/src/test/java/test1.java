import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author srx
 * @description
 * @create 2020-06-01 15:03:03
 */
public class test1 {
    public static void main(String[] args) {
//        Scanner input=new Scanner(System.in);
//        String text="";
//        input.useDelimiter("\n");
//            text=input.next();
////        while (input.hasNext()){
////        }
//        System.out.println(text);
//        speech_swing speech_swing = null;
        ObjectInputStream objectInputStream = null;
        BufferedInputStream bufferedInputStream=null;
        String[] info;
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            bufferedInputStream=new BufferedInputStream(socket.getInputStream());
            objectInputStream = new ObjectInputStream(bufferedInputStream);
            info = (String[]) objectInputStream.readObject();
            for (int i = 0; i <info.length ; i++) {
                System.out.println(info[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
