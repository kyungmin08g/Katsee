package kyungmin.katsee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/send"); // 클라이언트가 서버로 전송
    registry.enableSimpleBroker("/sub"); // 클라이언트에게 전송
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/wss") // Stomp 엔드포인트 | wss/로 들어요면 다 Stomp WebSocket 간주?ㅋㅋ
      .setAllowedOriginPatterns("*");
//      .withSockJS(); // 버전이 낮은 브라우저도 돌아가게 하겠다~ 이 소리임.
  }
}
