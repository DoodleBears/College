package DesignP.Memento;

import java.util.Stack;

public class Caretaker {
    private int length = 1 + 3;//1 means init memento
    //use stack to store the history
    Stack<Memento> st = new Stack<Memento>();

    public Caretaker (){

    }
    public void SaveMemento(Memento memento){
        //if history amount reach the upper bound, alert.
        if (st.size() < length) {
            st.push(memento);
            System.out.println("State Saving... state: " + memento.getState());
            System.out.println("History amount: " + (st.size() - 1));
        }
        else System.out.println("---------------\nCAN'T SAVE. Maximum History is 3\n---------------");
    }

    public Memento RetrieveMemento(){
        if(st.size() > 1){
            Memento m = st.pop();
            System.out.println("Reverting... History remain become: " + (st.size() - 1));
            return m;
        }
        //History stack is empty, State remain the same
        else {
            return st.firstElement();
        }
    }
}
