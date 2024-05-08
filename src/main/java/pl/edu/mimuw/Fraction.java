package pl.edu.mimuw;

import java.math.BigInteger;
import java.util.Objects;


public class Fraction {
    private final BigInteger nominator, denominator;

    public BigInteger getNominator(){
        return nominator;
    }
    public BigInteger getDenominator(){
        return denominator;
    }
    public Fraction(int getNominator, int getDenominator){
        if(getDenominator < 0) {
            getDenominator = -getDenominator;
            getNominator = -getNominator;
        }
        if(getDenominator == 0){
            throw new IllegalArgumentException("Zero cannot be a denominator");
        }
        BigInteger gcd = BigInteger.valueOf(getNominator).gcd(BigInteger.valueOf(getDenominator));
        this.nominator = BigInteger.valueOf(getNominator).divide(gcd);
        this.denominator = BigInteger.valueOf(getDenominator).divide(gcd);

    }
    public Fraction(BigInteger getNominator, BigInteger getDenominator) {

        BigInteger zero = new BigInteger("0");
        BigInteger minus = new BigInteger("-1");

        if(getDenominator.compareTo(zero) < 0){
            getDenominator = getDenominator.multiply(minus);
            getNominator = getDenominator.multiply(minus);
        }

        if(getDenominator.equals(zero)){
            throw new IllegalArgumentException("Zero cannot be a denominator");
        }
        BigInteger gcd = getNominator.gcd(getDenominator);
        this.nominator = getNominator.divide(gcd);
        this.denominator =getDenominator.divide(gcd);
    }
    public Fraction addition(Fraction second){
        BigInteger newNominator = nominator.multiply(second.getDenominator());
        BigInteger tmpAdd = second.getNominator().multiply(denominator);
        newNominator = newNominator.add(tmpAdd);
        BigInteger newDenominator = denominator.multiply(second.getDenominator());
        return new Fraction(newNominator, newDenominator);
    }
    public Fraction substraction(Fraction second){
        BigInteger minus = new BigInteger("-1");
        Fraction tmp = new Fraction(second.getNominator().multiply(minus), second.getDenominator());
        return addition(tmp);
    }
    public Fraction multiplication(Fraction second){
        BigInteger newNominator = nominator.multiply(second.getNominator());
        BigInteger newDenominator = denominator.multiply(second.getDenominator());
        return new Fraction(newNominator, newDenominator);
    }
    public Fraction divide(Fraction second){
        Fraction inverseSecond = new Fraction(second.getDenominator(), second.getNominator());
        return multiplication(inverseSecond);
    }

    @Override
    public String toString(){
        return nominator.toString() + "/" + denominator.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return Objects.equals(nominator, fraction.nominator) && Objects.equals(denominator, fraction.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nominator, denominator);
    }
}
