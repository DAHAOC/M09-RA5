

public class Monoalfabetic {


    public static void main(String[] args) {
        String cadena = "Aixo es un string per prova el xifrat";
        System.out.printf("Cadena per xifrar:\n%s\n",cadena);
        alfabetPermutat = permutaAlfabet(alfabet);

        String xifrat = xifraMonoAlfa(cadena);

        System.out.printf("Aquesta cadena xifrat:\n%s\n", xifrat);
        
        String desxifrat = desxifraMonoAlfa(xifrat);

        System.out.printf("Aquesta cadena esta desxifrat:\n%s", desxifrat);
    
    
    }   
    private static final char[] alfabet = {
        'A', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'Ë', 'F', 'G', 'H', 'I', 'Í', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'Ö', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    private static char[]alfabetPermutat;
    public static char[] permutaAlfabet(char[] alfabetOriginal) {
        char[] returnCharArray= new char[alfabetOriginal.length];
        int e = 0;
        for(int i = alfabetOriginal.length -1 ; i >= 0; i--) {
            char c = alfabetOriginal[i];
            returnCharArray[e] = c;
            e++;

        }
        return returnCharArray;
    }
    
    public static String xifraMonoAlfa(String cadena) {
        String cadenaReturn = "";
        
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(Character.isWhitespace(c)) {
                cadenaReturn += c;
            }
            for(int e = 0; e < alfabet.length; e++) {
                
                if(alfabet[e] == Character.toUpperCase(c)) {
                    cadenaReturn += alfabetPermutat[e];
                    break;
                }
            }
        }
        return cadenaReturn;

    } 

    
    public static String desxifraMonoAlfa(String xifrat) {
        String cadenaReturn = "";
        for(int i = 0; i < xifrat.length(); i++) {
            char c = xifrat.charAt(i);
            if(Character.isWhitespace(c)) {
                cadenaReturn += c;
            }
            for(int e = 0; e < alfabetPermutat.length; e++) {
                
                if(alfabetPermutat[e] == Character.toUpperCase(c)) {
                    cadenaReturn += alfabet[e];
                    break;
                }
            }
        }
        return cadenaReturn;

    }
}
