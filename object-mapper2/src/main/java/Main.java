import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        // ObjectMapper를 이용하여
        //
        /**
         *
         * dependency 추가함
         * group: 'com.fasterxml.jackson.core', name: 'jackson-databind'
         *
         * ObjectMapper를 이용하여
         * 1) DTO => String 변환
         * 2) DTO => JSON(String)
         * 3) JSON(String) => value(String or Int) 추출
         *     objectMapper.readTree(json) : String Json을 objectMapper에서 읽는다
         * 4) JSON(String) => value(List<DTO>) 추출
         *      JsonNode => ArrayNode => objectMapper.convertValue
         * 5) 정돈된 JSON 형태 String
         *      JsonNode => ObejctNode => objectNode.toPrettyString()
         */
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");
        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name =jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : " +_name);
        System.out.println("_age : " +_age);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars;

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        // JSON 객체에서 Object 객체로 형변환
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "park");
        objectNode.put("age", 20);

        // 정리된 JSON 형태로 보여준다.
        System.out.println(objectNode.toPrettyString());

    }
}
