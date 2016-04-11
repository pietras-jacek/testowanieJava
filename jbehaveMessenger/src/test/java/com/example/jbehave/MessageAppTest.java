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

//    private final String VALID_SERVER = "inf.ug.edu.pl";
//    private final String INVALID_SERVER = "inf.ug.edu.eu";
//
//    private final String VALID_MESSAGE = "some message";
//    private final String INVALID_MESSAGE = "ab";

    private static String invalidServer;
    private static String validServer;
    
    private static String invalidMessage;
    private static String validMessage;

    private Messenger msg;
    private MessageService mss;

    @Given("a server")
    public void serverSetup() {
        mss = new MessageServiceSimpleImpl();
        msg = new Messenger(mss);
    }

    @When("set connection server to $server")
    public void setConnectionValidServer(String server) {
        validServer = server;
    }

    @Then("valid should return $valid_server")
    public void shouldValid(int valid_server) {
        assertEquals(valid_server, msg.testConnection(validServer));
    }

    @When("set server connection to $server")
    public void setConnectionInvalidServer(String server) {
        invalidServer = server;
    }

    @Then("invalid should return $invalid_server")
    public void shouldInvalid(int invalid_server) {
        assertEquals(invalid_server, msg.testConnection(invalidServer));
    }
    
    
    

    @When("try send a $valid_message to $valid_server")
    public void sendValidMessage(String valid_message, String valid_server) {
        validMessage = valid_message;
        validServer = valid_server;
    }

    @Then("ValidSendMessage should return $vallid_number")
    public void shouldValidMessage(int valid_number) {
        assertEquals(valid_number, msg.sendMessage(validServer, validMessage));
    }

    @When("try sending a $invalid_message to $invalid_server")
    public void sendInvalidMessage(String invalid_message, String invalid_server) {
        invalidMessage = invalid_message;
        invalidServer = invalid_server;
    }

    @Then("InvalidSendMessage should be return $invalid_number")
    public void shouldInvalidMessage(int invalid_number) {
        assertEquals(invalid_number, msg.sendMessage(invalidServer, invalidMessage));
    }

}
