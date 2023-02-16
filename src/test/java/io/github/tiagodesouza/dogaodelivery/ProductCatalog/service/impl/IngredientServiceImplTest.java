package io.github.tiagodesouza.dogaodelivery.ProductCatalog.service.impl;

import io.github.tiagodesouza.dogaodelivery.repository.IngredientRepository;
import io.github.tiagodesouza.dogaodelivery.service.impl.IngredientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    private IngredientRepository ingredientRepository;
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    public void setUp() {
        ingredientRepository = mock(IngredientRepository.class);
        ingredientService = new IngredientServiceImpl(ingredientRepository);
    }

//    @Test
//    public void whenCreateIngredient_thenIngredientShouldSaved() {
//        Ingredient ingredient = new Ingredient("Alface", 10, new BigDecimal("0.4"));
//        ingredientService.createIngredient(ingredient);
//        verify(ingredientRepository, times(1)).save(ingredient);
//    }
//
//    @Test
//    public void whenCreateIngredientWithExistingName_thenIngredientAlreadyExistsExceptionShouldBeThrown() {
//        Ingredient ingredient = new Ingredient("Alface", 10, new BigDecimal("0.4"));
//        when(ingredientRepository.findByName(ingredient.getName())).thenReturn(Optional.of(ingredient));
//        assertThrows(IngredientAlreadyExists.class, () -> ingredientService.createIngredient(ingredient));
//    }
//
//    @Test
//    public void whenFindByName_thenReturnIngredient() {
//        String name = "Alface";
//        Ingredient ingredient = new Ingredient(name, 10, new BigDecimal("0.4"));
//
//        when(ingredientRepository.findByName(name)).thenReturn(Optional.of(ingredient));
//
//        Ingredient ingredientSaved = ingredientService.findByName(name);
//
//        assertEquals(ingredient, ingredientSaved);
//    }
//
//    @Test
//    public void whenFindByExistingName_thenIngredientNotFoundExceptionShouldBeThrown() {
//        String name = "Alface";
//
//        when(ingredientRepository.findByName(name)).thenReturn(Optional.empty());
//
//        assertThrows(IngredientNotFoundException.class, () -> ingredientService.findByName(name));
//    }
//
//    @Test
//    public void whenUpdateIngredient_thenIngredientShouldBeUpdate() {
//        Long id = 1L;
//        Ingredient ingredient = new Ingredient("Alface", 10, new BigDecimal("0.4"));
//        Ingredient ingredientToUpdate = new Ingredient("Alface", 10, new BigDecimal("1"));
//
//        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));
//
//        ingredientService.updateIngredient(id, ingredientToUpdate);
//
//        verify(ingredientRepository, times(1)).save(ingredient);
//        assertEquals(ingredientToUpdate.getName(), ingredient.getName());
//        assertEquals(ingredientToUpdate.getQuantity(), ingredient.getQuantity());
//        assertEquals(ingredientToUpdate.getPrice(), ingredient.getPrice());
//    }
//
//    @Test
//    public void whenUpdateNonExistingIngredient_thenIngredientNotFoundExceptionShouldBeThrown() {
//        Long id = 1L;
//        Ingredient ingredientToUpdate = new Ingredient("Alface", 10, new BigDecimal("0.4"));
//
//        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(IngredientNotFoundException.class, () -> ingredientService.updateIngredient(id, ingredientToUpdate));
//    }
//
//    @Test
//    public void whenFindAllIngredients_thenReturnAListOfIngredients() {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("Alface", 10, new BigDecimal("0.4")),
//                new Ingredient("Bacon", 10, new BigDecimal("2")),
//                new Ingredient("Hamburguer", 10, new BigDecimal("3"))
//        );
//
//        when(ingredientRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(ingredients));
//
//        List<Ingredient> result = ingredientService.findAllIngredients(0, 10);
//
//        verify(ingredientRepository, times(1)).findAll(PageRequest.of(0, 10));
//        assertEquals(ingredients, result);
//    }
//
//    @Test
//    public void whenFindIngredientById_thenReturnIngredient() {
//        Long id = 1L;
//        Ingredient ingredient = new Ingredient("Alface", 10, new BigDecimal("0.4"));
//
//        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));
//
//        Ingredient result = ingredientService.findIngredientById(id);
//
//        verify(ingredientRepository, times(1)).findById(id);
//        assertEquals(ingredient, result);
//    }
//
//    @Test
//    public void whenFindIngredientByIdNonExistingIngredient_thenIngredientNotFoundExceptionShouldBeThrows() {
//        Long id = 1L;
//
//        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(IngredientNotFoundException.class, () -> ingredientService.findIngredientById(id));
//    }
//
//    @Test
//    public void whenDeleteExistingIngredient_thenIngredientShouldBeDeleted() {
//        Long id = 1L;
//        Ingredient ingredient = new Ingredient("Alface", 10, new BigDecimal("0.4"));
//
//        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));
//        doNothing().when(ingredientRepository).deleteById(id);
//
//        ingredientService.deleteIngredient(id);
//
//        verify(ingredientRepository, times(1)).findById(id);
//        verify(ingredientRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    public void whenDeleteNonExistingIngredient_thenIngredientNotFoundExceptionShouldBeThrows() {
//        Long id = 1L;
//
//        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(IngredientNotFoundException.class, () -> ingredientService.deleteIngredient(id));
//    }
}