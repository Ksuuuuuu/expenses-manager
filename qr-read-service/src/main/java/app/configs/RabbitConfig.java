package app.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;

@EnableRabbit
@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue qrQueue() {
        return new Queue("qr-queue");
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("direct-exchange");
    }
    @Bean
    Binding qrBinding(Queue qrQueue, DirectExchange exchange) {
        return BindingBuilder.bind(qrQueue).to(exchange).with("qr");

    }

    @Bean
    public Queue receiptQueue() {
        return new Queue("receipt-queue");
    }

    @Bean
    Binding receiptBinding(Queue receiptQueue, DirectExchange exchange) {
        return BindingBuilder.bind(receiptQueue).to(exchange).with("receipt");
    }

//    //объявляем контейнер, который будет содержать листенер для сообщений
//     @Bean
//     public SimpleMessageListenerContainer messageListenerContainer1() {
//         SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//         container.setConnectionFactory(connectionFactory());
//         container.setQueueNames("message-queue");
//         // тут ловим сообщения из queue1
//         container.setMessageListener(message -> System.out.println("received from message-queue : " + new String(message.getBody())));
//         return container;
//     }
}
