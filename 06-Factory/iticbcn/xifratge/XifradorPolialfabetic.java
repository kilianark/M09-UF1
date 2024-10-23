package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    public static final char[] abc = {'a','à','á','ä','ã','â','b','c', 'ç','d','e','è','é','ë','ê','f','g','h','i','ì','í','ï','î','j','k','l','m','n','ñ','o','ò','ó','ö','õ','ô','p','q','r','s','t','u','ù','ú','ü','û','v','w','x','y','z'};
    public static char[] permutat;
    public static Random random; 
    public static int clauSecreta = 223333045;

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta); 
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]); 
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);  
            String msg = desxifraPoliAlfa(msgsXifrats[i]);  
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

   
    public static void initRandom(int clauSecreta) {  
        random = new Random(clauSecreta);  
    }

    public static void permutaAlfabet() {
        Character[] alfabet = fromCharToCharacter(abc);
        List<Character> list = Arrays.asList(alfabet);
        Collections.shuffle(list, random);  

        permutat = new char[list.size()];
        for (int i = 0; i < permutat.length; i++) {
            permutat[i] = list.get(i);
        }
    }

    public static String xifraPoliAlfa(String cadena) {
        StringBuilder ans = new StringBuilder();
        List<Character> list = Arrays.asList(fromCharToCharacter(abc));

        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet();  
            char currentChar = cadena.charAt(i);
            if (Character.isLetter(currentChar)) {
                if (Character.isUpperCase(currentChar)) {
                    char lower = Character.toLowerCase(currentChar);
                    ans.append(Character.toUpperCase(permutat[list.indexOf(lower)]));
                } else {
                    ans.append(permutat[list.indexOf(currentChar)]);
                }
            } else {
                ans.append(currentChar);  
            }
        }
        return ans.toString();
    }


    public static String desxifraPoliAlfa(String cadena) {
        StringBuilder ans = new StringBuilder();
        List<Character> list;

        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet();  
            char currentChar = cadena.charAt(i);
            list = Arrays.asList(fromCharToCharacter(permutat));
            if (Character.isLetter(currentChar)) {
                if (Character.isUpperCase(currentChar)) {
                    char lower = Character.toLowerCase(currentChar);
                    ans.append(Character.toUpperCase(abc[list.indexOf(lower)]));
                } else {
                    ans.append(abc[list.indexOf(currentChar)]);
                }
            } else {
                ans.append(currentChar); 
            }
        }
        return ans.toString();
    }

    public static Character[] fromCharToCharacter(char[] array) {
        Character[] ans = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            ans[i] = array[i];
        }
        return ans;
    }
}