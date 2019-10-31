package rabbit.test.Service;


import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbit.test.Bean.Book;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

@Service
public class BookService {

    private static final String ENCODING = Charset.defaultCharset().name();

    @RabbitListener(queues = "learn")
    public void receive(Book book){
        System.out.println("收到消息： " + book.toString());
    }

    @RabbitListener(queues = "learn.news")
    public void receive2(Message msg){
//        System.out.println(msg.toString());
//        System.out.println(ByteArrayUtil.toHexString(msg.getBody()));
        System.out.println(msg.getMessageProperties());//头部信息
        try {
            String s =  new String(msg.getBody(), ENCODING);
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
