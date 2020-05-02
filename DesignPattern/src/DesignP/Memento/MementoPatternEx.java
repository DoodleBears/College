package DesignP.Memento;
/*
实作 Undo 功能
用 Stack 存储历史记录
 */
public class MementoPatternEx {
    public static void main(String[] args){
        // init the stack with a memento
        System.out.println("-----Undo System Initialize Start-----");
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        caretaker.SaveMemento(originator.OriginatorMemento());
        System.out.println("-----Undo System Initialize Over-----\n");

        originator.setState(1);
        caretaker.SaveMemento(originator.OriginatorMemento());
        originator.setState(2);
        originator.revert(caretaker.RetrieveMemento());
        //history is empty (there is always at least one memento at the bottom of the stack
        originator.revert(caretaker.RetrieveMemento());

        for (int i = 0 ; i < 4 ; i++){
            originator.setState(2 + i);
            caretaker.SaveMemento(originator.OriginatorMemento());
        }

        originator.revert(caretaker.RetrieveMemento());
        originator.revert(caretaker.RetrieveMemento());
        originator.revert(caretaker.RetrieveMemento());
        originator.revert(caretaker.RetrieveMemento());
    }

}
