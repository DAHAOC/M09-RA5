package iticbcn.xifratge;




public class XifradorRotX implements Xifrador {

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int rot;
        try {
            rot = Integer.parseInt(clau);
            if (rot < 0 || rot > 40)
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        char[] chars = msg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + rot);
        }

        return new TextXifrat(new String(chars).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int rot;
        try {
            rot = Integer.parseInt(clau);
            if (rot < 0 || rot > 40)
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        byte[] data = xifrat.getBytes();
        char[] chars = new String(data).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - rot);
        }

        return new String(chars);
    }
}
