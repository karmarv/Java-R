package com.test;

import org.rosuda.JRI.Rengine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ComputeStats {
	
	protected static Logger log = LoggerFactory.getLogger(ComputeStats.class);
	
	public void test() {
		try { 
			log.info("-----------------------------SUM-------------------------------------------");
			log.info("JRI Path "+ System.getProperty("java.library.path"));
			// just making sure we have the right version of everything
			if (!Rengine.versionCheck()) {
				log.error("** Version mismatch - Java files don't match library version.");
				System.exit(1);
			}
			Rengine re=new Rengine(null, false, null);			
			re.eval("source('../src/main/resources/rlib/ComputeSum.R')"); //
			int num1=10;
			int num2=10;
			double sum=re.eval("myAdd("+num1+","+num2+")").asDouble();
			log.info("Sum is " + sum);
		} catch(Exception e) {
			log.error(e.getMessage(),e);
		};
		
	}

}
