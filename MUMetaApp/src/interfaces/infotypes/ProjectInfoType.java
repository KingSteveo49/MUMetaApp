/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.infotypes;
import interfaces.*;

/**
 *
 * @author bmbolen2017
 */
public class ProjectInfoType implements InfoType{
    
    private String description;
    private String kind;
    private Object data;
    private String type;
    
    public ProjectInfoType(String d, String k, Object o, String t){
        description = d;
        kind = k;
        data = o;
        type = t;
    }
    
    //Description of Info
    @Override
    public String getDescription(){
        return "This a project info type or something";
    }
    
    //The Kind of Info Being played with
    @Override
    public String getKind(){
        return "project";
    }
    
    //[path+content,path,path+content,path]
    @Override
    public String getData(){
        return "H:/test";
    }
   
    //[update,delete,set,get]
    @Override
    public String getType(){
        return "set";
    }
}
