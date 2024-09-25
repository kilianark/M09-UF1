public class RotX {
    public static final char[] abc = {'a','à','á','ä','ã','â','b','c','d','e','è','é','ë','ê','f','g','h','i','ì','í','ï','î','j','k','l','m','n','o','ò','ó','ö','õ','ô','p','q','r','s','t','u','ù','ú','ü','û','v','w','x','y','z'};
    public static final int lletresAbc = 48;

    public static void main(String args[]) {
        System.out.println(xifraRotX("Pe pE", 3));
        System.out.println(desxifraRotX(xifraRotX("Pe pE", 3), 3));
    }

    public static int buscaIndex(char lletra) {
        int index = 0;
        for (int j = 0; j < abc.length; j++) {
            if(Character.toLowerCase(abc[j]) == Character.toLowerCase(lletra)) {
                index = j;
                break;
            } 
        }
        return index;
    }

    public static String xifraRotX(String str, int rotation) {
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i))) {
                ans +=  str.charAt(i);
                continue;
            }
            int index = buscaIndex(str.charAt(i)) + rotation;
            if (index > lletresAbc-1) index -= lletresAbc;
            if(Character.isUpperCase(str.charAt(i))) ans += Character.toUpperCase(abc[index]);
            else ans += abc[index];
        }
        return ans;
    }

    public static String desxifraRotX(String str, int rotation) {
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i))) {
                ans +=  str.charAt(i);
                continue;
            }
            int index = buscaIndex(str.charAt(i)) - rotation;
            if (index < 0) index += lletresAbc;
            if(Character.isUpperCase(str.charAt(i))) ans += Character.toUpperCase(abc[index]);
            else ans += abc[index];
        }
        return ans;
    }
}
