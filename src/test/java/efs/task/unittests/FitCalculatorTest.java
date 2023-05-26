package efs.task.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class FitCalculatorTest {

    //testuje metodę isBMICorrect czy zwroci wartosc true dla okreslonych wartosci wag i wzrostu
    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    //testuje metodę isBMICorrect czy zwroci wartosc false dla okreslonych wartosci wag i wzrostu
    @Test
    void shouldReturnFalse_whenDietRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    //testuje metode isBMICorrect czy metoda rzuci wyjatkiem dla wzrostu = 0.0
    @Test
    void shouldThrowIllegalArgumentException_whenHeightIsZero(){
        //given
        double height = 0.0;
        double weight = 60;

        //when
        Class expectedException = IllegalArgumentException.class;

        //then
        Assertions.assertThrows(expectedException, () -> FitCalculator.isBMICorrect(weight, height));
    }

    //testuje metode isBMICorrect czy zwraca true dla wszystkich podanych wag przy stalym wzroscie
    @ParameterizedTest (name = "weight: {0}") //dostosowanie nazwy tekstu, wyswietlanym w raporcie
    @ValueSource(doubles = {78.0, 70.5, 80.2})
    void shouldReturnTrue_whenDietIsRecommended(double weight){
        //given
        double height = 1.62;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertTrue(recommended);
    }

    //testuje metode isBMICorrect czy dla wszystkich par wartosci zwraca false
    @ParameterizedTest (name = "weight: {0}, height: {1}")
    @CsvSource ({"70, 1.69", "45, 1.54", "79, 180"})
    void shouldReturnFalse_whenDietIsNotRecommended(double weight, double height){
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertFalse(recommended);
    }

    //testuje metode isBMICorrect czy dla wszystkich par wartosci zwraca false
    @ParameterizedTest (name = "weight: {0}, height: {1}")
    @CsvFileSource (resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenDietIsRecommendedFromFile(double weight, double height) {
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertFalse(recommended);
    }

    //testuje metode findUserWithTheWorstBMI czy zwraca uzytkownika o najgorszym wyniku BMI
    @Test
    void shouldReturnUserWithTheWorstBMI(){
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double weight = 97.3;
        double height = 1.79;

        //when
        User userWithTheWorstBMI = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        Assertions.assertEquals(weight, userWithTheWorstBMI.getWeight());
        Assertions.assertEquals(height, userWithTheWorstBMI.getHeight());
    }

    //testuje metode findUserWithTheWorstBMI czy zwraca null w przypadku pustej listy
    @Test
    void shouldReturnNull(){
        //given
        List<User> emptyUsers = new ArrayList<>();

        //when
        User userWithTheWorstBMI = FitCalculator.findUserWithTheWorstBMI(emptyUsers);

        //then
        Assertions.assertNull(userWithTheWorstBMI);
    }

    //testuje metode calculateBMIScore czy zwraca dobre wyniki BMI
    @Test
    void shouldBeEqualToGivenList(){
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double [] expectedBMIScore = TestConstants.TEST_USERS_BMI_SCORE;

        //when
        double [] resultBMIScore = FitCalculator.calculateBMIScore(users);

        //then
        Assertions.assertArrayEquals(expectedBMIScore, resultBMIScore);
    }
}