package com.redislabs.wf.dataload;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;
import java.time.LocalDate;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;


@SuppressWarnings("unused")
public class DataLoad {
	public void loaddata(String hostname, int port, int dbsize) {
		
		String ConnectionHost = hostname; //Key Initialization
		int ConnectionPort  = port; //Value Initialization
		int dbSize = dbsize; //Set key Initialization
		
		 /*Define Randomization for different parameters*/
		
        Random rFsymbol = new Random(); //Fund Symbol
        Random rFname = new Random(); //Fund Name
        Random rFfamily = new Random(); //Fund Family
        Random rFcategory = new Random(); //Fund Category
        Random rFrating = new Random(); //Fund Rating
        Random rFinvest_type = new Random(); //Fund Investment Type
        LocalDate randomDate;
        
        

        /*Min and Max for Randomizing Parameters*/
        int max = 10;
        int min = 1;
        int daymin = 1;
        int daymax = 28;
        int monthmin = 1;
        int monthmax = 12;
        int yearmin = 1960;
        int yearmax = 2010;
        int day;
        int month;
        int year;

        /*Initialize temp variables for Random Data*/
        int randomNumber;
        int begin_no;
        int end_no;
        int pipeline_counter = 0;
        String Fsymbol;
        String Ffname;
        String Fcategory;
        String Ffamily;
        String Finception_dt;
        int Frating;
        String Finvest_type;
        Map<String, String> fundProperties = new HashMap<String, String>();
        
        String[] rFsymboltemp = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};  //Fund Symbol Random Data
     
        String[] rFnamedata = {
        		"DWS RREEF Real Assets Fund - Class A",
        		"Aberdeen Income Builder Fund Class A",
        		"Thrivent Large Cap Growth Fund Class A",
        		"American Century Strategic Allocation: Aggressive Fund I Class",
        		"Horizon Active Asset Allocation Fund Investor Class",
        		"DWS RREEF Real Assets Fund - Class C",
        		"DWS RREEF Real Assets Fund - Class R",
        		"American Century Strategic Allocation: Aggressive Fund R Class",
        		"DWS RREEF Real Assets Fund - Class S",
        		"American Funds 2010 Target Date Retirement Fund Class A",
        		"American Century Strategic Allocation: Aggressive Fund R6 Class",
        		"DWS RREEF Real Assets Fund - Class R6",
        		"DWS RREEF Real Assets Fund - Class T",
        		"DWS RREEF Real Assets Fund - Class Inst",
        		"Cavanal Hill Active Core Fund Class A",
        		"Putnam Dynamic Asset Allocation Balanced Fund Class C",
        		"Thrivent Balanced Income Plus Fund Class A",
        		"Cavanal Hill Bond Fund Class A",
        		"American Beacon Balanced Fund Investor Class",
        		"American Funds 2015 Target Date Retirement Fund Class A",
        		"AllianzGI Best Styles U.S. Equity Fund Class A",
        		"Cavanal Hill Active Core Fund Class C",
        		"American Century Strategic Allocation: Conservative Fund C Class",
        		"American Century Strategic Allocation: Conservative Fund R6 Class",
        		"Invesco Greater China Fund Class A",
        		"American Century Strategic Allocation: Conservative Fund R5 Class",
        		"American Beacon Mid-Cap Value Fund R5 Class",
        		"Cavanal Hill Opportunistic Fund Class C",
        		"American Century Strategic Allocation: Conservative Fund R Class",
        		"Morgan Stanley Pathway Funds Alternative Strategies Fund",
        		"Transamerica Multi-Manager Alternative Strategies Portfolio Class R6",
        		"John Hancock Tax-Free Bond Fund Class A",
        		"Transamerica Multi-Managed Balanced Fund Class R6",
        		"Transamerica Intermediate Muni Class A",
        		"T. Rowe Price Mid-Cap Value Fund Advisor Class",
        		"Columbia Contrarian Asia Pacific Fund Institutional 2 Class",
        		"Touchstone Credit Opportunities II Fund Institutional Class",
        		"Third Avenue Real Estate Value Fund Institutional Class",
        		"Tarkio Fund",
        		"Third Avenue Real Estate Value Fund Z Class",
        		"Third Avenue Small Cap Value Fund Institutional Class",
        		"Transamerica Multi-Asset Income Fund Class A",
        		"Transamerica Multi-Manager Alternative Strategies Portfolio Class I",
        		"Transamerica Small/Mid Cap Value Fund Class R6",
        		"Transamerica Short-Term Bond Fund Class R6",
        		"PGIM QMA Small-Cap Value Fund- Class Z",
        		"Third Avenue Small Cap Value Fund Z Class",
        		"John Hancock Investment Grade Bond Fund Class A",
        		"Third Avenue Value Fund Institutional Class",
        		"Third Avenue Value Fund Z Class",
        		"Touchstone Dynamic Diversified Income Fund A",
        		"Touchstone Dynamic Diversified Income Fund C",
        		"Touchstone Dynamic Diversified Income Fund Y",
        		"TIAA-CREF Core Bond Fund Class W",
        		"T. Rowe Price Blue Chip Growth Fund I Class",
        		"Tweedy, Browne Global Value Fund II - Currency Unhedged",
        		"PGIM Jennison Diversified Growth Fund-Class A",
        		"PGIM Jennison Diversified Growth Fund-Class B",
        		"PGIM Jennison Diversified Growth Fund-Class C",
        		"Transamerica High Quality Bond R4",
        		"PGIM Jennison Diversified Growth Fund-Class R6",
        		"Transamerica High Quality Bond R",
        		"Transamerica High Quality Bond I3",
        		"PGIM Jennison Diversified Growth Fund-Class Z",
        		"Thrivent Government Bond Class A",
        		"Thrivent Government Bond S Class",
        		"T. Rowe Price Georgia Tax Free Bond Fund I Class",
        		"Tweedy, Browne Global Value Fund",
        		"Tweedy, Browne Worldwide High Dividend Yield Value Fund",
        		"TIAA-CREF Bond Index Fund Advisor Class",
        		"TIAA-CREF Bond Index Fund Institutional Class",
        		"TIAA-CREF Bond Index Fund Retail Class",
        		"TIAA-CREF Bond Index Fund Premier Class",
        		"TIAA-CREF Bond Index Fund Retirement Class",
        		"TIAA-CREF Bond Index Fund Class W",
        		"Transamerica Multi-Managed Balanced Fund Class I",
        		"Transamerica Balanced II R",
        		"Transamerica Balanced II I3",
        		"John Hancock Tax-Free Bond Fund Class C",
        		"TOBAM Emerging Markets Fund Class I",
        		"Templeton International Bond Fund Class A",
        		"TIAA-CREF Core Plus Bond Fund Premier Class",
        		"T. Rowe Price Short-Term Bond Fund I Class",
        		"Thornburg Better World International Fund Class A",
        		"Thornburg Better World International Fund Class C",
        		"Thornburg Better World International Fund Class I",
        		"Thrivent Moderately Conservative Allocation Fund Class A",
        		"Timothy Aggressive Growth Fund Class C",
        		"Thrivent Moderately Conservative Allocation Fund Class S",
        		"The Covered Bridge Fund Class A"
        		};   												//Fund Name Random Data
        
