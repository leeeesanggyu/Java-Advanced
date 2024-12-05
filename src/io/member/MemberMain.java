package io.member;

import java.util.List;

public class MemberMain {

    private static ObjectMemberRepository repository = new ObjectMemberRepository();

    public static void main(String[] args) {
        Member member = new Member("id1", "salgu", 27);
        repository.add(member);
        List<Member> members = repository.findAll();
        System.out.println(members);
    }
}
