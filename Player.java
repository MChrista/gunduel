package gunduel;

interface Player {
    public Action nextAction();
    public void perceive(Action a);
}