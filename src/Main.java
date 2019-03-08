import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
public class Main {
    public static void main(String[] args){
        feedzai fz = new feedzai();
        String a = "car cheating dale deal lead listen silent teaching";
        String[] result = fz.friendlyWords(a.split(" "));
        for(int i = 0; i<result.length;i++) {
            System.out.println(result[i]+i);
        }
    }


}
