package nju.edu.graduation.controller;

import nju.edu.graduation.entity.ChatResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleChat(String msg, StompHeaderAccessor stompHeaderAccessor){
        String from = msg.substring(0, msg.indexOf(";"));
        String to = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
        String message = msg.substring(msg.indexOf(";") + 1, msg.lastIndexOf(";"));
        Principal user = stompHeaderAccessor.getUser();
        String name = user.getName();
        messagingTemplate.convertAndSendToUser(to, "/queue/chat", new ChatResp(message,from,to));
    }

    @MessageMapping("/ws/nf")
    @SendTo("/topic/nf")
    public String handleNF(String msg, StompHeaderAccessor stompHeaderAccessor) {
        return msg;
    }
}
