package tn.spring.kaddem.struct;

import tn.spring.kaddem.entities.Etudiant;

import java.util.ArrayList;
import java.util.List;

public class crudStruct {
    public String type = "N/A" ;
    public String status = "N/A" ;
    public String Message = "N/A";
    public ArrayList<Boolean> StatusIds = new ArrayList<Boolean>();
    /*List<Object> list ;
    public  <T> ArrayList<T> listOf(Class<T> clazz)
    {
        return new ArrayList<T>();
    }*/
    public ArrayList<Integer> Ids= new ArrayList<Integer>();
}
