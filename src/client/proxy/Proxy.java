package client.proxy;

import client.comm.UDPClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;


public class Proxy implements ProxyInterface
{
   RemoteRef remoteRef;
   UDPClient udpClient;

    public Proxy()
    {
        remoteRef = new RemoteRef();
        udpClient = new UDPClient();
    }
    
    /*
    * Executes the  remote method "remoteMethod". The method blocks until
    * it receives the reply of the message. 
    */
    public JsonObject synchExecution(String methodName, String[] param)
    {
        JsonObject jsonRequest = new JsonObject();
        JsonObject jsonParam = new JsonObject();
        
        jsonRequest.addProperty("remoteMethod", methodName);

        Ref ref = remoteRef.getRemoteMethod(methodName);
        List<String> params = ref.getParams();
        for(int i = 0; i < params.size(); i++)
            jsonParam.addProperty(params.get(i), param[i]);
        jsonRequest.add("param", jsonParam);

        jsonRequest.addProperty("objectName", ref.getObjectName());
        jsonRequest.addProperty("semantic", ref.getSemantic());

        int id = jsonRequest.toString().hashCode();
//        System.out.println("Id = " + id);
        jsonRequest.addProperty("id", id);
//        System.out.println("JSON Request = " + jsonRequest.toString());

//        System.out.println("Proxy Request Sent = " + jsonRequest.toString());
        String strRet = udpClient.send(jsonRequest.toString(), ref.getSemantic());

        JsonParser parser = new JsonParser();
        return parser.parse(strRet.trim()).getAsJsonObject();
    }

    /*
    * Executes the  remote method remoteMethod and returns without waiting
    * for the reply. It does similar to synchExecution but does not 
    * return any value
    * 
    */
    public void asynchExecution(String remoteMethod, String[] param)
    {
        return;
    }
}


