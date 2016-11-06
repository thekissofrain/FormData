package com.test.formdata.dto;

import java.io.Serializable;

/**
 * Created by satish.gummadi on 05-11-2014.
 */
public class ApiDetailsBean implements Serializable {
    String timestamp;
    String apiMethod;
    String jsonObj;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getJsonObj() {
        return jsonObj;
    }

    public void setJsonObj(String jsonObj) {
        this.jsonObj = jsonObj;
    }
}
