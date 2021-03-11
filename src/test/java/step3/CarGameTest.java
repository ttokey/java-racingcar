package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.domain.Car;
import step3.service.GameService;
import step3.service.MoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class CarGameTest {
    MoveStrategy moveStrategy = new MoveStrategy();
    Random random = new Random();
    GameService gameService = new GameService();

    @Test
    @DisplayName("자동차를 한칸 움직이는 테스트")
    void moveCar() {
        // given
        Car testCar = new Car();
        Boolean isMove = true;
        Integer expectLocation = 1;

        // when
        testCar.moveCar(isMove);

        // then
        assertThat(testCar.getCurrentLocation()).isEqualTo(expectLocation);
    }

    @Test
    @DisplayName("n대의 자동차가 생성되었는지 테스트")
    void generateCar() {
        //given
        List<Car> expectCars = new ArrayList<>();
        Integer carNumber = 3;
        expectCars.add(new Car());
        expectCars.add(new Car());
        expectCars.add(new Car());

        //when
        List<Car> resultCars = gameService.generateCar(carNumber);

        //then
        for (Integer i = 0; i < carNumber; i++) {
            assertThat(resultCars.get(i)).isEqualToComparingFieldByField(expectCars.get(i));
        }
    }


    @Test
    @DisplayName("난수 발생 테스트")
    void generateRandomNumber() {
        //given
        Integer rowNum = 0;
        Integer highNum = 9;
        List<Integer> results = new ArrayList<>();
        Integer testLoopTime = 100;
        Integer testLoopStart = 0;

        //when
        for (Integer i = testLoopStart; i < testLoopTime; i++) {
            results.add(moveStrategy.generateRandomNumber());
        }

        //then
        for (Integer result : results) {
            assertThat(result).isBetween(rowNum, highNum);
        }
    }


    @Test
    @DisplayName("랜덤 난수를 입력했을 때 움직일지 안움직일지 정하는 메소드 테스트")
    void isMove() {
        //given
        Integer moveNum = 5;
        Integer nonMoveNum = 1;

        //when
        Boolean moveResult = moveStrategy.getIsMove(moveNum);
        Boolean nonMoveResult = moveStrategy.getIsMove(nonMoveNum);

        //then
        assertThat(moveResult).isTrue();
        assertThat(nonMoveResult).isFalse();
    }

    @Test
    @DisplayName("현재 위치 표현 테스트")
    void getLocationView() {
        //given
        Integer expectLocation = 3;
        Car testCar = new Car(expectLocation);
        String expectLocationView = "---";

        //when
        String result = testCar.getLocationView();


        //then
        assertThat(result).isEqualTo(expectLocationView);
    }


    @Test
    @DisplayName("게임 진행 테스트")
    void runManyStep() {
        //given
        List<Integer> expectLocation = new ArrayList<>();
        Integer testCarNum = 5;

        Integer startLocation = 0;
        Integer step = 5;

        for (Integer i = 0; i < testCarNum; i++) {
            expectLocation.add(startLocation + random.nextInt(step + 1));
        }
        Integer rowNum = startLocation;
        Integer highNum = startLocation + step;

        while (true) {
            //given
            List<Car> expectCars = new ArrayList<>();
            for (Integer i = 0; i < testCarNum; i++) {
                expectCars.add(new Car());
            }


            //when
            for (Integer i = 0; i < step; i++) {
                GameService.runStep(expectCars);
            }

            //then
            isCorrectLocation(expectCars, testCarNum, rowNum, highNum);
            if (isExpectLocation(expectCars, expectLocation, testCarNum)) {
                break;
            }
        }
    }


    void isCorrectLocation(List<Car> cars, Integer testCarNum, Integer rowNum, Integer highNum) {
        for (Integer i = 0; i < testCarNum; i++) {
            assertThat(cars.get(i).getCurrentLocation()).isBetween(rowNum, highNum);
        }
    }

    Boolean isExpectLocation(List<Car> cars, List<Integer> expectLocation, Integer testCarNum) {
        for (Integer i = 0; i < testCarNum; i++) {
            if (cars.get(i).getCurrentLocation() != expectLocation.get(i)) {
                return false;
            }
        }
        return true;
    }


    //given
    //when
    //then

}
