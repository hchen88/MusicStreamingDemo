package client.proxy;

import java.util.ArrayList;
import java.util.List;

public class Ref
{
    String methodName;
    String objectName;
    List<String> params;
    String semantic;

    public Ref(String methodName, String objectName, String semantic)
    {
        this.methodName = methodName;
        this.objectName = objectName;
        params = new ArrayList<>();
        this.semantic = semantic;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public List<String> getParams()
    {
        return params;
    }

    public String getObjectName()
    {
        return objectName;
    }

    public String getSemantic()
    {
        return semantic;
    }
}
