package au.com.inteliment.server.counter.entities;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author rjavaria
 *
 */
public class CountsEntity {
	private Map<String, Integer> counts = new HashMap<String, Integer>();

	public Map<String, Integer> getCounts() {
		return counts;
	}

	public void setCounts(Map<String, Integer> counts) {
		this.counts = counts;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Map.Entry<String, Integer> entry : counts.entrySet()) {
			builder.append(entry.getKey()).
					append("|").
					append(entry.getValue()).
					append(";");
		}
		return builder.toString();
	}
}
