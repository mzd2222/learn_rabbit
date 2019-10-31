package rabbit.test;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rabbit.test.Bean.Book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;   //注入AmqpAdmin系统管理逐渐

    /**
     * AmqpAdmin创建和删除消息队列、交换器、绑定规则等测试
     */
    @Test
    void TestAmqpAdmin(){
//        amqpAdmin.declare开头为创建组件
//        amqpAdmin.declareExchange(new DirectExchange("test_exchange"));
//        amqpAdmin.declareQueue(new Queue("test_queue"));
        amqpAdmin.declareBinding(new Binding("test_queue", Binding.DestinationType.QUEUE, "test_exchange","test_l",null));
        System.out.println("创建完成");

    }

    /**
     * 测试单播的点对点消息
     */
    @Test
    void contextLoads() {
//        message需要自己构造，可以定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routeKey,message);
//        rabbitTemplate.convertAndSend(exchange,routKey,object); 简化发送,自动序列化
        Map<String, Object> map = new HashMap<>();
        map.put("msg1", "这是第一个消息");
        map.put("msg2", "这是第二个消息");
        map.put("msg3", "这是第三个消息");
        map.put("data", Arrays.asList("hello world", 123, "sad"));
        //对象默认使用的是java默认的序列化规则。。。序列化之后看球不懂
//        rabbitTemplate.convertAndSend("exchange.direct","learn.news",map);
        rabbitTemplate.convertAndSend("test_exchange","test_l",new Book("asd222","mzd"));
    }


    //接受消息,如何将数据转为json发送
    @Test
    void rec_test(){
        Object o = rabbitTemplate.receiveAndConvert("learn.news");//简化接收
        System.out.println(o.getClass());
        System.out.println(o);
    }


    /**
     * 广播测试
     */
    @Test
    void guangbo(){
        rabbitTemplate.convertAndSend("learn_news","",new Book("asd222","mzd222"));
    }



}
