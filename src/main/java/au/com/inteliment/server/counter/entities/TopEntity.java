package au.com.inteliment.server.counter.entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * 
 * @author rjavaria
 *
 */
public class TopEntity {
	private String word;
	private Integer occurence;
	
	public TopEntity(String word, Integer occurence) {
		this.word = word;
		this.occurence = occurence;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Integer getOccurence() {
		return occurence;
	}
	public void setOccurence(Integer occurence) {
		this.occurence = occurence;
	}
	
	public static void sort(List<TopEntity> topEntityList) {
		Collections.sort(topEntityList, new Comparator<TopEntity>() {
			@Override
			public int compare(TopEntity arg0, TopEntity arg1) {
				int arg0Occurence = arg0.occurence.intValue();
				int arg1Occurence = arg1.occurence.intValue();
				
				return arg1Occurence - arg0Occurence;
			}
		});
	}
}
