package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler extends Thread {
    private Socket socket1;
    private Socket socket2;
    private BufferedReader in1;
    private BufferedReader in2;
    private PrintWriter out1;
    private PrintWriter out2;

    public ClientHandler(Socket socket1, Socket socket2) throws IOException {
        this.socket1 = socket1;
        this.socket2 = socket2;
        in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
        out1 = new PrintWriter(socket1.getOutputStream(), true);
        in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        out2 = new PrintWriter(socket2.getOutputStream(), true);
    }

    public void run() {
        try {
            while (true) {
                String message1 = in1.readLine();
                if (message1 == null) {
                    break;
                }
                System.out.println("Client 1: " + message1);
                out2.println("Client 1: " + message1);

                String message2 = in2.readLine();
                if (message2 == null) {
                    break;
                }
                System.out.println("Client 2: " + message2);
                out1.println("Client 2: " + message2);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            try {
                socket1.close();
                socket2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
