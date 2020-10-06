import it.mrbackslash.jmcrc.Jmcrc;

public class LibTester {
    private static boolean result;
    public static void main (String[] args){
        Jmcrc client = new Jmcrc();
        try{
            result = client.init("127.0.0.1", 25575, "password");
        }catch(Exception x) {
            System.out.println("Can't connect to the server");
        }
        System.out.println(result);
    }
}
