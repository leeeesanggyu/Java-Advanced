package network.tcp.autocloseable;

public class ResourceCloseMainV2 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) { // 핵심 비지니스 로직인 핵심 예외가 안터지고 부가 예외가 올라오게 된다.
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
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
            if (resource2 != null) {
                resource2.closeException();
            }
            if (resource1 != null) {
                resource1.closeException(); // 이 코드 자원 정리 안됨
            }
        }

    }
}
