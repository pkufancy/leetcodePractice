import java.util.HashSet;


public class codetest {
    public String solution(int N, String S, String T) {
        // write your code in Java SE 8
        int sunk = 0;
        int hit = 0;
        int[][] map = new int[N][N];
        String[] ships = S.split(",");



        for(int i = 0; i<ships.length; i++){
            String[] pos = ships[i].split(" ");
            if(Integer.parseInt(pos[0].substring(0,pos[0].length()-1)) ==Integer.parseInt(pos[1].substring(0,pos[1].length()-1))
                && pos[0].charAt(pos[0].length()-1)+2==pos[1].charAt(pos[1].length()-1)){
                ships[i] += " "+Integer.parseInt(pos[0].substring(0,pos[0].length()-1))+""+(char)(pos[0].charAt(pos[0].length()-1)+1);
            }else if(Integer.parseInt(pos[0].substring(0,pos[0].length()-1)) +2==Integer.parseInt(pos[1].substring(0,pos[1].length()-1))
                    && pos[0].charAt(pos[0].length()-1)==pos[1].charAt(pos[1].length()-1)){
                ships[i] += " "+(Integer.parseInt(pos[0].substring(0,pos[0].length()-1)) +1)+""+(char)(pos[0].charAt(pos[0].length()-1));
            }else if(Integer.parseInt(pos[0].substring(0,pos[0].length()-1)) +1==Integer.parseInt(pos[1].substring(0,pos[1].length()-1))
                    && pos[0].charAt(pos[0].length()-1)+1==pos[1].charAt(pos[1].length()-1)){
                ships[i] += " "+(Integer.parseInt(pos[0].substring(0,pos[0].length()-1)) +1)+""+(char)(pos[0].charAt(pos[0].length()-1))
                        + " "+Integer.parseInt(pos[0].substring(0,pos[0].length()-1))+""+(char)(pos[0].charAt(pos[0].length()-1)+1);
            }else if(pos[0].equals(pos[1])){
                ships[i] = pos[0];
            }
        }
        T = " "+T;
        for(int i = 0; i <ships.length; i++){
            System.out.println(ships[i]);
            String[] pos = ships[i].split(" ");
            int count = 0;
            //System.out.println(pos.length);
            for(int j = 0; j<pos.length;j++){
                if(T.contains(" "+pos[j])){
                    count++;
                    System.out.print(pos[j]+" ");
                }
            }
            if(count==pos.length){
                sunk++;
            }else if(count>0){
                hit++;
            }
            System.out.println(count);
        }
        return Integer.toString(sunk)+","+Integer.toString(hit);
    }

}
