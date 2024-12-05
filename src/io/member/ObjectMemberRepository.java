package io.member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 모든 데이터는 바이트로 저장된다.
 * 자바 객체 직렬화(Serialization)는 메모리에 있는 객체 인스턴스를 바이트 스트림으로 변환하여 파일에 저장하거나 네 트워크를 통해 전송할 수 있도록 하는 기능이다.
 * 이 과정에서 객체의 상태를 유지하여 나중에 역직렬화 (Deserialization)를 통해 원래의 객체로 복원할 수 있다.
 * 객체 직렬화를 사용하려면 직렬화하려는 클래스는 반드시 `Serializable` 인터페이스를 구현해야 한다.
 */
public class ObjectMemberRepository {

    private static final String FILE_PATH = "temp/members-obj.dat";

    public void add(Member member) {
        List<Member> members = findAll();
        members.add(member);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(members);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Member> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object findObject = ois.readObject();
            return (List<Member>) findObject;
        } catch (FileNotFoundException e) {
          return new ArrayList<>();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
