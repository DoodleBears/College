package DesignP.Memento;

import java.util.Stack;

public class Caretaker {
    private int length = 100;

    //use stack to store the history
    Stack<Memento> st = new Stack<Memento>();

    Memento memento_array[] = new Memento[length];
    public Caretaker (){

    }
    public void SaveMemento(Memento memento){
        //if history amount reach the upper bound, alert.
        if (st.size() < length) st.push(memento);
        else System.out.println("History Full");

    }

    public Memento RetrieveMemento(){
        if(!st.empty()){
            return st.pop();
        }
        //History stack is empty, State remain the same
        else {
            return null;
        }

    }
}
