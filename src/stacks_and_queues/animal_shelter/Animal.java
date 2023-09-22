package stacks_and_queues.animal_shelter;

public class Animal {
    private int priority;
    public Animal(int priority) {
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }
    public boolean isHigherPriorityThan(Animal animal) {
        return this.getPriority() < animal.getPriority();
    }
}
