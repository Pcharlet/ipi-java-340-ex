package com.ipiecoles.java.java340.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ipiecoles.java.java340.model.Commercial;
import com.ipiecoles.java.java340.model.Employe;
import com.ipiecoles.java.java340.service.EmployeService;

import cucumber.api.java.After;
import cucumber.api.java.Before;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeRepositoryTest {

    Commercial pierreDurand = new Commercial("Durand", "Pierre", "C12345", new LocalDate(), 2500d, 0d,0);

    Commercial jeanJacques = new Commercial("Jacques", "Jean", "C12346", new LocalDate(), 1500d, 0d,0);

    Commercial jacquesDupond = new Commercial("Dupond", "Jacques", "C12347", new LocalDate(), 1500d, 0d,0);

    @Before
    public void setUp(){
        commercialRepository.deleteAll();
        employeRepository.deleteAll();
        employeRepository.save(pierreDurand);
        employeRepository.save(jeanJacques);
        employeRepository.save(jacquesDupond);
    }

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    CommercialRepository commercialRepository;

    @Test
    public void testFindByNomOrPrenomAllIgnoreCasePrenom(){
        //Given

        //When
        List<Employe> employes = employeRepository.findByNomOrPrenomAllIgnoreCase("pierre");
        Assertions.assertThat(employes).hasSize(1);
        Assertions.assertThat(employes.get(0)).isEqualTo(pierreDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNom(){
        //Given

        //When
        List<Employe> employes = employeRepository.findByNomOrPrenomAllIgnoreCase("durand");
        Assertions.assertThat(employes).hasSize(1);
        Assertions.assertThat(employes.get(0)).isEqualTo(pierreDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNomPrenom(){
        //Given

        //When
        List<Employe> employes = employeRepository.findByNomOrPrenomAllIgnoreCase("jacques");
        Assertions.assertThat(employes).hasSize(2);
        Assertions.assertThat(employes).contains(jeanJacques, jacquesDupond);
    }

    @After
    public void tearDown(){
        employeRepository.deleteAll();
    }
    
    @Test
    public void TFindEmployesPlusRiches() {
    	pierreDurand = employeRepository.save(pierreDurand);
    	jeanJacques = employeRepository.save(jeanJacques);
    	jacquesDupond = employeRepository.save(jacquesDupond);
    	
    	List<Employe> employesPlusRiches = employeRepository.findEmployePlusRiches();
    	Assertions.assertThat(employesPlusRiches).isEqualTo(1);
    	
    }
}