        String[] rFfamilydata = {
        		"DWS",
        		"Aberdeen",
        		"Thrivent Funds",
        		"American Century Investments",
        		"Horizon Investments",
        		"American Funds",
        		"Cavanal Hill funds",
        		"Putnam",
        		"Thrivent Funds",
        		"Cavanal Hill funds",
        		"American Beacon",
        		"Allianz Global Investors",
        		"Invesco",
        		"American Beacon",
        		"TIAA Investments",
        		"Transamerica",
        		"John Hancock",
        		"TOBAM Core Investments",
        		"Franklin Templeton Investments",
        		"T. Rowe Price",
        		"Thornburg",
        		"Thrivent Funds",
        		"Timothy Plan",
        		"Thrivent Funds",
        		"Covered Bridge"
        };														//Fund Family Random Data
        
        String[] rFcategorydata = {
        		"World Allocation",
        		"Large Growth",
        		"Tactical Allocation",
        		"World Allocation",
        		"Allocation - 70% to 85% Equity",
        		"Allocation - 50% to 70% Equity",
        		"Allocation - 30% to 50% Equity",
        		"Intermediate Core Bond",
        		"Target-Date 2015",
        		"Large Blend",
        		"China Region",
        		"Mid-Cap Value",
        		"Large-Cap Value",
        		"Innovation",
        		"Health Care",
        		"Bonds",
        		"Small-Cap Value",
        		"Target-Date 2000-2010",
        		"Target-Date 2010-2020",
        		"Target-Date 2020-2030",
        		"Target-Date 2030-2040",
        		"Target-Date 2040-2050",
        		"Foreign Large Value",
        		"Emerging Markets"
        };														// Fund Category Random Data
        
