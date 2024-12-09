package network.tcp.autocloseable;

public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");

            // 핵심 예외 내부 Suppressed에 CloseException이 담겨서 온다.
            // 비핵심 예외를 숨겨서 온다.
            for (Throwable throwable: e.getSuppressed()) {
                System.out.println(throwable);
            }
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        try (ResourceV2 resource1 = new ResourceV2("resource1");
             ResourceV2 resource2 = new ResourceV2("resource2")) {
            resource1.call();
            resource2.callException();
        } catch (CallException e) {
            System.out.println("exception: " + e);
            throw e;
        }
    }
}
