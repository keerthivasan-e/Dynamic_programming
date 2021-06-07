/*Given a String and its pattern check whether the string matches with the pattern using DP
Input1:
Pattern :
a*ab?
No of matchers:
3
abca
acabb
aab
Output1:
false
true
false


Input2:
Pattern :
x?y*z
No of matchers:
1
xaylmz
Output2:
true
*/

import java.util.*;
public class Main{
    
    static boolean patternMatch(String p,String s){
        
        char[] pattern = p.toCharArray();
        char[] str = s.toCharArray();
        
        int write=0;
        boolean isFirst = true;
        
        for(int i=0;i<pattern.length;i++)
        {
            if(pattern[i] == '*')
            {
                if(isFirst)
                {
                    pattern[write++] = pattern[i];
                    isFirst = false;
                }
            }
            else
            {
                pattern[write++] = pattern[i];
                isFirst = true;
            }
        }
        
        boolean T[][] = new boolean[str.length+1][write+1];
        if(write > 0 && pattern[0] == '*')
        {
            T[0][1] = true;
        }
        T[0][0] = true;
        
        for(int i=1;i<T.length;i++)
        {
            for(int j=1;j<T[0].length;j++)
            {
                if(pattern[j-1] == '?'  || str[i-1] == pattern[j-1])
                {
                    T[i][j] = T[i-1][j-1];
                }
                else if(pattern[j-1] == '*')
                {
                    T[i][j] = T[i-1][j] || T[i][j-1];
                }
            }
        }
        return T[str.length][write];
    
        
    }
    
    
    public static void main (String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Pattern :");
        String s = scanner.next();
        System.out.println("No of matchers:");
        int number = scanner.nextInt();
        
        String[] word = new String[number];
        
        for(int i=0;i<number;i++)
            word[i] = scanner.next();
        
        for(int i=0;i<number;i++)
        {
        System.out.println(patternMatch(s,word[i]));
        }
    }
}
