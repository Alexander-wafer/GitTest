
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileManager{
    private String path;
    private File folder;
    
    
    FileManager(){
        path = getClass().getResource(File.separator).getPath();
        folder = new File(path+File.separator+"Users");
        folder.mkdir();
    }
    
    public void fileSave(User user,String nameFile) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path+File.separator+"Users"+File.separator+nameFile+".bin"));
        oos.writeObject(user);
        oos.close();
    }
    
    public  User fileLoad(String nameFile) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path+File.separator+"Users"+File.separator+nameFile));
        User user = (User) ois.readObject();
        ois.close();
        return user;
    }
    public User[] getAllFiles() throws IOException, FileNotFoundException, ClassNotFoundException{
        String[] list = folder.list();
        User[] users = new User[list.length];
        for (int i = 0; i < list.length; i++) {
            users[i] = fileLoad(list[i]);
        }
        return users;
    }
}
