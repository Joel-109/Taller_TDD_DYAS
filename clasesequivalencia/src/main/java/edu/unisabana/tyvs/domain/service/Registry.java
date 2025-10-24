package edu.unisabana.tyvs.domain.service;
import java.util.ArrayList;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private ArrayList<Person> personsArray;

    public Registry(){
        this.personsArray = new ArrayList<Person>();
    }

    public RegisterResult registerVoter(Person p) {

        // Nulidad
        if (p == null){
            return RegisterResult.INVALID;
        }

        // Estado de Vida
        if (!p.isAlive()){
            return RegisterResult.DEAD;
        }

        // Identificador (Unicidad)
        if (p.getId() <= 0){
            return RegisterResult.INVALID;
        } 

        // Verificación de Duplicados
        for (Person person : this.personsArray){
            if (person.getId() == p.getId()){
                return RegisterResult.DUPLICATED;
            }
        }

        
        // Verificación de Edad
        if (p.getAge() < 0 || p.getAge() > 120){
            return RegisterResult.INVALID_AGE;
        } else if (p.getAge() >= 0 && p.getAge() < 18){
            return RegisterResult.UNDERAGE;
        }

        this.personsArray.add(p);
        return RegisterResult.VALID;
    }
}