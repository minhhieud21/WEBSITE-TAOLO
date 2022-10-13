package com.example.JavaSpring.models;

public class ResponseObject {
    private boolean status;
    private Object data;

    public ResponseObject(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
