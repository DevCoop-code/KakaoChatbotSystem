package com.kakaochatbot.response;

public class MessageResponse 
{
	private String text;
	private PhotoResponse photo;
	private MessageButtonResponse message_button;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public PhotoResponse getPhoto() {
		return photo;
	}
	public void setPhoto(PhotoResponse photo) {
		this.photo = photo;
	}
	public MessageButtonResponse getMessage_button() {
		return message_button;
	}
	public void setMessage_button(MessageButtonResponse message_button) {
		this.message_button = message_button;
	}
	@Override
	public String toString() {
		return "MessageResponse [text=" + text + ", photo=" + photo + ", message_button=" + message_button + "]";
	}
}
