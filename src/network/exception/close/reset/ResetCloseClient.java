package network.exception.close.reset;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때 까지 잠시 대기

        // client -> server: PUSH[1]
        output.write(1);

        // client <- server: RST
        // RST 패킷이 도착했다는 것은 현재 TCP 연결에 심각한 문제가 있으므로 해당 연결을 사용하면 안된다는 의미
        Thread.sleep(1000); // RST 메세지 전송 대기

        try {
            // java.net.SocketException: Connection reset
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            // java.net.SocketException: Broken pipe
            output.write(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        log("연결 종료: " + socket.isClosed());

        // java.net.SocketException: Socket is closed
        // 자신이 소켓을 닫은 이후에 `read()` , `write()` 호출
    }
}
