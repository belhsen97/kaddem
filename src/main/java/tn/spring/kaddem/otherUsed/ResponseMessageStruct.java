package tn.spring.kaddem.otherUsed;

import java.io.Serializable;

public class ResponseMessageStruct implements Serializable {
    public String type ;
    public String status ;
    public String Message;

    public ResponseMessageStruct(String type,String status,String Message  )
    {this.type=type;  this.status=status; this.Message=Message; }

    public ResponseMessageStruct(){}
}
