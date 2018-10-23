import javax.cypher.Cypnet;
import mjson.Json;

public class Logistics {
	static Json defj = Json.object();
    public static void main(String[] args) {
		Cypnet.setTokenInfo("LGS", "Logistics Coin", 1000000000, "" );
	}	
	
	static long getJsonValue(Json j, String key){
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
	
   static Json getStateJson(){
	   String s = Cypnet.getState("@AllGoods"); //
	   Json j = Json.object();
	   try{
			j = Json.read(s);
	   }catch(Exception e){
		  //异常处理
	   }
	   return j;
   }
	
    public static String in(String name, long number ) {
	   String fromAddr = Cypnet.getAddress("");
	   Json j = getStateJson();
	   Json nj = j.at(name);
	   if( nj == null ){
	      j.set(name, Json.object().set(fromAddr, Json.object().set("total", number) ) );
	      Cypnet.setState("@AllGoods", j.toString()); 
		  return "ok1";
	   }
	   Json fj = nj.at(fromAddr);
	   if( fj == null ){
		  nj.set(fromAddr, Json.object().set("total", number) );
	      Cypnet.setState("@AllGoods", j.toString() ); 
		  return "ok2";
	   }
	   fj.set("total", number + getJsonValue(fj, "total") );
	   Cypnet.setState("@AllGoods", j.toString() ); 

       return "ok";
	}	

	public static String setTokenPrice(String name, long tokenPrice ) {
	   if( Cypnet.getAddress("") != Cypnet.getAddress("owner") )
		   return "Permission denied, must be the contract owner";
	   
	   Json j = getStateJson();
	   Json nj = j.at(name);
	   if( nj == null )
		   return "not found";
	   nj.set("tokenPrice", tokenPrice );
	   return "ok";
	}

	public static String pledge(String name, long number, String  addr ) {
	   String fromAddr = Cypnet.getAddress("");
	   String s = Cypnet.getState("@AllGoods"); //
	   Json j = getStateJson();
	   Json nj = j.at(name);
	   if( nj == null )
		   return "not found";
	   Json fj = nj.at(fromAddr);
	   if( fj == null )
		   return "not found";

	   long n = getJsonValue(fj, "locked"); 
	   long total = getJsonValue(fj, "total"); 
	   if( n + number < total ){
		    fj.set("locked", n + number );
			fj.set("lockaddr", addr );
			s = j.toString();
			Cypnet.setState("@AllGoods", s);
			return "ok";
		}

	   return "Insufficient balance";		
		//System.out.println(s);
	}
	
	public static String confirmPledge(String name, String addr, long number ) {
	   String fromAddr = Cypnet.getAddress("");
	   String s = Cypnet.getState("@AllGoods"); //
	   Json j = getStateJson();
	   Json nj = j.at(name);
	   if( nj == null )
		   return "not found";
	   Json fj = nj.at(addr);
	   if( fj == null )
		   return "not found";
	   s = getJsonValueS(fj, "lockaddr"); 
	   if( s != fromAddr ){
		   return  "Permission denied";
	   }
	   long n = getJsonValue(fj, "locked"); 
	   if( n < number )
		  number = n;
	   if( number <= 0 )
			return "invalid number";
		
	   long f = getJsonValue(fj,"tokenPrice");
	   if( f == 0 )
		   f = 1;
	   n = number*f;
       Cypnet.transfer(fromAddr, addr, n ); 
	   return "ok";
	}

	public static String releasePledge(String name, long number ) {
	   String fromAddr = Cypnet.getAddress("");
	   Json j = getStateJson();
	   Json nj = j.at(name);
	   if( nj == null )
		   return "not found";
	   Json fj = nj.at(fromAddr);
	   if( fj == null )
		   return "not found";
	   
	   long n = getJsonValue(fj, "locked");
	   if( n < number )
		  number = n;
	   if( number <= 0 )
			return "invalid number";

	   String  lockAddr = getJsonValueS(fj, "lockaddr");
	   if( lockAddr == "" )
			return "invalid lock address";
		   
	   fj.set("locked", n - number );
	   long f = getJsonValue(fj,"tokenPrice");
	   if( f == 0 )
		   f = 1;
       Cypnet.transfer(fromAddr, lockAddr, number*f );

	   Cypnet.setState("@AllGoods", j.toString() );
	   return "ok";
	}
	
	public static String out(String name, long number ) {
	   String fromAddr = Cypnet.getAddress("");
	   Json j = getStateJson();
	   Json nj = j.at(name);
	   if( nj == null )
		   return "not found";
	   Json fj = nj.at(fromAddr);
	   if( fj == null )
		   return "not found";
	   
	   long total = getJsonValue(fj, "total"); 
	   if( total < number )
		  return "Insufficient balance";   
	   long n = getJsonValue(fj, "locked");
	   if( total - n < number )
		   return "locked";

	   fj.set("total", total - number );
	   Cypnet.setState("@AllGoods", j.toString() );

	   return "ok";
	}

	//Get the number of the seller's goods 
    public static String getGoodsInfo(String name ) {
  	    String s = Cypnet.getState("@AllGoods"); 
		System.out.println(s);
		return s;
	}
}	
