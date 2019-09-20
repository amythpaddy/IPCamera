import java.io.*;
import java.net.Socket;

public class FileTransfer {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public FileTransfer(String address, int port) {
        try {
            socket = new Socket(address, port);
            dis = new DataInputStream(System.in);
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        String line = "";

        try {
            File file = new File("vid_test.mp4");
            byte[] b = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(b);
            assert dos != null;
            dos.writeInt(b.length);
            dos.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();

        }

        try {
            dis.close();
            dos.close();
            assert socket != null;
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new FileTransfer("127.0.0.1", 8000);
    }
}
