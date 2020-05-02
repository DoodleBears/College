package DesignP.Interpreter;

public class Context {
    private String sentence;
    private String fromLang;
    private String toLang;

    public Context(String fromLang ,String toLang ,String sentence){
        this.sentence = sentence;
        this.fromLang = fromLang;
        this.toLang = toLang;
    }
    public Context(){

    }

    public String getSentence() {
        return sentence;
    }

    public String getFromLang(){
        return fromLang;
    }

    public String getToLang(){
        return toLang;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setFromLang(String Lang){
        this.fromLang = Lang;
    }

    public void setToLang(String Lang){
        this.toLang = Lang;
    }

}
