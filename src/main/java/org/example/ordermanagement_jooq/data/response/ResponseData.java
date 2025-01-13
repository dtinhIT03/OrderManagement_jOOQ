package org.example.ordermanagement_jooq.data.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseData<T> {
    int status;
    String message;
    T content;
    public ResponseData(int status, String message)   {
        this.status =status;
        this.message = message;
    }
    public ResponseData(int status,String message,T content){
        this.status=status;
        this.message = message;
        this.content = content;
    }
}
