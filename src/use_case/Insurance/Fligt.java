import javax.cypher.Cypnet;
import mjson.Json;
import java.util.Map;

public class Fligt {
	static Json defj = Json.object();
    public static void main(String[] args) {
		//Set the token information for flight delay insurance
		Cypnet.setTokenInfo("FLGI", "Fligt Insurance", 1000000000, "" );
	}	
	//Set insurance fee
    public static String feeSet(long fee ) {
	   if( Cypnet.getAddress("") != Cypnet.getAddress("owner") )
		   return "Permission denied, must be the contract owner";
	   Json j = getStateJson("");
	   j.set("fee", fee );
	   Cypnet.setState("@FLGI", j.toString()); 

       return "ok";
	}	
	
	//User buys insurance with token
    public static String buy(long number ) {
	   String  fromAddr = Cypnet.getAddress("");
	   Json j = getStateJson("");
	   long fee = getJsonValueI(j, "fee" );
	   if( fee == 0 )
		   return "not found fee";
	   
	   boolean ok = Cypnet.transfer(fromAddr, "owner", fee*number);
	   if( ok ){
		   Json fj = j.at(fromAddr);
		   if( fj == null ){
			   j.set(fromAddr, Json.object().set("total", number));
			   Cypnet.setState("@FLGI", j.toString()); 
			   return "ok1";
		   }
		   
		  long n = getJsonValueI(fj, "total" );
		  fj.set("total", n + number );
	      Cypnet.setState("@FLGI", j.toString()); 
	   }
	   
	   return "ok";
	}
	
	//User taking a flight
    public static String flight(String flightNo, long useNumber ) {
	   String  fromAddr = Cypnet.getAddress("");
	   Json j = getStateJson("");
	   Json fj = j.at(fromAddr);
	   if( fj == null )
		   return "You have not purchased insurance yet";
	   long n = getJsonValueI(fj, "total" );
	   if( n == 0 )
		   return "You have not purchased insurance yet";
	   if( n < useNumber )
		   useNumber = n;
	   fj.set("total", n - useNumber );
	   n = getJsonValueI(fj, flightNo );
	   fj.set("@"+flightNo, n+useNumber );
	   Cypnet.setState("@FLGI", j.toString()); 
	   return "ok";
	}
	
	//Automatically compensate all users according to flight delays (send the amount of tokens to the user)
    public static String flightResult(String flightNo, long delay ) {
	   String  fromAddr = Cypnet.getAddress("");
	   if(  fromAddr != Cypnet.getAddress("owner") )
		   return "Permission denied, must be the contract owner";

       String s = Cypnet.getState("@FLGI"); //
	   if( s.indexOf("@"+flightNo) < 10 )
		   return "not found flightNo:"+flightNo;
	   
	   if( delay >=2.5*60 && delay <3*60 ){ // 2.5 - 3 hour
		    payTokenToAll(fromAddr, flightNo, 100 );
	   }else if( delay >=3*60 && delay <4*60 ){  // 3 - 4hour
 		    payTokenToAll(fromAddr, flightNo, 200 );
	   }else if( delay >4*60 ){  //  >4hour
		    payTokenToAll(fromAddr, flightNo, 400 );
	   }
	   
	   return "ok";
	}

	//Get the strings of current state for debug 
    public static String getStateInfo(String addr ) {
  	    String s = Cypnet.getState("@FLGI"); 
		System.out.println(s);
		return s;
	}

	static long payTokenToAll(String fromAddr, String flightNo, long number){
	   long  totalpay = 0; 
	   Json j = getStateJson("");
		Map<String,Object> m = j.asMap();
		for (String key : m.keySet()) { 
		   if( key.substring(0,2) == "0X" ){
			   Json kj = j.at(key);
			   if( kj != null ){
				   long n = getJsonValueI(kj, flightNo );
				   if( n > 0 ) {
					   Cypnet.transfer(fromAddr, key,  n*number); 
					   totalpay += n*number;
				   }
			   }
		   }
		  System.out.println("Key = " + key); 
		} 
	    return totalpay;
	}
	
	static long getJsonValueI(Json j, String key){
		if(j.equals(defj)) 
			return 0;
		Json x =j.at(key);
		if( x == null )
			return 0;
		return x.asLong();		
	}
	static String getJsonValueS(Json j, String key){
		if(j.equals(defj))
			return "";
		Json x =j.at(key);
		if( x == null )
			return "";
		return x.asString();	
	}
	//Covert state string into Json
   static Json getStateJson(String s){
	   if( s == "" )
	       s = Cypnet.getState("@FLGI"); //
	   Json j = Json.object();
	   try{
			j = Json.read(s);
	   }catch(Exception e){
		  
	   }
	   return j;
   }

}	
