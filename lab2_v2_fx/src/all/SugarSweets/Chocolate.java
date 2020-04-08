package all.SugarSweets;

import all.Sweet.Sweet;


/**
 * The type Chocolate.
 * @author Andrew
 * @version 1
 */
public class Chocolate extends Sweet {

    private double cacao_percent;

    /**
     * Instantiates a new Chocolate.
     *
     * @param Weight the weight
     * @param Name   the name
     */
    public Chocolate(double Weight, String Name){
        super(Weight, Name);
        this.addItemToConsist("cacao");
    }

    /**
     * Gets cacao percent.
     *
     * @return the cacao percent
     */
    public double getCacaoPercent() {
        return cacao_percent;
    }

    /**
     * Sets cacao percent.
     *
     * @param cacao_percent the cacao percent
     */
    public void setCacaoPercent(double cacao_percent) {
        this.cacao_percent = cacao_percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Chocolate chocolate = (Chocolate) o;

        return Double.compare(chocolate.cacao_percent, cacao_percent) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(cacao_percent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Chocolate{" +
                "cacao_percent=" + cacao_percent +
                '}';
    }
}
