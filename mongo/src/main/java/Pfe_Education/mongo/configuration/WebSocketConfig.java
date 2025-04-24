package Pfe_Education.mongo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Active la gestion des WebSockets avec STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // Enregistre les endpoints WebSocket
    @Override

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("**"); // Endpoint WebSocket standard (sans SockJS)
    }

    // Configure le broker de messages
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // Préfixe des messages envoyés au serveur
        registry.enableSimpleBroker("/topic"); // Active un broker simple pour les messages vers le client
    }
}
