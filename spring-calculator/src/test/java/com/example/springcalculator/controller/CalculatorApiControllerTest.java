package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.component.DollarCalculator;
import com.example.springcalculator.component.MarketApi;
import com.example.springcalculator.dto.Req;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @WebMvcTest: Test료할 Controller
@WebMvcTest(CalculatorApiController.class)
// @Import({Calculator.class, DollarCalculator.class}): CalculatorApiController에서 하위 주입으로 받고 있는 빈들을 Import 해야한다.
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiControllerTest {


    // @MockBean: MarketApi를 실제 사용하는 것이 아닌 내가 임의로 정의한 결과값을 설정하기 위한 빈설정
    @MockBean
    private MarketApi marketApi;

    // MockMvc 사용하기 위한 주입
    @Autowired
    private MockMvc mockMvc;

    // @BeforeEach: @Test가 진행되기전 미리 설정하는 Annotation
    @BeforeEach
    public void init(){
        // Mockito 설정: 실제가 아닌 임시값을 설정함
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    /**
     * mockMvc.perform : header, URI, queryparam, body 등을 내부에 입력한다.
     *
     * Method:  .get(URL주소), .post(URL주소) 로입력하면 됨
     * query: .queryParam("key", "value")
     * header: .contentType(입력될 데이터 타입)  .content(입력될 데이터)
     *
     * andExpect: 예상되는 값
     *  상태코드: MockMvcResultMatchers.status() =>  200을 기대하면 .isOk()
     *  body 또는 content: MockMvcResultMatchers.content()
     *  Json 형태의 결과값 확인: MockMvcResultMatchers.jsonPath("$.키값").value("결과값")
     *
     * .andDo(MockMvcResultHandlers.print()): 테스트코드 실행
     *
     * ------------------------------------
     *         mockMvc.perform(
     *                 MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
     *                 .queryParam("x", "10")
     *                 .queryParam("y", "10")
     *         ).andExpect(
     *                 MockMvcResultMatchers.status().isOk()
     *         ).andExpect(
     *                 MockMvcResultMatchers.content().string("60000")
     *         ).andDo(MockMvcResultHandlers.print());
     *
     *         mockMvc.perform(
     *                 MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
     *                 .contentType(MediaType.APPLICATION_JSON)
     *                 .content(json)
     *         ).andExpect(
     *                 MockMvcResultMatchers.status().isOk()
     *         ).andExpect(
     *                 MockMvcResultMatchers.jsonPath("$.result").value("0")
     *         ).andExpect(
     *                 MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
     *         ).andDo(MockMvcResultHandlers.print());
     *
     */

    @Test
    public void sumTest() throws Exception {
        // http://localhost:8080/api/sum




        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                .queryParam("x", "10")
                .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void minusTest() throws Exception {

        Req req = new Req();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(MockMvcResultHandlers.print());
    }
}
