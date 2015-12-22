package com.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Rahul Vishwakarma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-module.xml" })
public class JRInterfaceTest {

	protected static Logger log = LoggerFactory.getLogger(JRInterfaceTest.class);
	static String usage = "Usage: java -cp .  -jar jrilib.jar -key 5 ";	
	static Map<String,String> parameters = new HashMap<>();
	
	@Autowired
	private JRInterface wjrInterface;
	
	/**
	 * 
	 * MAIN
	 * 
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		log.info("-----------------------------INITIALIZING-------------------------------------------");
		String MY_TEST_VARIABLE = "My Test variable";
		log.info(">> "+MY_TEST_VARIABLE.getClass().getSimpleName());
		log.info(">> "+MY_TEST_VARIABLE.getClass().getCanonicalName());
		log.info(">> "+MY_TEST_VARIABLE.getClass().getName());
		
		for(Field f : MY_TEST_VARIABLE.getClass().getFields()) {
			log.info(f.getGenericType() +" "+f.getName() + " = " + f.get(MY_TEST_VARIABLE));
		}
	}

	/**
	 * Parse parameters
	 * 
	 * @param args
	 */
	public static void parseParameters(String[] args){
		if (args.length > 0) {
	        for(int i = 0; i < args.length; i++) {
	        	log.debug(args[i] + " ", false);
	        }
	        if(args.length == 1) {
	        	System.out.println(usage);
	        	log.error(usage);
	        	System.exit(1);
	        }else{
	          for(int a=0; a<args.length; a++){
				if((a+1)<args.length){
					try {
						String key = args[a];
						String value = args[++a];
						parameters.put(key,value);
					} catch (Exception e) {
						log.error("Unable to parse parameters");
					}
				}else{
					log.error("Unable to parse parameters "+args[a]);
				}
	          }
	        }
		}else
			log.error("No Arguments supplied. "+usage);
	}	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		log.info("-----------------------------------------------------------------START---------------------------------------------------------");
		//System.setProperty("java.library.path", "C:/awnics/Api/workspace/blogit/mars-space/JriLib/src/lib");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		log.info("--------------------------------------------------------------------------------------------------------------------------------");
	}

	//@Ignore
	@Test
	public void testInitWJRI(){
		log.info("-----------------------------TEST-------------------------------------------");
		try {
			log.info("Execute Class "+wjrInterface.getClass().getSimpleName()+" with full package name "+wjrInterface.getClass().getName());
			wjrInterface.execute();
			log.info("-----------------------------END-------------------------------------------");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
	}
}
