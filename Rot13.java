public class Rot13 {
    public static final char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static void main(String args[]) {
        System.out.println(xifraRot13("PepE"));
        System.out.println(desxifraRot13("CrcR"));
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
            int index = buscaIndex(str.charAt(i)) + 13;
            if (index > 25) index -= 26;
            if(Character.isUpperCase(str.charAt(i))) ans += Character.toUpperCase(abc[index]);
            else ans += abc[index];
        }
        return ans;
    }

    public static String desxifraRot13(String str) {
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            int index = buscaIndex(str.charAt(i)) - 13;
            if (index < 0) index += 26;
            if(Character.isUpperCase(str.charAt(i))) ans += Character.toUpperCase(abc[index]);
            else ans += abc[index];
        }
        return ans;
    }
}