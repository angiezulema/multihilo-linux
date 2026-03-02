/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simple_tcp_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket socket;
    private ServerMain server_main;
    
    public ServerThread (Socket socket, ServerMain server_main) {
        this.socket = socket;
        this.server_main = server_main;
    }

    @Override
    public void run() {
        try {
            
            int client_number = server_main.getClientNumber();
            System.out.println("Client" + client_number + ". has connected.");
            
            // I/O buffers:
            BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            
            // Lógica para saber si el ID es par (modulo de 2 igual a 0)
            String saludo = "Welcome! You are client number " + client_number + ".";
            if (client_number % 2 == 0) {
                saludo = saludo + " ¡Tienes mucha suerte!";
            }
            out_socket.println(saludo);
            
            String message = in_socket.readLine(); // receive "Thanks!"
            System.out.println("Client says: " + message); // display Client's message in the console
            
            socket.close();
            System.out.println("Client" + client_number + ". has disconnected.");
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

