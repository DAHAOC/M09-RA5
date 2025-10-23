package iticbcn.xifratge;


public class TextXifrat {
    private byte[] contingut;

    public TextXifrat(byte[] contingut) {
        this.contingut = contingut;
    }

    public byte[] getBytes() {
        return contingut;
    }

    @Override
    public String toString() {
        return new String(contingut);
    }
}