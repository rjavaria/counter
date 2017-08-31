package au.com.inteliment.server.counter.entities;

import java.util.List;
/**
 * 
 * @author rjavaria
 *
 */
public class SearchEntity {
	private List<String> searchText;

	public List<String> getSearchText() {
		return searchText;
	}

	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}
}
