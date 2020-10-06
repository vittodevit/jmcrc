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
        System.out.println("Function result is " + result);
        System.out.println("isConnected = " + client.isConnected());
        System.out.println("address = " + client.getAddress());
        System.out.println("port = " + client.getPort());
        try{
            String response = client.send("time set 0");
            System.out.println("Server response: ");
        }catch(Exception x){
            System.out.println("Failed to send payload");
        }
        try{
            client.disconnect();
        }catch(Exception x){
            System.out.println("Socket err");
        }

    }
}
