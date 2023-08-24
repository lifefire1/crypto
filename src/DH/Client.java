package DH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        long publicKey;
        int base = 10;
        int primeNumber = 317;
        int secretKey = new Random().nextInt(10);
        publicKey = calcPublicKey(base,secretKey,primeNumber);
        Socket socket = new Socket("localhost",5000);
        socket.getOutputStream().write(prepareFrame(publicKey + "\0"));
        socket.getOutputStream().flush();
        String bobPublicKey = readPublicKey(socket);
        long genSecretKey = calcGeneralSecretKey(bobPublicKey,secretKey,primeNumber);
        executorService.execute(() -> {
            while (true){
                sendMessageToBob(socket, genSecretKey);

            }
        });
        executorService.execute(() -> {
            while (true) {
                readMessageFromBob(socket,genSecretKey);
            }
        });
        Thread.sleep(120000);
        executorService.shutdown();
        socket.close();
    }

    public static void sendMessageToBob(Socket socket,long genSecKey) {
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
    public static void readMessageFromBob(Socket socket, long genSecKey) {

        int temp;
        StringBuilder builder = new StringBuilder();
        try {
            while ((char)(temp = socket.getInputStream().read()) != '\0'){
                builder.append((char) temp);
            }
            System.out.println("received message from Bob: " + Crypto.decrypt(builder.toString(), genSecKey));
        }   catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private static long calcGeneralSecretKey(String bobPublicKey, int secretKey, int primeNumber) {
        return (((long)Math.pow(Long.parseLong(bobPublicKey) + 0.0,secretKey + 0.0)) % primeNumber);
    }

    public static String readPublicKey(Socket client) throws IOException {
        byte[] arr = client.getInputStream().readNBytes(512);
        StringBuilder result = new StringBuilder();

        for (byte b : arr) {
            if (b != 0) { // Проверяем, что символ не является NULL
                result.append((char) b); // Добавляем символ в результат
            }
        }

        return result.toString();
    }

    public static byte[] prepareFrame(String publicKey){
        byte[] arr = new byte[512];
        for (int i = 0; i < publicKey.length(); i++) {
            arr[i] = (byte) publicKey.charAt(i);
        }
        return arr;
    }

    public static long calcPublicKey(int base, int secretKey, int primeInteger){
        return ((long)Math.pow(base, secretKey)) % primeInteger;
    }
}
