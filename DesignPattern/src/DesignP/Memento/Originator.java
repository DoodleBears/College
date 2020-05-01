package DesignP.Memento;

public class Originator {
    private int state;

    public Originator(int state){
        this.state = state;
        System.out.println("The state now is " + state + ".");
    }

    //Generate a Memento by Generator
    public Memento OriginatorMemento(){
        Memento memento = new Memento(state);
        return memento;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
        System.out.println("The state now is " + state + ".");
    }

    public void revert(Memento previousMemento){
        try {
            Memento memento = new Memento(previousMemento.getState());
        } catch (NullPointerException e){
            System.out.println("There is no History. The state now is still " + state + ".");
            //State remain the same, without assign value to state.
            return;
        }
        this.state = previousMemento.getState();

        System.out.println("The state now is back to " + state + ".");
    }
}
