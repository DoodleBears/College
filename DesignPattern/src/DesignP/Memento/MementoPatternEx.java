package DesignP.Memento;

public class MementoPatternEx {
    public static void main(String[] args){
        Originator originator = new Originator(1);
        Caretaker caretaker = new Caretaker();

        caretaker.SaveMemento(originator.OriginatorMemento());

        originator.setState(2);
        for (int i = 0 ; i < 10 ; i++){
            caretaker.SaveMemento(originator.OriginatorMemento());
        }

        originator.setState(3);

        originator.revert(caretaker.RetrieveMemento());
        System.out.println(originator.getState());

        originator.revert(caretaker.RetrieveMemento());
        System.out.println(originator.getState());

        originator.revert(caretaker.RetrieveMemento());
        System.out.println(originator.getState());

    }

}
