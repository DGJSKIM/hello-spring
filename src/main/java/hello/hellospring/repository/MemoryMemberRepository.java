package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{ //context_actions -> alt enter

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 시퀀스값 하나 올려서 그 값을 id로 저장
        store.put(member.getId(),member); // store 에 멤버 저장
        return member; 
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        return store.values().stream() //stream 객체 생성
                .filter(member -> member.getName().equals(name)) //filter로 가공하기
                //여기서는 member라는 객체에서 getName()이름을 가져온 후, name와 동일한 것 탐색
                .findAny(); //어떤 것이든 찾아서 반환, 결과
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
