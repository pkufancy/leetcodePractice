import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class feedzai {
    static boolean sameContents(String[] a, String[] b) {
        //the order is not important, ["a","b","a"] and ["a","a","b"] will be identify as different
        if((a.length==0 ||a.equals(null)) && (b.length==0||b==null)) return true;
        if((a.length==0 || b.length == 0)&& a.length !=b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            //String cannot be compared using !=, it can be compared using equals()
            if (!a[i].equals(b[i]))
                return false;
        }


        return true;
    }


    static String[] friendlyWords(String[] input) {
        HashMap<String, String> map = new HashMap<String, String>();
        for(int i = 0; i<input.length;i++){
            char[] tempChar = input[i].toCharArray();
            Arrays.sort(tempChar);
            String temp = String.valueOf(tempChar);
            if(map.containsKey(temp)){
                //System.out.println(temp);
                String orignial = map.get(temp);
                String newStr = orignial +" "+ input[i];
                map.put(temp,newStr);
            }else{
                map.put(temp,input[i]);

            }
        }
        String resultSet = "";
        for(String key : map.keySet()){
            //System.out.println(map.get(key));
            if(map.get(key).contains(" ")){
                resultSet += ";" + map.get(key);
            }
        }
        //System.out.println(resultSet);
        String[] resltfinal = resultSet.split(";");
        Arrays.sort(resltfinal);
        return resltfinal;
    }

    static boolean isFriendly(String a, String b){
        char[] charArraryA = a.toCharArray();
        char[] charArraryB = b.toCharArray();
        Arrays.sort(charArraryA);
        Arrays.sort(charArraryB);
        if(charArraryA.equals(charArraryB)) return true;
        return false;
    }


}
