package hello.hellospring.controller;


/*members/new post요청에 응답 할 클래스이며 해당 post 요청을 구현하는
메서드에 parameter로 데이터타입을 클래스로 넣어주면 html 문서의 form에 위치한
input type의 name이 매개변수인 name으로 set된다.
ex) <input type="text" name="name"></input>
이 과정은 스프링이 자동으로 setter를 호출하여 set한다.*/
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
