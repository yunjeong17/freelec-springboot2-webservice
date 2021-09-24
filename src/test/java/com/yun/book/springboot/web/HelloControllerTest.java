package com.yun.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //스프링부트 테스트와 jUnit사이에 연결자 역할
@WebMvcTest(controllers=HelloController.class)
//web(spring mvc)에 집중할 수 있는 어노테이션(여기서는 컨트롤러만 사용하기때문에 선언)
// @Controller,@ControllerAdvice 사용 가능
// @Service, @Component, @Repository등은 사용할수 없음.

public class HelloControllerTest {
    @Autowired MockMvc mvc; //@Autowired >> 스프링이 관리하는 빈을 주입
    //MockMvc >> 웹 api 테스트 할 때 사용, 스프링 mvc 테스트의 시작점. http get,post 등에 대한 api 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello="hello";


        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 http get 요청
                .andExpect(status().isOk()) //mvc.perform의 결과 검증,header의 status 검증
                .andExpect(content().string(hello)); //응답 본문 내용을 검증

        // https://scshim.tistory.com/321
        // perform() ??
        // MockMvc가 제공하는 "perform() 메소드를 사용하면 브라우저에서 서버에 URL 요청을 하듯 컨트롤러를 실행"시킬 수 있다.
        // perform() 메소드는 RequestBuilder 객체를 인자로 받고, 이는 MockMvcRequestBuilders의 정적 메소드를 이용해서 생성한다.

        // andExpect() ??
        // perform() 메소드를 이용하여 요청을 전송하면, 그 결과로 ResultActions 객체를 리턴하는데 이 객체는 응답 결과를 검증할 수 있는 andExpect() 메소드를 제공한다.
        // andExpect()가 요구하는 ResultMatcher는 MockMvcResultMatchers에 정의된 정적 메소드를 통해 생성할 수 있다.

    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name="hello";
        int amount=1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount))); //jsonPath : json응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명 명시
    }
}
