package com.recruitment.sms;

import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Controller
public class SmsController {

    @Value("${phoneNumber}")
    private String myTwilioPhoneNumber;

    @Autowired
    public SmsController(
            @Value("${twilioAccountSid}") String twilioAccountSid,
            @Value("${twilioAuthToken}") String twilioAuthToken) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
    }

    @PostMapping("/api/send-messages")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessages(@RequestBody MessageDetails messageDetails) {

        Message message = Message.creator(
                new PhoneNumber(messageDetails.getNumber()),
                new PhoneNumber(myTwilioPhoneNumber),
                messageDetails.getMessage()).create();
        System.out.println("Sent message w/ sid: " + message.getSid());
    }
}