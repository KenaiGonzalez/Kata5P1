package kata5P1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailListReader {
     public static List<String> read(String fileName) throws FileNotFoundException, IOException{
        List<String> list = new ArrayList<>(); 
        FileReader fr = new FileReader (new File (fileName));
        BufferedReader br = new BufferedReader(fr);
        while(true){
            String read = br.readLine();
            if(read==null)break;
            list.add(read);
        }
        return list;
    }

}
