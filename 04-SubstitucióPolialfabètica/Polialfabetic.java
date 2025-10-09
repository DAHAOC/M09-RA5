import java.util.Random;

public class Polialfabetic {

    private static Random random = null;
    private static final String alfabetOriginal = "abcdefghijklmnopqrstuvwxyzàèòéíúüïçñ";
    private static String alfabetPermutat = "";

    public static void main(String[] args) {
        final long clauSecreta = 444333L;
        String msgs[] = { "Test 01 àrbritre, coixi, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila" };

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void initRandom(long clau) {
        if (random == null) {
            random = new Random();
        }
        random.setSeed(clau);
    }

    public static String xifraPoliAlfa(String msg) {
        msg = msg.toLowerCase();
        String msgXifrat = "";  

        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
            int index = alfabetOriginal.indexOf(lletra);
            if (index != -1) {
                permutaAlfabet();

                char lletraXifrat = alfabetPermutat.charAt(index);
                msgXifrat += lletraXifrat;
            } else {
                msgXifrat += lletra;
            }
        }
        return msgXifrat;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        String desxifrat = "";
        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletra = msgXifrat.charAt(i);

            
            int indexPossible = alfabetOriginal.indexOf(lletra);

            if (indexPossible != -1) {
                
                permutaAlfabet();

                
                int index = alfabetPermutat.indexOf(lletra);
                char originalLletra = alfabetOriginal.charAt(index);
                desxifrat += originalLletra;
            } else {
               
                desxifrat += lletra;
            }
        }
        return desxifrat;
    }

    public static void permutaAlfabet() {
        char[] alfabetoArray = alfabetOriginal.toCharArray();
        int n = alfabetoArray.length;

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char temp = alfabetoArray[i];
            alfabetoArray[i] = alfabetoArray[j];
            alfabetoArray[j] = temp;
        }
        alfabetPermutat = new String(alfabetoArray);
    }

}