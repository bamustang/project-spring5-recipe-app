package guru.springframework.recipes.repository;

import guru.springframework.recipes.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository uom;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByUnitOfMeasurePinch() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = uom.findByUnitOfMeasure("pinch");
        assertEquals("pinch", unitOfMeasureOptional.get().getUnitOfMeasure());
    }

    @Test
    public void findByUnitOfMeasureCup() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = uom.findByUnitOfMeasure("cup");
        assertEquals("cup", unitOfMeasureOptional.get().getUnitOfMeasure());
    }

    @Test
    public void findByUnitOfMeasureTbsp() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = uom.findByUnitOfMeasure("tablespoon");
        assertEquals("tablespoon", unitOfMeasureOptional.get().getUnitOfMeasure());
    }



}