package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DNS 조회
 *
 * 1. /etc/hosts 시스템의 호스트파일을 먼저 확인한다.
 * 2. 호스트파일에 정의되어 있지 않다면, DNS 서버에 요청해서 IP 주소를 얻는다.
 */
public class InetAddressMain {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println(localhost);

        InetAddress google = InetAddress.getByName("google.com");
        System.out.println(google);
    }
}
