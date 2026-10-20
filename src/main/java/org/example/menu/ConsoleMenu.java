package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){
        // TODO:
        // Show menu in a loop
        // Read user input
        // Call correct 'Shelter' methods based on selected option

        while(true){
            this.printMenu();
            System.out.print(">>> ");
            String data = this.scanner.nextLine();

            int value;
            try{
                value = Integer.parseInt(data);
            } catch(NumberFormatException ex){
                System.out.println("\nInvalid data. Try again.");
                continue;
            }

            if(value == 0){
                break;
            }

            switch (value){
                case 1:
                    System.out.print("Enter a name of your animal: ");
                    String animalName = scanner.nextLine();

                    System.out.print("Enter an age of your animal: ");

                    String ageStr = scanner.nextLine();
                    int age;
                    try{
                        age = Integer.parseInt(ageStr);
                    }catch(Exception ex){
                        System.out.println("Invalid age. Animal adding failed");
                        break;
                    }

                    System.out.print("Choose species of the animal (Dog, Cat, Bird): ");
                    String animalSpecies = scanner.nextLine();

                    switch (animalSpecies.toLowerCase()){
                        case "dog" -> {
                            this.shelter.addAnimal(new Dog(
                                    new AnimalId(),
                                    animalName,
                                    age
                            ));
                            System.out.println("Successfully added the animal!");
                        }
                        case "cat" -> {
                            this.shelter.addAnimal(new Cat(
                                    new AnimalId(),
                                    animalName,
                                    age
                            ));
                            System.out.println("Successfully added the animal!");
                        }
                        case "bird" -> {
                            this.shelter.addAnimal(new Bird(
                                    new AnimalId(),
                                    animalName,
                                    age
                            ));
                            System.out.println("Successfully added the animal!");
                        }
                        default -> System.out.println("Invalid species of the animal. Adding failed");
                    }
                    break;
                case 2:
                    this.shelter.getAllAnimals().forEach(System.out::println);
                   break;
                case 3:
                    System.out.print("\nPlease enter the species of an animal: ");
                    String userInput = scanner.nextLine();

                    List<Animal> result = this.shelter.findBySpecies(userInput);
                    System.out.println("\nFound " + result.size() + " animals");

                    result.forEach(System.out::println);
                    break;
                case 4:
                    this.shelter.findAvailableAnimals().forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("\nPlease enter the ID of the animal: ");
                    String animalId = this.scanner.nextLine();

                    boolean isSuccessfull = this.shelter.markAsAdopted(animalId);
                    if(!isSuccessfull){
                        System.out.println("The animal with your entered id is already adopted or doesn't exist");
                    }else{
                        System.out.println("Successfully adopted the animal");
                    }
                    break;

            }
        }
    }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit
                """);
    }
}
