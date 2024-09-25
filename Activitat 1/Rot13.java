public class Rot13 {
    public static final char[] abc = {'a','à','á','ä','ã','â','b','c','d','e','è','é','ë','ê','f','g','h','i','ì','í','ï','î','j','k','l','m','n','ñ','o','ò','ó','ö','õ','ô','p','q','r','s','t','u','ù','ú','ü','û','v','w','x','y','z'};
    public static final int lletresAbc = abc.length;
    public static void main(String args[]) {
        System.out.println(xifraRot13("Pe pE"));
        System.out.println(desxifraRot13("Cr cR"));
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
    public static String xifraRot13(String str) {
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i))) {
                ans +=  str.charAt(i);
                continue;
            }
            int index = buscaIndex(str.charAt(i)) + 13;
            if (index > lletresAbc-1) index -= lletresAbc;
            if(Character.isUpperCase(str.charAt(i))) ans += Character.toUpperCase(abc[index]);
            else ans += abc[index];
        }
        return ans;
    }

    public static String desxifraRot13(String str) {
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i))) {
                ans +=  str.charAt(i);
                continue;
            }
            int index = buscaIndex(str.charAt(i)) - 13;
            if (index < 0) index += lletresAbc;
            if(Character.isUpperCase(str.charAt(i))) ans += Character.toUpperCase(abc[index]);
            else ans += abc[index];
        }
        return ans;
    }
}