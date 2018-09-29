import javax.cypher.Cypnet;

public class HelloWorld {
    public static void main(String[] args) {
        long totalSupply = 10000;
        //我们创建一个 符号为Hello,名称为Hello world,发行总量为10000的代币。
        Cypnet.setTokenInfo("Hello", "Hello world", totalSupply, "");
        //把发行的代币全都给合约创建者
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

    public static String setValue(String skey, String sValue) {
        Cypnet.setState(skey, sValue);
        return "ok";
    }
}