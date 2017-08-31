package au.com.inteliment.server.counter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import au.com.inteliment.server.counter.entities.CountsEntity;
import au.com.inteliment.server.counter.entities.TopEntity;
import au.com.inteliment.server.counter.exception.CounterException;
import au.com.inteliment.server.counter.repository.IContentProvider;
/**
 * Counter Service implementation
 * 
 * @author rjavaria
 *
 */
@Service
public class CounterService implements ICounterService {

	private static final Logger logger = 
            LoggerFactory.getLogger(CounterService.class);
	
	@Autowired
	IContentProvider contentProvider;
	
	@Override
	public CountsEntity getTextCount(List<String> searchTexts)
	throws CounterException
	{
		final String METHOD = "getTextCount()";
		logger.debug("Entering...{}", METHOD);
		
		//Read file content
		List<String> lines = contentProvider.readFileContents();
		
		if(0 == lines.size()) {
			//Error msg and code can be defined in json file....
			String errorMsg = "No contents found!!";
			int errorCode = 2001;
			logger.error("Error msg : {}, Error Code ; {}", errorMsg, errorCode);
			throw new CounterException(errorMsg, errorCode);
		}
		
		CountsEntity countsEntity = new CountsEntity();		
		for(String line : lines) {
			for(String searchText : searchTexts) {
				int count1 = StringUtils.countOccurrencesOf(line, searchText);
				
				//with lower case
				int count2 = StringUtils.countOccurrencesOf(line, searchText.toLowerCase());
				
				if(countsEntity.getCounts().containsKey(searchText)) {
					//IF map already contains the text
					int previousCount = countsEntity.getCounts().get(searchText).intValue();
					countsEntity.getCounts().put(searchText, 
												 Integer.valueOf(previousCount + count1 + count2));
				}
				else {
					//IF first time adding the text in map 
					countsEntity.getCounts().put(searchText, Integer.valueOf(count1 + count2));
				}
			}
		}
		return countsEntity;
	}

	@Override
	public String getTopText(int topCount) 
	{
		final String METHOD = "getTopText()";
		logger.debug("Entering...{}", METHOD);
		
		//Read file content
		List<String> lines = contentProvider.readFileContents();
		
		if(0 == lines.size()) {
			return "";
		}
		
		CountsEntity countsEntity = new CountsEntity();
		
		for(String line : lines) {
			//remove character like ',' '.' ';' 
			String newLine = line.replaceAll("[,.;]", "");
			String[] words = newLine.toLowerCase().split(" ");
			
			//iterate through all words of a line
			for(String word : words) {
				if(countsEntity.getCounts().containsKey(word)) {
					countsEntity.getCounts().put(word, 
					countsEntity.getCounts().get(word) + 1);
				}
				else {
					countsEntity.getCounts().put(word, 1);
				}
			}
		}
		
		List<TopEntity> topEntityList = new ArrayList<TopEntity>();
		for(Map.Entry<String, Integer> entry : countsEntity.getCounts().entrySet()) {
			topEntityList.add(new TopEntity(entry.getKey(), entry.getValue()));
		}
		
		//sort the list in ascending order based on occurence 
		TopEntity.sort(topEntityList);
		
		//get the sublist for requested top counts
		List<TopEntity> subList = topEntityList.subList(0, topCount);
		
		//build the result
		StringBuilder builder = new StringBuilder();
		for(TopEntity topEntity : subList) {
			builder.append(topEntity.getWord());
			builder.append("|");
			builder.append(topEntity.getOccurence());
			builder.append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		
		return builder.toString();
	}
}
