package rabbit.test.Config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//定义自己的序列化器，将复杂数据序列化为json格式
@Configuration
public class AmqpConfig  {

    //给message指定序列化器
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}

