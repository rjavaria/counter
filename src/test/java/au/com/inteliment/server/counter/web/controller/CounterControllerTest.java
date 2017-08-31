/**
 * 
 */
package au.com.inteliment.server.counter.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import au.com.inteliment.server.counter.CounterServerBoot;
import au.com.inteliment.server.counter.service.ICounterService;

/**
 * @author rjavaria
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CounterServerBoot.class)
@SpringBootTest
public class CounterControllerTest {

	@InjectMocks
	private CounterController counterController;
	
	@Mock
	private ICounterService counterService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(counterController).build();
	}
	
	/**
	 * Test method for {@link au.com.inteliment.server.counter.web.controller.CounterController#countText(au.com.inteliment.server.counter.entities.SearchEntity)}.
	 */
	@Test
	public void testCountText() throws Exception {
		//Tests if API has been implemented in REST Controller.
		this.mockMvc.perform(post("/counter-api/search").
							 contentType(MediaType.APPLICATION_JSON_VALUE).
							 header("Authorization", "Basic b3B0dXM6Y2FuZGlkYXRlcw==").
							 content("{\"searchText\": [\"Duis\", \"Sed\", \"Donec\", \"Augue\", \"Pellentesque\", \"123\"]}").
							 accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
							 andDo(print());
	}

	/**
	 * Test method for {@link au.com.inteliment.server.counter.web.controller.CounterController#topText(int)}.
	 */
	@Test
	public void testTopText() throws Exception {
		this.mockMvc.perform(get("/counter-api/top/{topCount}", 20).
				 contentType(MediaType.APPLICATION_JSON_VALUE).
				 header("Authorization", "Basic b3B0dXM6Y2FuZGlkYXRlcw==")).
				 andDo(print());
	}
}
