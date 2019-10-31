package rabbit.test;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * 1.RabbitAutoConfiguration
 * 2.自动配置了和rabbitmq的连接
 * 3.RabbitProperties自动配置
 * 4.RabbitTemplate封装了RabbitMQ发送和接收消息
 * 5.AmqpAdmin + RabbitMQ系统管理功能组件
 *   AmqpAdmin创建和删除消息队列、交换器、绑定规则等
 * 6.@EnableRabbit + @RabbitListener 监听功能
 * 7.
 *
 */



@EnableRabbit //开启基于注解的Rabbitmq ,监听消息队列的内容
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
