package DesignP.Interpreter;

public class InterpreterPatternEx {
    public static void main(String[] args) {

        Expression e = new Expression();
        Context c = new Context("JP","EN","名前");

        e.translate(c);

        c.setToLang("CN");
        e.translate(c);
        c.setSentence("私");
        e.translate(c);
        c.setSentence("233");
        e.translate(c);
        c.setToLang("JP");
        e.translate(c);
    }
}
