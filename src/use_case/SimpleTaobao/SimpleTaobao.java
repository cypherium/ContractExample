import javax.cypher.Cypnet;
import java.lang.Integer;

public class SimpleTaobao {
    public static void main(String[] args) {
		Cypnet.setTokenInfo("SimpleTaobao", "Simple Taobao", 0, "" );
    }

    public static String changeGoodsInfo(String name, long number ) {
		name = name.replace('@', '#');
		String s = Cypnet.getCallerState(name, "");
		if( s != "" ){
		   int num = Integer.parseInt(s);
		   Cypnet.setCallerState(name, String.valueOf(num+number), "" );
		   
		} else {
			
		   Cypnet.setCallerState(name, String.valueOf(number), "" );
		   
		   s = Cypnet.getCallerState("@AllGoods", "");
		   if( s.indexOf(name) < 0 ){
			   s +=  name.replace('|', ',') + "|";
			   Cypnet.setCallerState("@AllGoods", s, "" );
		   }
		}
		
		String fromAddr = Cypnet.getAddress("");
	    s = Cypnet.getState("@AddrList");
	    if( s.indexOf(fromAddr) < 0 ){
		   s += fromAddr + "@";
		   Cypnet.setState("@AddrList", s);
	    }

		return "ok";
	}
	
    public static String getGoodsInfo(String name, String addr ) {
		String s = Cypnet.getCallerState(name, addr);
		System.out.println(s);
		return s;
	}

    public static String findGoods(String name) {
		String res = "";
	    String s = Cypnet.getState("@AddrList");
		System.out.println("findGoods:" + s);
		String[] a = s.split("@");
		int n = a.length;
		for(int i=0; i<n; i++ ){
		   s = Cypnet.getCallerState(name, a[i]);
		   if( s != "" ){
			   res += a[i] + ":" + s + "|";
		   }
		}
		System.out.println(res);
		return res;
	}
	
    public static String transfer(String name, long number, String _to ) {
		String s = Cypnet.getCallerState(name, "");
		System.out.println(s);
		if( s != "" ){
			int numFrom = Integer.parseInt(s);
			if(number > numFrom ){
				return "Insufficient balance";
			}
		    Cypnet.setCallerState(name, String.valueOf(numFrom-number), "" );
			System.out.println("transfer..1="+String.valueOf(numFrom-number));
  		    s = Cypnet.getCallerState(name, _to);
			if( s != "" ){
			   int numTo = Integer.parseInt(s);
		       Cypnet.setCallerState(name, String.valueOf(numTo + number),  _to );
			}else {
		       Cypnet.setCallerState(name, String.valueOf(number),  _to );
			}
		}else {
			return "not found!";
		}
		return "ok";
	}
}	
