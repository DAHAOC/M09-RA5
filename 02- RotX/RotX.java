
public class RotX {
    public static void main(String[] args) {
        int desplasament = 5;
        System.out.println("text");
        String text = "Hola, Mr. calçot";
        System.out.println(text+"\n");
        String xifrat = xifraRotX(text, 5);
        System.out.println("xifrat");
        System.out.printf("(%d) - %s => %s \n\n", desplasament, text, xifrat);
        
        System.out.println("desxifrat");
        System.out.printf("(%d) - %s => %s \n\n", desplasament, xifrat, desxifraRotX(xifrat, 5));

        System.out.println("Missatge Xifrat: " + xifrat);
        forcaBrutaRotX(xifrat);
    }
    public static String xifraRotX(String cadena, int desplasament) {
        char[] abcminus = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùüç".toCharArray();
        char[] abcmayus = "ABCDEFGHIJKLMNÑIOQRSTUVWXYZÁÀÉÈÍÌÏÓÒÚÙÜÇ".toCharArray();
        String retornar = "";
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(Character.isWhitespace(c) || !Character.isLetter(c)) {
                retornar += c;
                continue;
            }

            for(int e = 0; e < abcminus.length; e++) {
                char c2 = abcminus[e];
                if (Character.toLowerCase(c) == c2) {
                    if(Character.isUpperCase(c)) {
                        retornar += abcmayus[(e + desplasament) % abcmayus.length];
                    } else {
                        retornar += abcminus[(e + desplasament) % abcminus.length];
                    }
                } else {
                    continue;
                }
            } 
        }
        return retornar;
    }
    public static String desxifraRotX(String cadena, int desplasament) {
        char[] abcminus = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùüç".toCharArray();
        char[] abcmayus = "ABCDEFGHIJKLMNÑIOQRSTUVWXYZÁÀÉÈÍÌÏÓÒÚÙÜÇ".toCharArray();
        String retornar ="";

        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(Character.isWhitespace(c) || !Character.isLetter(c)) {
                retornar += c;
                continue;
            }
            
            for(int e = 0; e < abcminus.length; e++) {
                char c2 = abcminus[e];
                if(Character.toLowerCase(c) == c2) {
                    if(Character.isUpperCase(c)) {
                        retornar += abcmayus[(e- desplasament + abcminus.length) % abcminus.length];
                    } else {
                        
                        retornar += abcminus[(e- desplasament + abcminus.length) % abcminus.length];
                    }
                } 
            }
        }
        return retornar;
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        char[] abcminus = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùüç".toCharArray();
        char[] abcmayus = "ABCDEFGHIJKLMNÑIOQRSTUVWXYZÁÀÉÈÍÌÏÓÒÚÙÜÇ".toCharArray();
        
        for(int i = 0; i < abcminus.length; i++) {
            String possible = desxifraRotX(cadenaXifrada, i);
            System.out.println("(" + i + ")  " + possible);
        }
    }
}
