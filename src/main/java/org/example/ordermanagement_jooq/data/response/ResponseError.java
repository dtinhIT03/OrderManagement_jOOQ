package org.example.ordermanagement_jooq.data.response;

public class ResponseError extends ResponseData{
    public ResponseError(int status, String message){
        super(status,message);
    }

}
