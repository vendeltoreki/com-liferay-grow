/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.grow.gamification.badges.notification;

import com.liferay.grow.gamification.model.Message;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.ClientEndpoint;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import org.osgi.service.component.annotations.Component;

/**
 * @author Vilmos Papp
 */
@ClientEndpoint
@Component(
	immediate = true,
	property = "org.osgi.http.websocket.endpoint.path=/o/gamification",
	service = Endpoint.class
)
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

		List<Session> toRemove = new ArrayList<>();

		for (Session session : _sessions) {
			if (session.isOpen()) {
				RemoteEndpoint.Basic basicRemote = session.getBasicRemote();

				basicRemote.sendText(jsonMessage);
			}
			else {
				toRemove.add(session);
			}
		}

		for (Session session : toRemove) {
			_sessions.remove(session);
		}
	}

	public void sendMessage(String message) throws IOException {
		Message plainMessage = new JsonParser().parse(message, Message.class);

		JsonSerializer jsonSerializer = new JsonSerializer();

		String jsonMessage = jsonSerializer.serialize(plainMessage);

		List<Session> toRemove = new ArrayList<>();

		for (Session session : _sessions) {
			if (session.isOpen()) {
				RemoteEndpoint.Basic basicRemote = session.getBasicRemote();

				basicRemote.sendText(jsonMessage);
			}
			else {
				toRemove.add(session);
			}
		}

		for (Session session : toRemove) {
			_sessions.remove(session);
		}
	}

	private static List<Session> _sessions = new ArrayList<>();

}