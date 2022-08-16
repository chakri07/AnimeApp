package edu.uchicago.chakradhar.emailer;


import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Handler for requests to Lambda function.
 */
public class CreateContactLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
    private final DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        Message msg = null;
        try {
            msg = objectMapper.readValue(input.getBody(), Message.class);
        } catch (JsonProcessingException e) {
            return response
                    .withBody(String.format("{%s}", e.getMessage()))
                    .withStatusCode(400);
        }

        JSONObject json = new JSONObject();
        json.put("name", msg.getName());
        json.put("body", msg.getBody());
        json.put("email", msg.getEmail());

        Table contactTable = dynamoDB.getTable(System.getenv("CONTACT_TABLE"));
        Item item = new Item()
                .withPrimaryKey("email", msg.getEmail())
                .withString("body", msg.getBody())
                .withString("name", msg.getName());
        contactTable.putItem(item);

        // send an email to the user part from here
        String emailBody = "";
        emailBody = emailBody + "Request from " + msg.getName() +
                " with the message" + "\n" +  msg.getBody() +
                "\n" + " you can reach him/her at: " + msg.getEmail();
        try {
            Emailer.sendEmail(emailBody);
        } catch (Exception e) {
            return response
                    .withStatusCode(500)
                    .withBody(e.getMessage());
        }


        return response
                .withStatusCode(200)
                .withBody(json.toString());
    }
}
