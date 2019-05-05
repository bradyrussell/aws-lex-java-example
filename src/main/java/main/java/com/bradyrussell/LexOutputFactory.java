package main.java.com.bradyrussell;

import java.util.HashMap;

/*        HashMap<String, Object> dialogMessage = new HashMap<String, Object>();
        HashMap<String, Object> dialogAction = new HashMap<String, Object>();

        dialogMessage.put("contentType", "PlainText");
        dialogMessage.put("content", message);

        dialogAction.put("message", dialogMessage);
        dialogAction.put("fulfillmentState", "Fulfilled");
        dialogAction.put("type", "Close");

        HashMap<String, Object> internalMap = new HashMap<String, Object>();

        if(sessionAttributes != null) internalMap.put("sessionAttributes", sessionAttributes);

        internalMap.put("dialogAction", dialogAction);
        return internalMap;*/

public class LexOutputFactory {
    public static HashMap<String,Object> makeSimpleResponse(String message, HashMap<String,Object> sessionAttributes) {
        return makeBaseResponse("Fulfilled", "Close", "PlainText", message, sessionAttributes);
    }

    public static HashMap<String,Object> makeFailedResponse(String message, HashMap<String,Object> sessionAttributes) {
        return makeBaseResponse("Failed", "Close", "PlainText", message, sessionAttributes);
    }

    public static HashMap<String,Object> makeConfirmResponse(String message, HashMap<String,Object> sessionAttributes, LexInput input) {
        return makeConfirmResponse(message, sessionAttributes, (String) input.getIntent().get("name"), input.getSlots());
    }

    public static HashMap<String,Object> makeConfirmResponse(String message, HashMap<String,Object> sessionAttributes, String intentName, HashMap<String,Object> intentSlots) {
        HashMap<String, Object> baseResponse = makeBaseResponse("ConfirmIntent", "PlainText", message, sessionAttributes);
        HashMap<String, Object> dialogAction = (HashMap<String, Object>) baseResponse.get("dialogAction");

        dialogAction.put("intentName", intentName);
        dialogAction.put("slots", intentSlots);

        return baseResponse;
    }

    public static HashMap<String,Object> makeBaseResponse(final String fulfillmentState, final String dialogActionType, final String contentType, final String message, HashMap<String,Object> sessionAttributes) {
        final HashMap<String, Object> dialogMessage = new HashMap<String, Object>() {{
            put("contentType", contentType);
            put("content", message);
        }};

        final HashMap<String, Object> dialogAction = new HashMap<String, Object>() {{
            put("message", dialogMessage);
            if(fulfillmentState != null) put("fulfillmentState", fulfillmentState);
            put("type", dialogActionType);
        }};

        HashMap<String, Object> internalMap = new HashMap<String, Object>() {{
            put("dialogAction", dialogAction);
        }};

        if(sessionAttributes != null) internalMap.put("sessionAttributes", sessionAttributes);

        return internalMap;
    }

    public static HashMap<String,Object> makeBaseResponse(final String dialogActionType, final String contentType, final String message, HashMap<String,Object> sessionAttributes) {
        return makeBaseResponse(null,dialogActionType,contentType,message,sessionAttributes);
    }

}
