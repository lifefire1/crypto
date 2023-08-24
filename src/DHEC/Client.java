package DHEC;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import static DHEC.DHECLibrary.getPontN;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        Curve curve = new Curve(2,2,17);
        Point beginPoint = new Point(5,1);
        Point temp = new Point(5,1);
        int privateAliseKey = new Random().nextInt(10);
        Point publicKeyAlise = getPontN(privateAliseKey,beginPoint,temp,curve);
        socket.getOutputStream().write(preparePackage(publicKeyAlise.toString()));
        socket.getOutputStream().flush();
        Point bob = createPubK(socket.getInputStream().readNBytes(512));
        System.out.println(getPontN(privateAliseKey, bob, new Point(5,1), curve));
        socket.close();
    }

    private static Point createPubK(byte[] byteArrayAlise) {
        String s = new String(byteArrayAlise).trim();
        String[] t = s.split(" ");
        return new Point(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
    }

    public static byte[] preparePackage(String s){
        byte[] arr = new byte[512];
        int i = 0;
        for (byte temp : s.getBytes()){
            arr[i] = temp;
            i++;
        }
        return arr;
    }
}


