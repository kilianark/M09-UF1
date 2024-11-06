package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    public static final char[] abc = {'a','à','á','ä','ã','â','b','c','d','e','è','é','ë','ê','f','g','h','i','ì','í','ï','î','j','k','l','m','n','ñ','o','ò','ó','ö','õ','ô','p','q','r','s','t','u','ù','ú','ü','û','v','w','x','y','z'};
    public static final int lletresAbc = 49;


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

    @Override
    public TextXifrat xifra(String str, String clau) throws ClauNoSuportada {
        try {
            int rotation = Integer.parseInt(clau);

            if(rotation < 0 || rotation > abc.length) {
                throw new ClauNoSuportada(String.format("The rotation %d is greater than the max rotation %d", rotation, abc.length));
            }
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
            return new TextXifrat(ans.getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 49");
        }
    }

    @Override
    public String desxifra(TextXifrat msg, String clau) throws ClauNoSuportada {
        try {
            int rotation = Integer.parseInt(clau);

            if(rotation < 0 || rotation > abc.length) {
                throw new ClauNoSuportada(String.format("The rotation %d is greater than the max rotation %d", rotation, abc.length));
            }
            String str = msg.toString();
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
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 49");            
        }
    }

    public void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 0; i < lletresAbc; i++) {
            String ans = "";
            for (int j = 0; j < cadenaXifrada.length(); j++) {
                if(!Character.isLetter(cadenaXifrada.charAt(j))) {
                    ans +=  cadenaXifrada.charAt(j);
                    continue;
                }
                int index = buscaIndex(cadenaXifrada.charAt(j)) + i;
                if (index >= lletresAbc) index -= lletresAbc;
                //System.out.println(index);
                if(Character.isUpperCase(cadenaXifrada.charAt(j))) ans += Character.toUpperCase(abc[index]);
                else ans += abc[index];
            }
            System.out.println(ans);
        }
    }
}
