package com.daulio.response;


import java.util.ArrayList;
import java.util.List;

/**
*
* @author daulio
* @param <T>
*/

public class Response<T> {
	
	private T data;
    private List<String> errors = new ArrayList<>();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        if (errors == null) {
            return new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
