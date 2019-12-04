import javax.cypher.Cypnet;

public class _cypher_contract {
    public static void main(String[] args) {
        long totalSupply = 10000;
        Cypnet.setTokenInfo("Hello", "Hello world", totalSupply, "");
        Cypnet.changeBalance("caller", totalSupply);
        System.out.println("Hello");
    }

    public static String transfer(String _to, long _value) {
        long n = Cypnet.balanceOf("caller");
        if (n < _value) {
            //throw new Exception("Insufficient balance");
            return "Insufficient balance";
        }
        Cypnet.transfer("caller", _to, _value);
        return null;
    }

    public static String getValue(String skey) {
        String s = Cypnet.getState(skey);
        return s;
    }

    public static String setValue(String _to, String skey, String sValue) {
        Cypnet.setState(skey, sValue);
        return "ok";
    }
}

