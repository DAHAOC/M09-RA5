package iticbcn.xifratge;

import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    private char[] permutacio;

    public XifradorMonoalfabetic() {
        // Inicialitza permutació aleatòria per exemple
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÀÁÂÃÄÅàáâãäåÈÉÊËèéêëÌÍÎÏìíîïÒÓÔÕÖòóôõöÙÚÛÜùúûüÇçÑñ ";
        permutacio = alfabeto.toCharArray();
        Random rnd = new Random();
        for (int i = permutacio.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            char tmp = permutacio[i];
            permutacio[i] = permutacio[j];
            permutacio[j] = tmp;
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");

        char[] res = new char[msg.length()];
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            int idx = c % permutacio.length; 
            res[i] = permutacio[idx];
        }
        return new TextXifrat(new String(res).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");

        byte[] data = xifrat.getBytes();
        char[] res = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            int idx = data[i] % permutacio.length;
            res[i] = (char) idx; 
        }
        return new String(res);
    }
}
