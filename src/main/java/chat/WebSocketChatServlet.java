package chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;
import java.util.concurrent.TimeUnit;


@WebServlet(name="WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    //private static final int LOGOUT_TIME=10*60*1000;
    private final ChatService chatService;

    public WebSocketChatServlet(){
        this.chatService=new ChatService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(TimeUnit.MINUTES.toMillis(10));
        factory.setCreator(((req, resp) -> new ChatWebSocket(chatService)));
    }
}
