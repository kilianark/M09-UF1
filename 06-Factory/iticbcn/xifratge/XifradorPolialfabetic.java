package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    public static final char[] abc = {'a','à','á','ä','ã','â','b','c', 'ç','d','e','è','é','ë','ê','f','g','h','i','ì','í','ï','î','j','k','l','m','n','ñ','o','ò','ó','ö','õ','ô','p','q','r','s','t','u','ù','ú','ü','û','v','w','x','y','z'};
    public char[] permutat;
    public Random random; 
    public int clauSecreta = 223333045;
   
    public void initRandom(long clauSecreta) {  
        random = new Random(clauSecreta);  
    }

    public void permutaAlfabet() {
        Character[] alfabet = fromCharToCharacter(abc);
        List<Character> list = Arrays.asList(alfabet);
        Collections.shuffle(list, random);  

        permutat = new char[list.size()];
        for (int i = 0; i < permutat.length; i++) {
            permutat[i] = list.get(i);
        }
    }

    @Override
    public TextXifrat xifra(String cadena, String clau) throws ClauNoSuportada {
        try {
            long clauLong = Long.parseLong(clau);
            initRandom(clauLong);

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
            return new TextXifrat(ans.toString().getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long.");
        }
    }

    @Override
    public String desxifra(TextXifrat msg, String clau) throws ClauNoSuportada {

        try {

            long clauLong = Long.parseLong(clau);
            initRandom(clauLong);

            String msgString = msg.toString();
            StringBuilder ans = new StringBuilder();
            List<Character> list;

            for (int i = 0; i < msgString.length(); i++) {
                permutaAlfabet();  
                char currentChar = msgString.charAt(i);
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
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long.");
        }
    }

    public static Character[] fromCharToCharacter(char[] array) {
        Character[] ans = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            ans[i] = array[i];
        }
        return ans;
    }
}