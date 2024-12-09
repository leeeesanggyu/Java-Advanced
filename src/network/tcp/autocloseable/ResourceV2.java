package network.tcp.autocloseable;

public class ResourceV2 implements AutoCloseable {

    private String name;

    public ResourceV2(String name) {
        this.name = name;
    }

    public void call() {
        System.out.println(name + " call");
    }

    public void callException() throws CallException {
        System.out.println(name + " callException");
        throw new CallException(name + " exception");
    }

    @Override
    public void close() throws CloseException {
        System.out.println(name + " close");
        throw new CloseException(name + " closeException");
    }
}
