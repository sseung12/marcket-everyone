package market.everyone.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.everyone.dto.Message;
import market.everyone.dto.MsgRoom;
import market.everyone.service.MsgService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
@Component
@RequiredArgsConstructor
public class CarrotWebSocketHandler extends TextWebSocketHandler {

    private final MsgService msgService;
    private final ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : {}",payload);

        Message msg = objectMapper.readValue(payload,Message.class);
        MsgRoom room = msgService.findById(msg.getRoomId());
        room.handleActions(session,msg,msgService);

    }
}
