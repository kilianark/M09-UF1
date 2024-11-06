package iticbcn.xifratge;

import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    public static final char[] abc = {'a','à','á','ä','ã','â','b','c', 'ç','d','e','è','é','ë','ê','f','g','h','i','ì','í','ï','î','j','k','l','m','n','ñ','o','ò','ó','ö','õ','ô','p','q','r','s','t','u','ù','ú','ü','û','v','w','x','y','z'};
    public static final char[] permutat = (permutaAlfabet(abc));

    public static char[] permutaAlfabet(char[] abc) {
        Character[] alfabet = fromCharToCharacter(abc);
        List<Character> list = Arrays.asList(alfabet);
        Collections.shuffle(list);

        char[] result = new char[list.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public String xifraMonoAlfa(String cadena) {
        String ans = "";
        List<Character> list = Arrays.asList(fromCharToCharacter(abc));
        for(int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                if(Character.isUpperCase(cadena.charAt(i))) {
                    char lower = Character.toLowerCase(cadena.charAt(i));
                    ans += Character.toUpperCase(permutat[list.indexOf(lower)]);
                }
                else ans += permutat[list.indexOf(cadena.charAt(i))];   
            }
            else ans += cadena.charAt(i);
                
        }
        return ans;
    }

    public String desxifraMonoAlfa(String cadena) {
        String ans = "";
        List<Character> list = Arrays.asList(fromCharToCharacter(permutat));
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                if(Character.isUpperCase(cadena.charAt(i))) {
                    char lower = Character.toLowerCase(cadena.charAt(i));
                    ans += Character.toUpperCase(abc[list.indexOf(lower)]);
                }
                else ans += abc[list.indexOf(cadena.charAt(i))];
            }
            else ans += cadena.charAt(i);
        }
        return ans;
    }

    public static Character[] fromCharToCharacter(char[] array) {
        Character[] ans = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            ans[i] = array[i];
        }
        return ans;
    }
}