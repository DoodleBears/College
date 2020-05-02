package DesignP.Interpreter;

public class Expression {
    private Language CN = new ChineseLang();
    private Language EN = new EnglishLang();

    public Expression(){

    }
    //判断来源语言，和翻译语言，调用对应方法
    public void translate(Context c){
        switch (c.getToLang()) {
            case "CN":
                CN.interpret(c.getSentence());
                break;
            case "EN":
                EN.interpret(c.getSentence());
                break;
            default:
                System.out.println(c.getFromLang() + " can not translate to " + c.getToLang());
        }
        return;
    }
}
