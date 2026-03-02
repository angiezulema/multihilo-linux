import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {
    public Sender() throws Exception {
        DatagramSocket socket = new DatagramSocket();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Ingresa un mensaje en minúsculas (Ej. hola juan): ");
        String message = keyboard.nextLine();
        byte[] buffer = message.getBytes();

        // CAMBIA ESTO POR LA IP DE TU WINDOWS (Ej. "192.168.1.15")
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("172.30.44.240"), 2020);
        socket.send(packet);
        System.out.println("Enviado: " + message);

        // Recibir la respuesta invertida
        buffer = new byte[1500];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String response = new String(buffer).trim();
        System.out.println("Respuesta del Receiver: " + response);
    }

    public static void main(String[] args) {
        try { new Sender(); } catch (Exception e) { e.printStackTrace(); }
    }
}