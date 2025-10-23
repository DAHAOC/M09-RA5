package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    private Random rnd;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        rnd = new Random(seed);
        char[] res = new char[msg.length()];
        for (int i = 0; i < msg.length(); i++) {
            res[i] = (char)(msg.charAt(i) + rnd.nextInt(10));
        }
        return new TextXifrat(new String(res).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }

        rnd = new Random(seed);
        byte[] data = xifrat.getBytes();
        char[] res = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            res[i] = (char)(data[i] - rnd.nextInt(10));
        }
        return new String(res);
    }
}
