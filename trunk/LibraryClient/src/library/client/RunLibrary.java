package library.client;

import library.common.*;
import java.rmi.Naming;

/**
 * @author Karolina
 */
public class RunLibrary {
  
   public static void main(String[] args){
        ClientSettings ls = new ClientSettings("libraryClient");
        ls.loadSettings();
       
        System.setSecurityManager(new SecurityManager());
        try{
            String rmiString = "//"+ ls.getAddress()+":"
                    + Integer.toString(CommonSettings.getInstance().getPort())+"/"
                    + CommonSettings.getInstance().getObjectName();
            System.out.println(rmiString);
            LibraryReaderInterface library = (LibraryReaderInterface)Naming.lookup
                (rmiString);
            MainForm mf = new MainForm(library);
            mf.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }	
    }
}
