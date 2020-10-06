/*
This software is distributed under the Apache License 2.0
Copyright 2020 Vittorio Lo Mele

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

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
            System.out.println("Server response: " + response);
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
