package au.com.inteliment.server.counter.exception;

import org.springframework.http.HttpStatus;
/**
 * Client Error object using Builder pattern
 *  
 * @author rjavaria
 *
 */
public class CounterClientError {
    private HttpStatus status;
    private int code;
    private String message;
    private String time;
    private String uri;
    
    public CounterClientError() {
        super();
    }
    
    public static class Builder {
        private HttpStatus status;
        private int code;
        private String message;
        private String time;
        private String uri;
        
        public Builder(HttpStatus status) {
            this.status = status;
        }
        
        public Builder code(int val) {
            this.code = val;
            return this;
        }
        
        public Builder message(String val) {
            message = val;
            return this;
        }
        
        public Builder time(String val) {
            time = val;
            return this;
        }
        
        public Builder uri(String val) {
            uri = val;
            return this;
        }
        
        public CounterClientError build() {
            return new CounterClientError(this);
        }
    }
    
    private CounterClientError(Builder builder) {
       this.status = builder.status;
       this.code = builder.code;
       this.message = builder.message;
       this.time = builder.time;
       this.uri = builder.uri;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
