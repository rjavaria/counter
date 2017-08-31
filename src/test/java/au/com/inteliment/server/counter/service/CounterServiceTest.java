package au.com.inteliment.server.counter.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.com.inteliment.server.counter.CounterServerBoot;
import au.com.inteliment.server.counter.entities.CountsEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CounterServerBoot.class)
public class CounterServiceTest {
	
	@Autowired
	private ICounterService counterService;
		
	@Test
	public final void testGetTextCount() throws Exception {
		
		List<String> searchTexts = new ArrayList<String>();
		searchTexts.add("Duis");		
		searchTexts.add("Augue");
		
		CountsEntity counts = counterService.getTextCount(searchTexts);
		
		Assert.assertEquals(11, counts.getCounts().get("Duis").intValue());
		Assert.assertEquals(7, counts.getCounts().get("Augue").intValue());
	}

	@Test
	public final void testGetTopText()  throws Exception {
		String topText = counterService.getTopText(20);
		Assert.assertEquals("eget|17,vel|17,sed|16,in|15,et|14,eu|13,ut|13,sit|12,nulla|12,metus|12,amet|12,id|12,ac|12,ipsum|11,duis|11,nec|11,vitae|11,at|11,dolor|10,non|10", 
				topText);
	}
}