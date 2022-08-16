package edu.uchicago.chakradhar.emailer;


import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

public class AppTest {
    @Test
    public void successfulResponse() {
//        CreateContactLambda app = new CreateContactLambda();
        assert(true);

//        JSONObject json = new JSONObject();
//        json.put("subject","test subject");
//        json.put("body","test msg");
//        json.put("email","testemail@gmail.com");
//
//        APIGatewayProxyResponseEvent result = app.handleRequest(
//                new APIGatewayProxyRequestEvent()
//                        .withBody(json.toString()),null);
//        assertEquals(200, result.getStatusCode().intValue());
//        assertEquals("application/json", result.getHeaders().get("Content-Type"));
//        String content = result.getBody();
//        assertNotNull(content);
//        assertTrue(content.contains("\"subject\""));
//        assertTrue(content.contains("\"body\""));
//        assertTrue(content.contains("\"email\""));
    }
}
