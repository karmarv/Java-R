

A.) Execution Instructions

	1.) Go to the base folder where the pom.xml file is
		Build the jar package by using 
		# mvn package
		
	2.) Go to the target folder and execute the java archive
		# java -Djava.library.path=../src/lib -cp .  -jar jrilib.jar -key 0
		
B.) Code flow		

	1.) The entry point to the code is com.test.JRInterface.java
	2.) JRInterface.java calls the ComputeStats.java which is actually responsible for loading and executing the R script.
	3.) ComputeStats.java initializes the REngine and executes the ComputeSum.R script which takes two inputs and returns a sum.
	
C.) Packaging 

	1.) The code is maven packaged and has 64 bit R-3.0 Dlls bundled with it.
	 
