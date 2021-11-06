import com.epam.ofeitus.calculator.Calculator;
import com.epam.ofeitus.calculator.converter.ExpressionConverterException;
import com.epam.ofeitus.calculator.parser.ExpressionParserException;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        try {
            System.out.println(calculator.evaluate("2**3**2 + ((2**3)**2//2 - 5*(243//3)**2) % 13"));
        } catch (ExpressionParserException | ExpressionConverterException e) {
            e.printStackTrace();
        }
    }
}
