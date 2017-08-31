package au.com.inteliment.server.counter.repository;

import java.util.List;
/**
 * Type Definition of content provider 
 *  
 * @author rjavaria
 *
 */
public interface IContentProvider {
	/**
	 * Read the paragraph file and return the contents.
	 * <p>
	 * File location is defined as a property in application.properties
	 * Property (counter.web.env.filename) 
	 * 
	 * @return list - each list item represent a line
	 */
	public List<String> readFileContents();
}
