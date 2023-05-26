package efs.task.unittests;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class PlannerTest
{
    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    //testuje metode calculateDailyCaloriesDemand czy dobrze wylicza dzienne zapotrzebowanie kalorii dla wszystkich wartosci typu wyliczeniowego
    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldCheckDailyCaloriesDemand(ActivityLevel activityLevel){
        //given
        User user = TestConstants.TEST_USER;
        Map<ActivityLevel, Integer> acitivityLevelMap = TestConstants.CALORIES_ON_ACTIVITY_LEVEL;

        //when
        int resultDailyCaloriesDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        Assertions.assertEquals(resultDailyCaloriesDemand, acitivityLevelMap.get(activityLevel));
    }


    //testuje metode calculateDailyIntake czy dobrze wylicza zapotrzebowanie na skladniki odzywcze
    @Test
    void shouldCheckDailyIntake(){
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake expextedDailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        DailyIntake resultDailyIntake = planner.calculateDailyIntake(user);

        //then
        Assertions.assertEquals(expextedDailyIntake.getCalories(), resultDailyIntake.getCalories());
        Assertions.assertEquals(expextedDailyIntake.getProtein(), resultDailyIntake.getProtein());
        Assertions.assertEquals(expextedDailyIntake.getFat(), resultDailyIntake.getFat());
        Assertions.assertEquals(expextedDailyIntake.getCarbohydrate(), resultDailyIntake.getCarbohydrate());
    }

}
