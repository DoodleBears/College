package DesignP.Memento;

public class Originator {
    private int state;

    public Originator() {

    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
        System.out.println("Set state to " + state);
    }

    //Generate a Memento by Generator
    public Memento OriginatorMemento(){
        Memento memento = new Memento(state);
        return memento;
    }

    public void revert(Memento previousMemento){
        if (previousMemento.getState() == 0){
            System.out.println("---------------\nRevert fail. History is empty. The state now is still " + state + ".\n---------------");
            //State remain the same, without assign value to state.
            return;
        }
        int temp = this.state;
        this.state = previousMemento.getState();

        System.out.println("The state successfully back to " + state + " from " + temp + ".");
        return;
        /*
        try {
            Memento memento = new Memento(previousMemento.getState());
        } catch (NullPointerException e){
            System.out.println("There is no History. The state now is still " + state + ".");
            //State remain the same, without assign value to state.
            return;
        }
        */
    }
}
