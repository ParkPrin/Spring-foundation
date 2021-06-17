package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Req<T> {

    private Header header;
    private T responseBody;

    public static class Header {
        private String respolnseCode;

        public String getRespolnseCode() {
            return respolnseCode;
        }

        public void setRespolnseCode(String respolnseCode) {
            this.respolnseCode = respolnseCode;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "respolnseCode='" + respolnseCode + '\'' +
                    '}';
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", responseBody=" + responseBody +
                '}';
    }
}
