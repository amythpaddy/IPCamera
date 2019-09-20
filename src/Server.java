import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream input;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");
            System.out.println("Waiting for client to connect");
            socket = server.accept();
            System.out.println("Client connected");
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            byte[] line;

                try {
                    line = new byte[input.readInt()];
                    input.readFully(line);
                    OutputStream os = new FileOutputStream(new File("vid_result.mp4"));
                    os.write(line);
                    os.close();
                }catch (IOException e){
                    e.printStackTrace();

            }

            System.out.println("Closing Connection");
            socket.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String a[]){
        new Server(8000);
    }
}
