package DesignP.Interpreter;

public class EnglishLang implements Language {
    public  EnglishLang(){}
    @Override
    public void interpret(String s) {
        String sentence = new String();
        if (s.equals("私") ){
            sentence = "From(JP):私\nTo(EN):me,my,mine";
        }
        else if (s.equals("名前")){
            sentence = "From(JP):名前\nTo(EN):last name";
        }
        else sentence = "词汇/句子不存在";
        System.out.println(sentence);
    }
}
