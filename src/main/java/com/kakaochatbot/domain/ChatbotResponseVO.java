package com.kakaochatbot.domain;

import com.kakaochatbot.response.MessageResponse;

public class ChatbotResponseVO 
{
	private MessageResponse message;

	public MessageResponse getMessage() {
		return message;
	}

	public void setMessage(MessageResponse message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "KakaOResponseVO [message=" + message + "]";
	}
}
