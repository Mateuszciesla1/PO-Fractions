package pl.edu.mimuw;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class FractionTest {

    @Test
    public void testToString() {
        Fraction f = new Fraction(1, 2);
        assertEquals("1/2", f.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 2);
        Fraction f3 = new Fraction(2, 4);

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
        assertEquals(f1, f3);
    }

    @Test
    public void testConstructorWithIntegers() {
        Fraction f = new Fraction(3, 4);
        assertEquals(BigInteger.valueOf(3), f.getNominator());
        assertEquals(BigInteger.valueOf(4), f.getDenominator());

        Fraction f2 = new Fraction(0, 4);
        assertEquals(BigInteger.valueOf(0), f2.getNominator());
        assertEquals(BigInteger.valueOf(1), f2.getDenominator());
    }

    @Test
    public void testConstructorWithNegativeDenominator() {
        Fraction f = new Fraction(3, -4);
        assertEquals(BigInteger.valueOf(-3), f.getNominator());
        assertEquals(BigInteger.valueOf(4), f.getDenominator());
    }

    @Test
    public void testConstructorWithBigIntegers() {
        Fraction f = new Fraction(new BigInteger("5"), new BigInteger("10"));
        assertEquals(new BigInteger("1"), f.getNominator());
        assertEquals(new BigInteger("2"), f.getDenominator());
    }

    @Test
    void WhileCreatingZeroThrow(){
        assertThrows(IllegalArgumentException.class, ()->{
            Fraction tmp = new Fraction(1,0);
        });
    }

    @Test
    void testAddition(){
        // #TEST 1
        Fraction first = new Fraction(1, 2);
        Fraction second = new Fraction(1, 2);
        Fraction Final = new Fraction(1,1);
        first = first.addition(second);
        assertEquals(first, Final);

        // #TEST2
        Fraction f1 = new Fraction(1, 4);
        Fraction f2 = new Fraction(1, 2);
        Fraction result = f1.addition(f2);
        assertEquals(new BigInteger("3"), result.getNominator());
        assertEquals(new BigInteger("4"), result.getDenominator());

    }

    @Test
    void testSubstraction(){
        //#TEST 1
        Fraction first = new Fraction(1, 4);
        Fraction second = new Fraction(1, 2);
        first = first.substraction(second);
        Fraction Final = new Fraction(-1,4);
        assertEquals(first, Final);

        //#TEST 2
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 4);
        Fraction result = f1.substraction(f2);
        assertEquals(new BigInteger("1"), result.getNominator());
        assertEquals(new BigInteger("4"), result.getDenominator());
    }

    @Test
    void CorrectMultiplication(){
        Fraction first = new Fraction(11, 3);
        Fraction second = new Fraction(1, 2);
        first = first.multiplication(second);
        Fraction Final = new Fraction(11,6);
        assertEquals(first, Final);
    }

    @Test
    void CorrectDivision(){
        Fraction first = new Fraction(11, 6);
        Fraction second = new Fraction(11, 2);
        first = first.divide(second);
        Fraction Final = new Fraction(1,3);
        assertEquals(first, Final);
    }

    @Test
    void WhileDividingZeroThrow(){
       assertThrows(IllegalArgumentException.class, ()->{
            Fraction tmp = new Fraction(1, 1);
            tmp = tmp.substraction(tmp);
            Fraction tmp2 = new Fraction(1, 1);
            tmp2 = tmp2.divide(tmp);
        });
    }

    @Test
    public void testChainedOperationsAddAndMultiply() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 3);
        Fraction f3 = new Fraction(3, 4);

        // (1/2 + 2/3) * 3/4
        Fraction result = f1.addition(f2).multiplication(f3);
        assertEquals(new BigInteger("7"), result.getNominator());
        assertEquals(new BigInteger("8"), result.getDenominator());
    }

    @Test
    public void testRepeatingOperations() {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(4, 5);

        // (2/3 + 4/5) * (2/3 + 4/5)
        Fraction sum = f1.addition(f2);
        Fraction result = sum.multiplication(sum);
        assertEquals(new BigInteger("484"), result.getNominator());
        assertEquals(new BigInteger("225"), result.getDenominator());
    }

    @Test
    public void testMixedTypesConstructor() {
        Fraction f1 = new Fraction(2, -3);
        Fraction f2 = new Fraction(new BigInteger("-2"), new BigInteger("3"));
        assertEquals(f1, f2);
    }


}
