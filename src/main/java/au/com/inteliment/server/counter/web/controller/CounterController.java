/**
 * 
 */
package au.com.inteliment.server.counter.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static au.com.inteliment.server.counter.common.Constants.URI.*;
import static au.com.inteliment.server.counter.common.Constants.VAR.*;
import au.com.inteliment.server.counter.entities.CountsEntity;
import au.com.inteliment.server.counter.entities.SearchEntity;
import au.com.inteliment.server.counter.service.ICounterService;

/**
 * @author rjavaria
 *
 */

@RestController
@RequestMapping({"/api/counter", "counter-api"})
public class CounterController {

	private static final Logger logger = 
            LoggerFactory.getLogger(CounterController.class);
            		
	@Autowired
	private ICounterService counterService;
	
	/**
	 * API to search the text and returns the respective counts.
	 * 
	 * @param searchEntity List of texts to search
	 * @return counts of individual searched text
	 * @throws Exception
	 */
	@RequestMapping( value = SEARCH,
			 		 method = POST,
			 		 headers = "Accept=application/json",
			 		 produces = {MediaType.APPLICATION_JSON_VALUE}
					)
	public ResponseEntity<CountsEntity> 
	countText( @RequestBody SearchEntity searchEntity) throws Exception {
		logger.info("Count words for searchText : {}", searchEntity.getSearchText());
		
		CountsEntity countsEntity = counterService.getTextCount(searchEntity.getSearchText());
		
		logger.info("Searched text counts : {}", countsEntity.toString());
		return new ResponseEntity<>(countsEntity, HttpStatus.OK);
	}

	/**
	 * API to find top N number of texts which has highest counts 
	 * 
	 * @param topCount Top N texts
	 * @return Comma separated (CSV) list with text and count (e.g. text1|100;text2|91 ) 
	 */
	@RequestMapping( value = TOP,
			 		 method = GET
			       )
	public @ResponseBody String
	topText( @PathVariable(TOPCOUNT) int topCount) {
		logger.info("Top texts count : {}", topCount);
		
		return counterService.getTopText(topCount);
	}
	
	@RequestMapping(value = HELLO)
	String hello()
	{
	   return "Hello Counter!";
	}
}