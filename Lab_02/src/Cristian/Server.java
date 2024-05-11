package algoritmodecristian;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server extends Thread {
	private final ServerSocket serverSocket;
    private long timeRecv;  // the time when receiving message from client
    private long timeSend;  // the time when sending message to client
   
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
//        serverSocket.setSoTimeout(10000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Output the server name
                String localHostName = java.net.InetAddress.getLocalHost().getHostName();
                System.out.println("Nombre de Servidor: " + localHostName);
                
                System.out.println("Esperando cliente en puerto " +
                                   serverSocket.getLocalPort() + "...");
                
                // Accept a connection from clients; blocking call
                Socket server = serverSocket.accept();
                System.out.println("Conectado en: " + server.getRemoteSocketAddress());
                
                // Receive message from clients
                DataInputStream in = new DataInputStream(server.getInputStream());
                timeRecv = System.currentTimeMillis();
                System.out.println(in.readUTF());
                
                // Send message back to clients
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                long time_on_server = System.currentTimeMillis();
                timeSend = System.currentTimeMillis();
                out.writeLong(time_on_server);    // send the total time on server back to client
                out.writeLong(timeSend);    // send the sending time to client
                
                // Close the connection
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    
    public static void main(String [] args) {
        
        //port
        //int port = Integer.parseInt(args[0]);
        int port = 9092;
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
