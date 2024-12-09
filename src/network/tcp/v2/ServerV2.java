package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        Socket socket = serverSocket.accept();
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        while (true) {
            /**
             * 클라이언트로부터 문자 받기
             * Client를 하나 더 만들어 시작하면 Server OS의 TCP 수신 버퍼에서 대기하게 된다.
             * 여기서 소켓 객체 없이 서버 소켓만으로도 TCP 연결은 완료가 된다.
             * 하지만 연결 이후에 서로 메세지를 주고 받으려면 소켓객체가 더 필요하다.
             */
            String received = input.readUTF();
            log("client -> server: " + received);

            if (received.equals("exit")) {
                break;
            }

            // 클라이언트에게 문자 보내기
            String toSend = received + " World!";
            output.writeUTF(toSend);
            log("server -> client: " + toSend);
        }

        // 자원 정리
        log("연결 종료: " + socket);
        output.close();
        input.close();
        socket.close();
        serverSocket.close();
    }
}