        String[] rFinvestmenttype = {
        		"Blend",
        		"Growth",
        		"Value"
        };
        
        
        
		//Configuring pool parameters
		GenericObjectPoolConfig JedisPoolConfig = new GenericObjectPoolConfig ();
        JedisPoolConfig.setMaxTotal(10); //Default is 8 -- The maximum number of connections that can be allocated from this pool.
        JedisPoolConfig.setMaxIdle(10); //Default is 8 -- The maximum number of connections that can remain idle in the pool, without extra ones being released.
        JedisPoolConfig.setMinIdle(1); //Default is 0 -- The minimum number of connections that can remain idle in the pool, without extra ones being created.
        JedisPoolConfig.setMaxWaitMillis(0) ; //Default is -1(never timeout) -- The maximum number of milliseconds that the caller needs to wait when no connection is available.
        JedisPoolConfig.setBlockWhenExhausted(true); //Default is true -- Whether the caller has to wait when the resource pool is exhausted. The following maxWaitMillis takes effect only when this value is true.
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),ConnectionHost, ConnectionPort); //Jedis Pool -- Connection Pooling	
      
        
        System.out.println("Testing Connection.....");
        Jedis jedis = new Jedis(hostname,port);
        try {
        	jedis.ping();
        } catch (Exception connectionerror) {
        	System.out.println("Connection Errors " + connectionerror);
        	System.out.println("Connection Test failed.. Please re-enter connection parameters or check if the db is running");
        	System.exit(0);
        }
        jedis.close();
        
        
        
        System.out.println(ConnectionHost + ConnectionPort);
        for(int total = 1;total<=dbsize;total++) {  
        try { 
        		Jedis jedispool = jedisPool.getResource();
			//Jedis jedis = new Jedis(ConnectionHost, ConnectionPort);
			Pipeline jspipeline = jedispool.pipelined();
			randomNumber=rFsymbol.nextInt(rFsymboltemp.length);
	        Fsymbol = rFsymboltemp[rFsymbol.nextInt(rFsymboltemp.length)] + rFsymboltemp[rFsymbol.nextInt(rFsymboltemp.length)] + rFsymboltemp[rFsymbol.nextInt(rFsymboltemp.length)] + rFsymboltemp[rFsymbol.nextInt(rFsymboltemp.length)] + rFsymboltemp[rFsymbol.nextInt(rFsymboltemp.length)];
	        randomNumber=rFname.nextInt(rFnamedata.length);
	        Ffname = rFnamedata[randomNumber];
	        randomNumber=rFfamily.nextInt(rFfamilydata.length);
	        Ffamily = rFfamilydata[randomNumber];
	        randomNumber=rFcategory.nextInt(rFcategorydata.length);
	        Fcategory = rFcategorydata[randomNumber];
	        year = yearmin + (int) Math.round(Math.random() * (yearmax - yearmin));
	        month = monthmin + (int) Math.round(Math.random() * (monthmax - monthmin));
	        day = daymin + (int) Math.round(Math.random() * (daymax - daymin));
	        randomDate = LocalDate.of(year, month, day);
	        randomNumber=rFinvest_type.nextInt(rFinvestmenttype.length);
	        Finvest_type = rFinvestmenttype[randomNumber];
	        Frating = rFrating.nextInt((max - min) + 1);
	        
	        //Add to Hash
	        
	        fundProperties.put("fundname", Ffname);
	        fundProperties.put("Fundfamily", Ffamily);
	        fundProperties.put("Category", Fcategory);
	        fundProperties.put("InceptionDate", String.valueOf(randomDate));
	        fundProperties.put("InvestmentType", Finvest_type);
	        fundProperties.put("Rating", String.valueOf(Frating));
	        jedispool.hset(Fsymbol, fundProperties);
	        jspipeline.hset(Fsymbol, fundProperties);
	        pipeline_counter++;
	        if (pipeline_counter == 5 || total == dbsize) {
	        	jspipeline.sync();
	        }
	    	System.out.println(Ffname + " " + "\n" + Ffamily + " " + "\n" + Fcategory + "\n" + randomDate + "\n" + Fsymbol);
	    //	jedisPool.close();
		} catch (Exception operation) {
    		System.out.print("Operational Errors " + operation); //Capture Errors
		}
        //jspipeline.sync();
		}
        jedisPool.close();
	} 
	}

