package com.liferay.grow.gamification.badges.notification;

import com.liferay.grow.gamification.model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.ClientEndpoint;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.osgi.service.component.annotations.Component;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

@Component(
	     immediate = true,
	     property = {"org.osgi.http.websocket.endpoint.path=/o/gamification"},
	     service = Endpoint.class
	 )
@ClientEndpoint
public class BadgeWebSocketEndpoint extends Endpoint {
	
	@Override
    public void onOpen(final Session session, EndpointConfig endpointConfig) {
		_sessions.add(session);
        session.addMessageHandler(
            new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String text) {
                try {
                    sendMessage(text);
                }
                catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        });
    }
	public void sendMessage(Message message) throws IOException {
		JsonSerializer jsonSerializer = new JsonSerializer();

		String jsonMessage = jsonSerializer.serialize(message);

		for (Session session : _sessions) {
			if (session.isOpen()) {
				session.getBasicRemote().sendText(jsonMessage);
			}			
		}
		for (Session session : _sessions) {
			if (!session.isOpen()) {
				_sessions.remove(session);
			}
		}
	}
	public void sendMessage(String message) throws IOException {
		Message plainMessage = new JsonParser().parse(message, Message.class);

		JsonSerializer jsonSerializer = new JsonSerializer();

		String jsonMessage = jsonSerializer.serialize(plainMessage);

		for (Session session : _sessions) {
			if (session.isOpen()) {
				session.getBasicRemote().sendText(jsonMessage);
			}			
		}
		for (Session session : _sessions) {
			if (!session.isOpen()) {
				_sessions.remove(session);
			}
		}
	}

	private static List<Session> _sessions = new ArrayList<>();
}
