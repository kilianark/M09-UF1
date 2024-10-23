package iticbcn.xifratge;

public class ClauNoSuportada extends Exception {
    public ClauNoSuportada () {
        super("Clau no suportada");
    }

    public ClauNoSuportada (String msg) {
        super(msg);
    }
}
