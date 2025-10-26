package edu.unisabana.tyvs.domain.service;
import java.util.ArrayList;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private ArrayList<Person> personsArray;
    private int minAge;
    private int maxAge;

    public Registry(){
        this.personsArray = new ArrayList<Person>();
        this.minAge = 18;
        this.maxAge = 120;
    }

    public RegisterResult registerVoter(Person p) {

        // Nulidad
        if (p == null){
            return RegisterResult.INVALID;
        }

        int age = p.getAge();
        boolean alive = p.isAlive();
        int id = p.getId();

        // Estado de Vida
        if (!alive){
            return RegisterResult.DEAD;
        }

        // Identificador (Unicidad)
        if (id <= 0){
            return RegisterResult.INVALID;
        } 

        for (Person person : this.personsArray){
            // Verificación de Duplicados
            if (person.getId() == id){
                return RegisterResult.DUPLICATED;
            }
        }

        // Validación de Edad
        if (age < 0 || age > this.maxAge){
            return RegisterResult.INVALID_AGE;
        } else if (age >= 0 && age < this.minAge){
            return RegisterResult.UNDERAGE;
        }

        this.personsArray.add(p);
        return RegisterResult.VALID;
    }
}