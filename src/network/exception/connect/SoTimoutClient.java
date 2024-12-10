package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 외부 서버와 통신을 하는 경우 반드시 ConnectTimeout과 SocketTimeout을 지정하자.
 */
public class SoTimoutClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();

        try {
            socket.setSoTimeout(1000);
            int read = input.read();
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
