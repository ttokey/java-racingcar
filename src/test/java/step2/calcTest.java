package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class calcTest {
    Calculator calculator = new Calculator();

    @Test
    @DisplayName("input split test")
    void splitStr() {
        String testStr = "2 + 3 * 4 / 2";
        String[] splitStr = calculator.splitStr(testStr);
        assertThat(splitStr).containsExactly("2", "+", "3", "*", "4", "/", "2");
    }


    @DisplayName("Valid input String is Blank or Null exception case")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void validBlankOrNull(String inputStr) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            calculator.validBlankOrNull(inputStr);
        });
    }

    @ParameterizedTest
    @DisplayName("Valid input String is Blank or Null not exception case")
    @ValueSource(strings = {"2 + 3 * 4 / 2", "123"})
    void validNotBlankOrNullStr(String inputStr) {
        assertThatCode(() -> calculator.validBlankOrNull(inputStr)).doesNotThrowAnyException();
    }

    @DisplayName("Valid input operation exception case")
    @ParameterizedTest
    @ValueSource(strings = {"&", "1", "^", "%"})
    void validNotOperation(String inputOperation) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            calculator.validOperation(inputOperation);
        });
    }

    @DisplayName("Valid input operation not exception case")
    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/"})
    void validOperation(String inputOperation) {
        assertThatCode(() -> calculator.validOperation(inputOperation)).doesNotThrowAnyException();
    }

    @DisplayName("Valid input String has correct operation")
    @Test
    void validAllOperation() {
        String[] inputStrArr = {"2", "+", "3", "*", "4", "-", "5", "/", "6"};
        Boolean result = calculator.isCorrectOperation(inputStrArr);
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("test plus")
    void plus() {
        Integer input1 = 3;
        Integer input2 = 5;
        Integer expectResult = 8;
        Integer result = calculator.plus(input1, input2);

        assertThat(result).isEqualTo(expectResult);
    }

    @Test
    @DisplayName("test minus")
    void minus() {
        Integer input1 = 4;
        Integer input2 = 3;
        Integer expectResult = 1;
        Integer result = calculator.minus(input1, input2);

        assertThat(result).isEqualTo(expectResult);
    }

    @Test
    @DisplayName("test multiple")
    void multiple() {
        Integer input1 = 3;
        Integer input2 = 5;
        Integer expectResult = 15;
        Integer result = calculator.multiple(input1, input2);

        assertThat(result).isEqualTo(expectResult);
    }

    @Test
    @DisplayName("test division")
    void division() {
        Integer input1 = 4;
        Integer input2 = 2;
        Integer expectResult = 2;
        Integer result = calculator.division(input1, input2);

        assertThat(result).isEqualTo(expectResult);
    }

    @ParameterizedTest
    @CsvSource({"\"+\",\"6\"", "\"-\",\"2\"", "\"*\",\"8\"", "\"/\",\"2\""})
    @DisplayName("operator Test")
    void operator(String inputOperation, String expectResult) {
        String inputNum1 = "4";
        String inputNum2 = "2";

        String result = calculator.operator(inputNum1, inputNum2, inputOperation);

        assertThat(result).isEqualTo(expectResult);
    }


    @ParameterizedTest
    @CsvSource({"\"2 + 3 * 4 / 2\",\"10\"", "\"1 + 5 * 4 / 3\",\"8\""})
    @DisplayName("calculate Test")
    void calculate(String inputStr, String expectResult) {
        String result = calculator.calculate(inputStr);
        assertThat(result).isEqualTo(expectResult);
    }


}
