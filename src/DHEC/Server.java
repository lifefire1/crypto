package DHEC;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import static DHEC.DHECLibrary.getPontN;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5000);
        Curve curve = new Curve(2,2,17);
        Point beginPoint = new Point(5,1);
        Point temp = new Point(5,1);
        int privateBobKey = new Random().nextInt(10);
        Point publicKeyBob = getPontN(privateBobKey,beginPoint,temp,curve);
        Socket client = server.accept();
        byte [] byteArrayAlise = client.getInputStream().readNBytes(512);
        Point alisePubKey = createPubK(byteArrayAlise);
        client.getOutputStream().write(preparePackage(publicKeyBob.toString()));
        client.getOutputStream().flush();
        System.out.println(getPontN(privateBobKey, alisePubKey, new Point(5, 1), curve));
        client.close();
        server.close();
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



