
import java.io.Serializable;

public class User implements Serializable{
    private String  name;
    private String  password;
    private int     attempts;
    
    User(String name,String password){
        this.name = name;
        this.password = password;
    }
    
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    public int getAttempts(){
        return attempts;
    }
    
    public void setAttempts(int attempts){
        this.attempts = attempts;
    }
    
}
