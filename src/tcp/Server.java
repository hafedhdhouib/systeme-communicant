package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running...");
        Socket socket1 = serverSocket.accept();
        Socket socket2 = serverSocket.accept();
        new ClientHandler(socket1, socket2).start();
    }
}