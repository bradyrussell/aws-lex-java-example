package main.java.com.bradyrussell;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class Hello implements RequestHandler<Map<String,Object>, Map<String,Object>>{

    public Map<String,Object> handleRequest(Map<String,Object> input, final Context context) {
        final LexInput lexInput = new LexInput(input);

        if(!lexInput.hasConfirmationStatus()) {
            return LexOutputFactory.makeConfirmResponse("Are you sure you want to select "+lexInput.getSlots().get("abc")+"?", null, lexInput);
        }

        if(lexInput.getConfirmationStatus()) {
            return LexOutputFactory.makeSimpleResponse("Confirmed!", new HashMap<String, Object>() {{
                put("memory_limit", context.getMemoryLimitInMB());
                put("remaining_ms", context.getRemainingTimeInMillis());
            }});
        } else {
            return LexOutputFactory.makeSimpleResponse("Declined!", new HashMap<String, Object>() {{
                put("memory_limit", context.getMemoryLimitInMB());
                put("remaining_ms", context.getRemainingTimeInMillis());
            }});
        }
    }
}