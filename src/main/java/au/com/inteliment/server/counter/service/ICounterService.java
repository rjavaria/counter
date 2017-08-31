package au.com.inteliment.server.counter.service;

import java.util.List;

import au.com.inteliment.server.counter.entities.CountsEntity;
import au.com.inteliment.server.counter.exception.CounterException;
/**
 * Controller delegates API business logic to Service - defines all functions  
 * 
 * @author rjavaria
 *
 */
public interface ICounterService {
	/**
	 * Search the text and returns the respective counts.
	 * 
	 * @param searchText List of texts to search
	 * @return counts of individual searched text
	 * @throws CounterException
	 */
	public CountsEntity getTextCount(List<String> searchText) throws CounterException;
	
	/**
	 * Find top N number of texts which has highest counts.
	 * 
	 * @param topCount Top N texts
	 * @return Comma separated (CSV) list with text and count (e.g. text1|100;text2|91 ) 
	 */
	public String getTopText(int topCount);
}
