package hello.hellospring.domain;

public class Member {

    private Long id;       // 시스템이 값을 정함
    private String name;   // 회원가입 시 고객이 입력하는 값

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
