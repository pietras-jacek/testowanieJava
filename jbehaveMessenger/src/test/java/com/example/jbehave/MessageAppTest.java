package com.example.jbehave;

import static org.junit.Assert.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.example.jbehave.messenger.source.ConnectionStatus;
import com.example.jbehave.messenger.source.MalformedRecipientException;
import com.example.jbehave.messenger.source.MessageService;
import com.example.jbehave.messenger.source.MessageServiceSimpleImpl;
import com.example.jbehave.messenger.source.SendingStatus;

import jbehave.messenger.Messenger;

public class MessageAppTest {
	
	Messenger messenger = new Messenger(new MessageServiceSimpleImpl());

	private final String VALID_SERVER = "inf.ug.edu.pl";
	private final String INVALID_SERVER = "inf.ug.edu.eu";

	private final String VALID_MESSAGE = "some message";
	private final String INVALID_MESSAGE = "ab";
	
	@Given("a message")
	public void messageSetup() {
		messenger = new Messenger(new MessageServiceSimpleImpl());
	}
	
	@When("set connection server to $server")
	public void setConnectionServer(String server) {
		messenger.testConnection(server);
	}
	
	@Then("valid should return $valid_server")
	public void shouldValid(int valid_server) {
		assertEquals(valid_server, messenger.testConnection(VALID_SERVER));
	}
	
	@Then("invalid should return $invalid_server")
	public void shouldInvalid(int invalid_server) {
		assertEquals(invalid_server, messenger.testConnection(INVALID_SERVER));
	}
	

	@Test
	public void checkSendingMessage() {

		assertEquals(1, messenger.sendMessage(INVALID_SERVER, VALID_MESSAGE));
		assertEquals(2, messenger.sendMessage(VALID_SERVER, INVALID_MESSAGE));

		assertThat(messenger.sendMessage(VALID_SERVER, VALID_MESSAGE),
				either(equalTo(0)).or(equalTo(1)));
	}
}
