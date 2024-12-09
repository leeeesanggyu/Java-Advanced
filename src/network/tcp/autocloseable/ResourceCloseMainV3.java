package network.tcp.autocloseable;

public class ResourceCloseMainV3 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;
        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callException();
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            System.out.println("자원 정리");
            /**
             * 이렇게 처리하면 자원 정리 시점에 예외가 발생해도, 다음 자원을 닫을 수 있다.
             * 자원 정리 시점에 발생한 예외는 잡아서 처리했기 때문에, 자원 정리에 발생한 부가 예외가 핵심 예외를 가리지 않는다.
             */
            if (resource2 != null) {
                try {
                    resource2.closeException();
                } catch (CloseException e) {
                    // close()에서 발생한 예외는 버린다. 필요하면 로깅 정도
                    System.out.println("close exception: " + e);
                }
            }
            if (resource1 != null) {
                try {
                    resource1.closeException();
                } catch (CloseException e) {
                    System.out.println("close exception: " + e);
                }
            }
        }

    }
}
