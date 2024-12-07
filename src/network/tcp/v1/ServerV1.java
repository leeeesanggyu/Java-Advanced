package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

/**
 * 클라리언트가 연결을 시도하면 OS 계층에서 TCP 3way-handshake가 발생하고, TCP 연결이 완료된다.
 * TPC 연결이 완료되면 서버는 OS backlog queue에 클라이언트와 서버의 TCP 연결 정보를 보관한다.
 *  - 연결 정보엔 클라이언트 IP, Port 서버의 IP, Port 정보가 모두 들어있다.
 */
public class ServerV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        /**
         * ServerSocket은 단지 클라이언트와 서버의 TCP 연결만 지원하는 특별한 소켓이다.
         * 실제 클라이언트와 서버가 정보를 주고 받으려면 Socket 객체가 필요하다.
         *
         * 1. accept()를 호출하면 backlog queue에서 TCP 정보를 조회한다.
         *   - TCP 연결 정보가 없다면, 연결 정보가 생성될 때까지 블로킹하여 대기한다.
         * 2. 정보가 들어온다면 해당 정보를 기반으로 Socket 객체를 생성한다.
         * 3. 사용한 TCP 연결 정보는 backlog queue에서 제거된다.
         */
        Socket socket = serverSocket.accept();
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        // 클라이언트로부터 문자 받기
        String received = input.readUTF();
        log("client -> server: " + received);

        // 클라이언트에게 문자 보내기
        String toSend = received + " World!";
        output.writeUTF(toSend);
        log("server -> client: " + toSend);

        // 자원 정리
        log("연결 종료: " + socket);
        output.close();
        input.close();
        socket.close();
        serverSocket.close();
    }
}
