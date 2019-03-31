package atm;

import java.io.*;

public class Serialize {

    private String file;
    private Object obj;


    public Serialize (String file, Object obj){
        this.file = file;
        this.obj = obj;
    }


    public void  store(){
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }


    public Object retrieve() {
        File f = new File(file);
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object thing = ois.readObject();
                ois.close();
                fis.close();
                return thing;

            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }
        }
        return null;
    }
}
