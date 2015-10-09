package javarestserver;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;

/**
 *
 * @author Gregory
 */
public class NameServer {
    private static ArrayList<String> nameList = new ArrayList<String>();
    
    public static String getNames() {
        String output = "{";
        for (int i = 0; i < nameList.size(); i++) {
            output += "\"name\":\"";
            output += nameList.get(i);
            output += "\",";
        }
        output += "}";
        return output;
    }
    
    public static String getName(int index) {
        if (checkIndex(index)) {
            return nameList.get(index);
        }
        return "";
    }
    
    public static int saveName(InputStream stream) {
        String name = readName(stream);
        nameList.add(name);
        return nameList.size() - 1;
    }
    
    public static int updateName(InputStream stream, int index) {
        if (checkIndex(index)) {
            String name = readName(stream);
            nameList.set(index, name);
            return index;
        }
        return -1;
    }
    
    public static int deleteName(int index) {
        if (checkIndex(index)) {
            nameList.remove(index);
            return index;
        }
        return -1;
    }
    
    private static boolean checkIndex(int index){
        if (index < nameList.size() && index >= 0) {
            return true;
        }
        return false;
    }
    
    private static String readName(InputStream stream) {
        return new Scanner(stream,"UTF-8").useDelimiter("\\A").next();
    }
}
