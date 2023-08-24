package DH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int base = 10;
        int primaryValue;
        int secretKey = new Random().nextInt(10);
        primaryValue = 317;
        ServerSocket serverBob = new ServerSocket(5000);
        Socket alisSocket = serverBob.accept();
        String alisePublicKey = readPublicKey(alisSocket);
        long publicKey = createPublicKey(base,secretKey,primaryValue);
        alisSocket.getOutputStream().write(prepareFrame(publicKey + "\0"));
        long genSecretKey = calcGeneralSecretKey(alisePublicKey,secretKey,primaryValue);
        executorService.execute(() -> {
            while (true){
                readMessageFromAlice(alisSocket,genSecretKey);

            }
        });
        executorService.execute(() -> {
            while (true) {
                sendMessageToAlice(alisSocket,genSecretKey);
            }
        });
        Thread.sleep(120000);
        executorService.shutdown();
        alisSocket.close();
        serverBob.close();
    }

    public static void sendMessageToAlice(Socket socket,long genSecKey) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the message:");
            String message = reader.readLine();
            String resString = Crypto.encrypt(message, genSecKey) + '\0';
            socket.getOutputStream().write(resString.getBytes());
            socket.getOutputStream().flush();
        }   catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void flushInput(Socket socket){
        try {
            socket.getInputStream().readNBytes(507);
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void readMessageFromAlice(Socket socket, long genSecKey) {
        int temp;
        StringBuilder builder = new StringBuilder();
        try {
            while ((char)(temp = socket.getInputStream().read()) != '\0'){
                builder.append((char) temp);
            }
            System.out.println("received message from Alice: " + Crypto.decrypt(builder.toString(), genSecKey));
        }   catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private static long calcGeneralSecretKey(String alisePublicKey, int secretKey, int primeNumber) {
        return (((long)Math.pow(Long.parseLong(alisePublicKey) + 0.0,secretKey + 0.0)) % primeNumber);
    }

    public static byte[] prepareFrame(String publicKey){
        byte[] arr = new byte[512];
        for (int i = 0; i < publicKey.length(); i++) {
            arr[i] = (byte) publicKey.charAt(i);
        }
        return arr;
    }

    public static long createPublicKey(int base, int secretKey, int primaryValue){
        return ((long) Math.pow(base + 0.0, secretKey + 0.0) % primaryValue);
    }

    public static String readPublicKey(Socket client) throws IOException {
        byte[] arr = client.getInputStream().readNBytes(512);
        StringBuilder result = new StringBuilder();

        for (byte b : arr) {
            if (b != 0) {
                result.append((char) b);
            }
        }

        return result.toString();
    }
}