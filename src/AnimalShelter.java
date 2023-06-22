import java.util.LinkedList;

public class AnimalShelter {
    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int nextPriorityNumber = 0;

    public void enqueue(Dog dog) {
        dogs.addLast(dog);
    }
    public void enqueue(Cat cat) {
        cats.addLast(cat);
    }
    public Dog dequeueDog() {
        return dogs.removeFirst();
    }
    public Dog peekDog() {
        return dogs.getFirst();
    }
    public Cat dequeueCat() {
        return cats.removeFirst();
    }
    public Cat peekCat() {
        return cats.getFirst();
    }
    public Animal dequeueAny() {
        if(isDogsEmpty() && !isCatsEmpty()) {
            return dequeueCat();
        } else if (isCatsEmpty() && !isDogsEmpty()) {
            return dequeueDog();
        } else {
            Dog firstDog = peekDog();
            Cat firstCat = peekCat();
            Animal animal = firstDog.isHigherPriorityThan(firstCat) ? dogs.removeFirst() : cats.removeFirst();
            return animal;
        }
    }
    public Animal peekAny() {
        if(isDogsEmpty() && !isCatsEmpty()) {
            return peekCat();
        } else if (isCatsEmpty() && !isDogsEmpty()) {
            return peekDog();
        } else {
            Dog firstDog = peekDog();
            Cat firstCat = peekCat();
            Animal animal = firstDog.isHigherPriorityThan(firstCat) ? firstDog : firstCat;
            return animal;
        }
    }
    public int getNextPriorityNumber() {
        return ++nextPriorityNumber;
    }
    public boolean isEmpty() {
        return dogs.isEmpty() && cats.isEmpty();
    }
    public boolean isDogsEmpty() {
        return dogs.isEmpty();
    }
    public boolean isCatsEmpty() {
        return cats.isEmpty();
    }
    public static void main(String[] args) {
        AnimalShelter animalShelter = new AnimalShelter();
        for(int i = 0; i < 10; i++) {
            int dogPriority = animalShelter.getNextPriorityNumber();
            Dog dog = new Dog(dogPriority);
            animalShelter.enqueue(dog);
            int catPriority = animalShelter.getNextPriorityNumber();
            Cat cat = new Cat(catPriority);
            animalShelter.enqueue(cat);
        }
        for(int i = 0; i < 5; i++) {
            Dog dog = animalShelter.dequeueDog();
            StringBuilder dogSB = new StringBuilder("Dog Priority: ");
            dogSB.append(dog.getPriority());
            System.out.println(dogSB);
            Cat cat = animalShelter.dequeueCat();
            StringBuilder catSB = new StringBuilder("Cat Priority: ");
            catSB.append(cat.getPriority());
            System.out.println(catSB);
        }
        int animalPriority = animalShelter.peekAny().getPriority();
        StringBuilder animalPeekSB = new StringBuilder("Animal Peek: ");
        System.out.println(animalPeekSB.append(animalPriority));
        while(!animalShelter.isEmpty()) {
            Animal animal = animalShelter.dequeueAny();
            StringBuilder animalSB = new StringBuilder("Animal Priority: ");
            animalSB.append(animal.getPriority());
            System.out.println(animalSB);
        }
    }
}
