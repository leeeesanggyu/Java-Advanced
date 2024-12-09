package network.tcp.v6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManagerV6 sessionManager = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown"));

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                log("소켓 연결: " + socket);

                SessionV6 session = new SessionV6(socket, sessionManager);
                sessionManager.add(session);
                new Thread(session).start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }
    }

    private static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        private ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");
            try {
                sessionManager.closeAll();
                serverSocket.close();
                /**
                 * 우아한 종료를 하게 되면 non 데몬 스레드의 종료 여부와 관계없이 자바 프로세스가 종료된다.
                 * 단 ShutdownHook의 실행이 끝날 때 까지는 기다려 준다.
                 * 따라서 다른 스레드가 자원을 정리하거나 필요한 로그를 남길 수 있도록 ShutdownHook의 실행을 잠시 대기한다.
                 */
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
