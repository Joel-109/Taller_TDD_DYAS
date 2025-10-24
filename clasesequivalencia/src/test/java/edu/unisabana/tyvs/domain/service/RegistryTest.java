package edu.unisabana.tyvs.domain.service;

import org.junit.Assert;
import org.junit.Test;

import edu.unisabana.tyvs.domain.model.Gender;
import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class RegistryTest {
    
    @Test
    public void shouldRegisterValidPerson() {
        // Arrange: preparar los datos y el objeto a probar
        Registry registry = new Registry();
        Person person = new Person("Ana", 1, 30, Gender.FEMALE, true);

        // Act: ejecutar la acción que queremos probar
        RegisterResult result = registry.registerVoter(person);

        // Assert: verificar el resultado esperado
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldRejectDeadPerson(){
        Registry registry = new Registry();
        Person dead = new Person("Carlos", 2, 40, Gender.MALE, false);

        RegisterResult result = registry.registerVoter(dead);

        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test 
    public void shouldRejectIdDuplicated(){
        Registry registry = new Registry();
        Person person = new Person("Francisco", 2, 76, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }

    @Test
    public void shouldRejectWhenPersonIsNull(){
        Registry registry = new Registry();
        Person person = null;

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldRejectWhenIdIsZeroOrNegative(){
        Registry registry = new Registry();
        Person person = new Person("Carlos", 0, 25, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldRejectUnderageAt17(){
        Registry registry = new Registry();
        Person person = new Person("Joel", 3, 17, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test 
    public void shouldAcceptAdultAt18(){
        Registry registry = new Registry();
        Person person = new Person("Michael", 4, 18, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldAcceptMaxAge120(){
        Registry registry = new Registry();
        Person person = new Person("Luisa", 5, 120,Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldRejectInvalidAgeOver120(){
        Registry registry = new Registry();
        Person person = new Person("Sara", 6, 121,Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }
}
