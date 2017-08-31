package au.com.inteliment.server.counter.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import au.com.inteliment.server.counter.environment.WebApplicationConfig;

/**
 * Content provider implementation
 * 
 * @author rjavaria
 *
 */
@Component
public class ContentProvider implements IContentProvider {

	private static final Logger logger = 
            LoggerFactory.getLogger(ContentProvider.class);
            		
	@Autowired
	private WebApplicationConfig webApplicationConfig;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private List<String> lines = new ArrayList<String>();
	private long lastModifiedTime = 0L;
	
	@Override
	public List<String> readFileContents() 
	{
		final String METHOD = "readFileContents()";
		logger.debug("Entering...{}", METHOD);
		
		//load the file
		String fileName = webApplicationConfig.getFilename();
		String location = "classpath:" +fileName;
		Resource contents = resourceLoader.getResource(location);
		
		BufferedReader reader = null;
		try {
			/**
			 * If file's last modified time stamp is same, do NOT read again for each new request
			 * Return from cache  
			 */
			long modifiedTime = lastModifiedTime;
			lastModifiedTime = contents.getFile().lastModified();
			if(modifiedTime == lastModifiedTime) {
				logger.debug("Content file hasn't modified, returning contents from cache.");
				return lines;
			}
			
			logger.debug("Reading content file...");
			InputStream iStream = contents.getInputStream();			
			reader = new BufferedReader(new InputStreamReader(iStream));
			
			String line;
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			logger.error("Exception : {}", e.getMessage());
		} finally {
			if(null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Exception : {}", e.getMessage());
				} 
			}
		}
		return lines;
	}
}