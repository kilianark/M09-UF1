import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Polialfabetic {
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

    public static Character[] fromCharToCharacter(char[] array) {
        Character[] ans = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            ans[i] = array[i];
        }
        return ans;
    }
}