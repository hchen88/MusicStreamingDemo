package client.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class RemoteRef implements RemoteRefInterface
{

    Gson gson;

    public RemoteRef()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();

    }

    @Override
    public Ref getRemoteMethod(String methodName) {

        List<Ref> refs = null;
        Type type = new TypeToken<List<Ref>>(){}.getType();
        try {
            FileReader reader = new FileReader("src/client/data/remoteref.json");
            refs = gson.fromJson(reader, type);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Ref ref = null;
        for(Ref r : refs){
            if(r.getMethodName().equals(methodName))
                ref = r;
        }

        return ref;
    }

}
