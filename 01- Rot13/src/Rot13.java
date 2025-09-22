
public class Rot13 {
    public static void main(String[] args) {
        System.out.println("Text a codificar");
        String text = "Hola, texto para probar. abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùüç";
        String codificat = xifraRot13(text);
        System.out.println(codificat);
        System.out.println(desxifraRot13(codificat));
    }
    public static String xifraRot13(String input) {
        char[] abcminus = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùüç".toCharArray();
        char[] abcmayus = "ABCDEFGHIJKLMNÑIOQRSTUVWXYZÁÀÉÈÍÌÏÓÒÚÙÜÇ".toCharArray();
        String retornar = "";
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(Character.isWhitespace(c) || !Character.isLetter(c)) {
                retornar += c;
                continue;
            }

            for(int e = 0; e < abcminus.length; e++) {
                char c2 = abcminus[e];
                if (Character.toLowerCase(c) == c2) {
                    if(Character.isUpperCase(c)) {
                        retornar += abcmayus[(e + 13) % abcmayus.length];
                    } else {
                        retornar += abcminus[(e + 13) % abcminus.length];
                    }
                } else {
                    continue;
                }
            } 
          
        }
        return retornar;
    }
    
    public static String desxifraRot13(String input) {
        char[] abcminus = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùüç".toCharArray();
        char[] abcmayus = "ABCDEFGHIJKLMNÑIOQRSTUVWXYZÁÀÉÈÍÌÏÓÒÚÙÜÇ".toCharArray();
        String retornar ="";

        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(Character.isWhitespace(c) || !Character.isLetter(c)) {
                retornar += c;
                continue;
            }
            
            for(int e = 0; e < abcminus.length; e++) {
                char c2 = abcminus[e];
                if(Character.toLowerCase(c) == c2) {
                    if(Character.isUpperCase(c)) {
                        retornar += abcmayus[(e-13 + abcminus.length) % abcminus.length];
                    } else {
                        
                        retornar += abcminus[(e-13 + abcminus.length) % abcminus.length];
                    }
                } 
            }
        }
        return retornar;
    }
} 