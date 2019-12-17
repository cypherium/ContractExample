import javax.cypher.Cypnet;
import mjson.Json;
 
public class _cypher_contract {
	
	static Json defj = Json.object();
	
    public static void main(String[] args) {
        long totalSupply = 10000;
	    System.out.println("main");
		//Set token information 
		Cypnet.setTokenInfo("HR", "HR Coin", totalSupply, "" );		
       // Cypnet.changeBalance("caller", totalSupply);
		
	}	
	
	public static String setActor(String addr, String actType, String name ) {	
  	  // String fromAddr = Cypnet.getAddress("");
	  // if( Cypnet.getAddress("") != Cypnet.getAddress("owner") )
	   //   return "Permission denied, must be the contract owner";
	   addr = addr.substring(36).toUpperCase();
	   System.out.println("addr:"+addr);
	   
	   String sKey = "";
	   if( actType == "company" ) {
		   sKey =  "@HR_company";
	   }else if( actType == "teacher" ) {
		  sKey = "@HR_teacher";
	   }else if( actType == "prizer" ) {
		  sKey = "@HR_prizer";
	   }else {
		   return "";
	   }
	  Json j = getStateJson(sKey);  
	  j.set(addr, name);
	  Cypnet.setState(sKey, j.toString() ); 
	   return "ok";
	}

    public static String register(String fromAddr, String name, String sex, String age, String contact) { //注册基本信息
	   //String fromAddr = Cypnet.getAddress("");
	   fromAddr = fromAddr.substring(36).toUpperCase();
	   String skey = fromAddr + "@basic";
	   Cypnet.setState(skey, name+"@"+sex+"@"+age );
       skey = fromAddr+ "@contact";	   
	   Cypnet.setState(skey, contact ); 
       return "ok";
	}

    public static String education(String id, String fromDate,String endDate, String major ) { //教育注册
	  String fromAddr = Cypnet.getAddress("");
	  // String s = Cypnet.getState("@HR_teacher");
	  // if( s.indexOf(fromAddr) < 0 ){
	//	   return "you are not teacher";
	 //  }
	   fromAddr = fromAddr.substring(36).toUpperCase();
	   id = id.substring(36).toUpperCase();
	   String skey = id+ "@basic";
	   String s = Cypnet.getState(skey);
	   if( s == "" ){
		   return "not found the student's id";
	   }
	   skey = id+"@education";
	   Json j = getStateJson(skey);
	   j.set(fromAddr, fromDate+"@"+endDate+"@"+ major );	   
	   Cypnet.setState(skey, j.toString() ); 
	   
       return "ok";
	}
 
    public static String prize(String id, String name, String sdate ) { 
	   String fromAddr = Cypnet.getAddress("");
	   //String s = Cypnet.getState("@HR_prizer");
	   //if( s.indexOf(fromAddr) < 0 ){
		//   return "you are not prizer";
	  // }
	   fromAddr = fromAddr.substring(36).toUpperCase();
	   id = id.substring(36).toUpperCase();
	   String skey = id+ "@basic";
	   String  s = Cypnet.getState(skey);
	   if( s == "" ){
		   return "not found the id";
	   }
	   skey = id+"@prize";
	   Json j = getStateJson(skey);
	   j.set(fromAddr, name +"@"+sdate );
	   Cypnet.setState(skey, j.toString() ); 
	   
       return "ok";
	}
	
    public static String entry(String id, String date, String position ) { 
	   String fromAddr = Cypnet.getAddress("");
	  // String s = Cypnet.getState("@HR_company");
	  // if( s.indexOf(fromAddr) < 0 ){
	//	   return "you are not employer";
	 //  }
	   fromAddr = fromAddr.substring(36).toUpperCase();
	   id = id.substring(36).toUpperCase();
	   String skey = id+ "@basic";
	   String  s = Cypnet.getState(skey);
	   if( s == "" ){
		   return "not found the id";
	   }
	   skey = id+"@entry";
	   Json j = getStateJson(skey);
	   j.set(fromAddr, date+"@"+position );
	   Cypnet.setState(skey, j.toString() ); 
   
       return "ok";
	}

    public static String leave(String id, String date, String desc ) { 
	  String fromAddr = Cypnet.getAddress("");
	//   String s = Cypnet.getState("@HR_company");
	 //  if( s.indexOf(fromAddr) < 0 ){
	//	   return "you are not employer";
	 //  }
	   fromAddr = fromAddr.substring(36).toUpperCase();
	   id = id.substring(36).toUpperCase();
	   String skey = id+"@leave";
	   Json j = getStateJson(skey);
	   j.set(fromAddr, date+"@"+desc );
	   Cypnet.setState(skey, j.toString() ); 
       return "ok";
	}

   public static String recommend(String id, String company ) {
	  String fromAddr = Cypnet.getAddress("");
	   fromAddr = fromAddr.substring(36).toUpperCase();
	   id = id.substring(36).toUpperCase();
	   String skey = id+ "@basic";
	   String s = Cypnet.getState(skey);
	   if( s == "" ){
		   return "not found the id";
	   }
	   skey = id + "@recommend";
	   s = Cypnet.getState(skey);
	   if( s == "" || s.indexOf(company) < 0 ){
		   s += "@"+company;
		   Cypnet.setState(skey, s );
	   }
       return "ok";
	}
    
   public static String resume(String id, String infotype) { 
	   id = id.substring(36).toUpperCase();
	   return Cypnet.getState(id+ "@"+infotype);
	}
	
    static Json getStateJson(String name){
	   String s = Cypnet.getState(name); 
	   Json j = Json.object();
	   if( s != null && s != "" )
	   {
		   try{
				j = Json.read(s);
		   }catch(Exception e){
		   }
	   }
	   return j;
    }	

	static String getJsonValueS(Json j, String key){
		if(j.equals(defj))
			return "";
		Json x =j.at(key);
		if( x == null )
			return "";
		return x.asString();	
	}
    public static String getValue(String skey) {
        String s = Cypnet.getState(skey);
        return s;
    }

}	
