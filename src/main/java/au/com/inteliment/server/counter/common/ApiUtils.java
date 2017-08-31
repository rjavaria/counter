package au.com.inteliment.server.counter.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author rjavaria
 *
 */
public class ApiUtils {
    
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = 
            LoggerFactory.getLogger(ApiUtils.class);
    
    public static String toJsonString(Object obj) {
        String jsonStr = null;
        try {
        	jsonStr = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
          e.printStackTrace();
          logger.debug(e.getMessage());
        }
        return jsonStr;
      }
}
