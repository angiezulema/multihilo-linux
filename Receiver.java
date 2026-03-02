import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;

public class Receiver {
    public Receiver() throws Exception {
        DatagramSocket socket = new DatagramSocket(2020);
        System.out.println("Receiver is running on FEDORA CLON...");

        while (true) {
            byte[] buffer = new byte[1500];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet); 

            String message = new String(buffer).trim();
            System.out.println("Recibido: " + message);

            // --- LÓGICA DE CONTEO DE CARACTERES ---
            // Usamos LinkedHashMap para mantener el orden en el que aparecen las letras
            LinkedHashMap<Character, Integer> counts = new LinkedHashMap<>();
            for (char c : message.toCharArray()) {
                // Si la letra ya existe, le suma 1; si no, la crea con valor 1
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }

            // Construir el string de respuesta "a:2, b:4, c:1"
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
                sb.append(entry.getKey()).append(":").append(entry.getValue()).append(", ");
            }
            
            String result = sb.toString();
            if (result.length() > 2) {
                result = result.substring(0, result.length() - 2); // Quitar la última coma y espacio
            }

            // Devolver respuesta
            buffer = result.getBytes();
            DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
            socket.send(outPacket);
            System.out.println("Enviado de vuelta: " + result + "\n");
        }
    }

    public static void main(String[] args) {
        try { new Receiver(); } catch (Exception e) { e.printStackTrace(); }
    }
}