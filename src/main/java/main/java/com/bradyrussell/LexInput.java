package main.java.com.bradyrussell;

import java.util.HashMap;
import java.util.Map;

public class LexInput {
    private Map<String,Object> input;

    public LexInput(Map<String, Object> input) {
        this.input = input;
    }

    HashMap<String,Object> getIntent(){
        return (HashMap<String, Object>) input.get("currentIntent");
    }

    HashMap<String,Object> getSlots(){
        return (HashMap<String, Object>) getIntent().get("slots");
    }

    HashMap<String,Object> getSessionAttributes(){
        if(!input.containsKey("sessionAttributes")) return new HashMap<String, Object>();
        return (HashMap<String, Object>) input.get("sessionAttributes");
    }

    public boolean hasConfirmationStatus(){
        return getIntent().containsKey("confirmationStatus") && getIntent().get("confirmationStatus") != null  && !((String)(getIntent().get("confirmationStatus"))).equalsIgnoreCase("none");
    }

    public Boolean getConfirmationStatus(){
        if(!hasConfirmationStatus()) return null;
        else if(((String)getIntent().get("confirmationStatus")).equalsIgnoreCase("confirmed")) return true;
        return false;
    }

    @Override
    public String toString() {
        return input.toString();
    }
}
