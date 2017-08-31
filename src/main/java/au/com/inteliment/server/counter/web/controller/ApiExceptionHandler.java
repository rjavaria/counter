package au.com.inteliment.server.counter.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import au.com.inteliment.server.counter.common.ApiUtils;
import au.com.inteliment.server.counter.exception.CounterClientError;
import au.com.inteliment.server.counter.exception.CounterException;

/**
 * Exception Handler across application.
 * 
 * @author rjavaria
 *
 */

@ControllerAdvice
public class ApiExceptionHandler { 

    private static final Logger logger = 
            LoggerFactory.getLogger(ApiExceptionHandler.class);
    
    @ExceptionHandler(CounterException.class)
    public ResponseEntity<CounterClientError> counterError(HttpServletRequest request, CounterException e) {
        HttpStatus status = getHttpStatus(e.getErrorCode());
        
        CounterClientError clientError = new CounterClientError.Builder(status).
                                                                  code(e.getErrorCode()).
                                                                  message(e.getMessage()).
                                                                  time(getTime()).
                                                                  uri(request.getRequestURI()).
                                                                  build();
        
        logger.info("Counter Client Error: {}", ApiUtils.toJsonString(clientError));
        return new ResponseEntity<CounterClientError>(clientError, status);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CounterClientError> handleRuntimeException(HttpServletRequest request, 
                                                                  RuntimeException e) {
        
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CounterClientError clientError = new CounterClientError.Builder(status).
                code(0).
                message(e.getMessage()).
                time(getTime()).
                uri(request.getRequestURI()).
                build();
        
        logger.info("Counter Client Error: {}", ApiUtils.toJsonString(clientError));
        return new ResponseEntity<CounterClientError>(clientError, status);
    }
    
    private HttpStatus getHttpStatus(int errorCode) {
        String code = String.valueOf(errorCode);
        String statusStr = code.substring(0, 3);
        
        return HttpStatus.valueOf(Integer.parseInt(statusStr));
    }
    
    private String getTime() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(System.currentTimeMillis());
        String time = calendar2String(cal);
        return time;
    }
    
    public static String calendar2String(Calendar cal) {
        if (cal == null)
            return null;
        
        return DEFAULT_CALENDAR_FORMAT.format(cal.getTime());
    }
    
    private static final SimpleDateFormat DEFAULT_CALENDAR_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss");
}
