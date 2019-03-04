import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
public class bangkok {

    static String findNumber(List<Integer> arr, int k) {
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i)==k){
                return "YES";
            }
        }
        return "NO";
    }

    static List<Integer> oddNumbers(int l, int r) {
        if(l%2==0) l++;
        List<Integer> result = new LinkedList<Integer>();
        for(int i=l; i<=r;i+=2){
            result.add(i);
        }
        return result;
    }

    public static String reverseWords(String inputWords) {
        String result = "";
        String[] temp = inputWords.split(" ");
        for(int i = temp.length-1; i>=0; i--){
            result += " "+ temp[i];
        }
        return result.substring(1);
    }

    public static List<Integer> getFirstTwoItemsWithoutPair(List<Integer> list){
        int count=0;
        int[] listArray = new int[list.size()];
        List<Integer> result = new LinkedList<Integer>();
        for(int i = 0; i<list.size();i++){
            listArray[i]=list.get(i);
        }
        while(result.size()<2){
            for(int i = 0; i < listArray.length; i++){
                if(!duplicate(listArray[i], listArray)){
                    result.add(listArray[i]);
                    count++;
                    break;
                }
            }
        }
        return result;
    }
    public static boolean duplicate(int target, int[] list){
        for(int i = 0; i<list.length; i++){
            if(list[i]==target) return true;

        }
        return false;
    }


    public static List<String> sortDates(List<String> dates) {
        String temp;
        int size = dates.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (dateEarly(dates.get(i), dates.get(j))) {

                    temp = dates.get(i);
                    dates.set(i, dates.get(j));
                    dates.set(j, temp);
                }
            }
        }
        return dates;
    }

    public static boolean dateEarly(String date1, String date2){
        String[] date1Set = date1.split(" ");
        String[] date2Set = date2.split(" ");
        if(Integer.parseInt(date1Set[2]) < Integer.parseInt(date2Set[2])) return true;
        if(Integer.parseInt(date1Set[2]) > Integer.parseInt(date2Set[2])) return false;
        if(Integer.parseInt(date1Set[2]) == Integer.parseInt(date2Set[2])){
            //comparing month
            if(monthToNum(date1Set[1]) < monthToNum(date2Set[1])) return true;
            if(monthToNum(date1Set[1]) > monthToNum(date2Set[1])) return false;
            if(monthToNum(date1Set[1]) == monthToNum(date2Set[1])){
                //compare date
                if(Integer.parseInt(date1Set[0]) < Integer.parseInt(date2Set[0])) return true;
                else return false;
            }

        }
        return true;
    }

    public static int monthToNum(String month){
        switch (month){
            case "Jan": return 1;
            case "Feb": return 2;
            case "Mar": return 3;
            case "Apr": return 4;
            case "May": return 5;
            case "Jun": return 6;
            case "Jul": return 7;
            case "Aug": return 8;
            case "Sep": return 9;
            case "Oct": return 10;
            case "Nov": return 11;
            case "Dec": return 12;
        }
        return 0;
    }



}
