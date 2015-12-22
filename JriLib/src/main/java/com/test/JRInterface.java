package com.test;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


/**
 * Java-R Interface 
 * A sample main class to call the R compute
 *
 */
@Component
public class JRInterface {
	
	protected static Logger log = LoggerFactory.getLogger(JRInterface.class);

	static String usage = "\nUsage: "
			+ "\n\tjava -cp .  -jar jrilib.jar -key 5 "
			+ "\n\tjava -Djava.library.path=../src/lib -cp .  -jar jrilib.jar -key 0 \n";

	@Autowired
	private ComputeStats computeStats;
	
	static Map<String,String> parameters = new HashMap<>();
	
	/**
	 * 
	 * MAIN
	 * 
	 * @param args
	 */
	//Generic comment
	public static void main(String[] args) {
		log.info("-----------------------------INITIALIZING-------------------------------------------");
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-module.xml");
		parseParameters(args);
		//Instantiate this main component
		JRInterface main = applicationContext.getBean(JRInterface.class);
		log.info("Execute Class "+main.getClass().getSimpleName()+" with full package name "+main.getClass().getName());
		main.execute();
		System.exit(1);
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
	 * Execute the migration code 
	 */
	public void execute(){
		log.info("-----------------------------INIT-------------------------------------------");
		try {
			log.info("Executing on Properties: "+parameters);
			computeStats.test();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		log.info("----------------------------------END--------------------------------------");
	}
	
	
	
}
