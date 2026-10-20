package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return this.animals;
    }

    public List<T> findBySpecies(String species){
        List<T> r = new ArrayList<>();

        for(T animal : this.animals) {
            if (animal.getSpecies().equals(species)) {
                r.add(animal);
            }
        }
        return r;
    }

    public List<T> findAvailableAnimals(){
        List<T> r = new ArrayList<>();

        for(T animal : this.animals){
            if(animal.getAdoptionStatus().equals(AdoptionStatus.AVAILABLE)){
                r.add(animal);
            }
        }
        return r;
    }

    public boolean markAsAdopted(String id){
        // I've changed the type from void to boolean
        // to return True if the animal with the id has been found.
        // It will help me to handle the case when a user
        // enter a nonexistent animal id OR an id of already adopted animal

        for(T animal : this.animals){
            if(animal.getId().toString().equals(id)){
                if(animal.getAdoptionStatus().equals(AdoptionStatus.ADOPTED)){
                    return false;
                }

                animal.markAsAdopted();
                return true;
            }
        }
        return false;
    }
}
