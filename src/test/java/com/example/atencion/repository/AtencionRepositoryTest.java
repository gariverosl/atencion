package com.example.atencion.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.atencion.model.Atencion;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AtencionRepositoryTest {

    @Autowired
    private AtencionRepository atencionRepository;
    
    @Test
    public void insertAtencionTest(){


        Atencion atenciontest = new Atencion();
        //atenciontest.setId(1);
        atenciontest.setFecha("01-10-2012");
        atenciontest.setCausa("test1");
        atenciontest.setNombreDoc("Dr.F");


        Atencion result = atencionRepository.save(atenciontest);

        assertNotNull(result.getId());
        assertEquals("Dr.F", result.getNombreDoc());
        assertEquals("test1", result.getCausa());
        assertEquals("01-10-2012", result.getFecha());

    }

    @Test
    public void getAtencionByTest() {
        // Datos de prueba
        Atencion atenciontest = new Atencion();
        
        atenciontest.setId(1);
        atenciontest.setFecha("2024-04-15");
        atenciontest.setCausa("garganta3 ");
        atenciontest.setNombreDoc("doc1");

        Optional<Atencion> result2 = atencionRepository.findById(atenciontest.getId());
        
        assertEquals(Optional.of(atenciontest) , result2 );

        //assertNotNull(result2.getId());
        //assertEquals("test1", result2.getNick());
        //assertEquals("123", result2.getPass());

    }

    @Test
    public void getExistsTest() {
        // Datos de prueba
        Atencion atenciontest = new Atencion();
        atenciontest.setId(1);
        atenciontest.setFecha("2024-04-15");
        atenciontest.setCausa("garganta3 ");
        atenciontest.setNombreDoc("doc1");

        Boolean test = atencionRepository.existsById(atenciontest.getId());
        
        assertEquals( true , test);

    }
}
