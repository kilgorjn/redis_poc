package com.redislabs.wf.dataload;

import java.util.Scanner;

/**
 * Madhu Murty -- Redis Labs for Wells Fargo
 * Date -- 04/17/2021
 */
public class App 
{
    public static void main( String[] args )
    {
    		String hostname = "127.0.0.1"; //Assigning default Local Host Connection
    		int port = 6380; //Assigning default Local host connection
    		int dbsize = 5; //Default database size to create - 10MB -- total data size for 100 keys is 694kb
    		Scanner in = new Scanner(System.in);
			/*if ( args == null || args.length ==0) {
				System.out.println("Argument missing please enter .... ");
				System.exit(1);
			}
			if ((args[0].equals("-L")) || (args[0].equals("-S"))) {
				benchmark_input = args[0];
					} else {
						System.out.println("Invalid Argument. Acceptable Options are -I for Database Initialization or -S for test");
						System.exit(1);		
					}*/
	    	if ( args.length < 2 ) {
	    		System.out.println("Not enough argument missing number of threads and number of iteration ");
	    		System.exit(1);
	    	}
		        //host name
				hostname = args[0];
		        //port
		        port = Integer.parseInt(args[1]);
		        //DB Size to load
		        dbsize = Integer.parseInt(args[2]);

		        System.out.println("running code with these argument: Server: " + hostname + " port: " + port + " DB Size to load:" + dbsize);

			/*if ((args[0].equals("-L")) && args[1].equals("-M")){
				size_input = args[1];
			} else {
				System.out.println("Invalid Argument. Acceptable Options are -M for Database Size");
				System.exit(1);	
			}
			if ((args[1].equals("-M")) && args[2] == null){
				System.out.println("No DB size specified, using default db size of 5MB");
			} else dbsize = Integer.parseInt(args[2]);
			
			if (args[3].compareTo("-H") != 0) {
				 System.out.println("Invalid Argument. Acceptable Option is -H for Host - FQDN or IP address");
		            System.exit(1);
			 }
			 if (args[4] == null) {
				 System.out.println("No Host - FQDN or IP address has been specified, using default connection 127.0.0.1");
		            //System.exit(1);
			 } else hostname = args[4];

			 if (args[5].compareTo("-P") != 0) {
				 System.out.println("Invalid Argument. Acceptable Option is -P for Port");
		         System.exit(1); 
			 }	
			 else port = Integer.parseInt(args[6]);      
		
			*///if (benchmark_input.equals("L")){	
					
		    DataLoad dataload = new DataLoad();
		    dataload.loaddata(hostname, port,dbsize);

    }
}
