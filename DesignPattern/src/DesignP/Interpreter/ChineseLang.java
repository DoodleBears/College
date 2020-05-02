package DesignP.Interpreter;

public class ChineseLang implements Language {
    public ChineseLang(){}
    @Override
    public void interpret(String s) {
        String sentence = new String();
        if (s.equals("私") ){
            sentence = "From(JP):私\nTo(CN):我";
        }
        else if (s.equals("名前")){
            sentence = "From(JP):名前\nTo(CN):名字";
        }
        else sentence = "词汇/句子不存在";
        System.out.println(sentence);
    }
}
