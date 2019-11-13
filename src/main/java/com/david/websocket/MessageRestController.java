package com.david.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class MessageRestController {
    private SimpMessagingTemplate template;

    @Autowired
    public MessageRestController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping
    public ResponseEntity postMessage(@RequestBody Message newMessage) throws Exception {
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage output = new OutputMessage(newMessage.getFrom(), newMessage.getText(), time);
        this.template.convertAndSend("/topic/location", output);
        return ResponseEntity.ok("Success");

    }
}
