package network.exception.close.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

/**
 * TCP 연결을 종료하려면 서로 FIN 메세지를 보내야 한다.
 * 1. A가 B로 FIN 메세지를 보낸다.
 * 2. FIN 메세지를 받은 B도 FIN 메세지를 보낸다.
 *
 * socket.close()를 호출하면 TCP에서 종료의 의미인 FIN 패킷을 상대방에게 전달한다.
 * FIN 패킷을 받으면 상대방도 socket.close()를 호출해서 FIN 패킷을 상대방에게 전달해야 한다.
 */
public class NormalCloseClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();

//        readByInputStream(input, socket);
//        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
        log("read = " + read);

        if (read == -1) {
            input.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine();
        log("readString = " + readString);

        if (readString == null) {
            br.close();
            socket.close();
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);

        try {
            dis.readUTF();
        } catch (EOFException e) {
            log(e);
        } finally {
            dis.close();
            socket.close();
        }
    }
}
