package all.SugarSweets;

import all.Sweet.Sweet;


/**
 * The type Marshmallow.
 * @author Andrew
 * @version 1
 */
public class Marshmallow extends Sweet {

    /**
     * The Pectin concentrait.
     */
    double pectin_concentrait;

    /**
     * Instantiates a new Marshmallow.
     *
     * @param Weight the weight
     * @param Name   the name
     */
    public Marshmallow(double Weight, String Name){
        super(Weight, Name);
        this.addItemToConsist("eggs");
    }

    /**
     * Gets pectin concentrait.
     *
     * @return the pectin concentrait
     */
    public double getPectin_concentrait() {
        return pectin_concentrait;
    }

    /**
     * Sets pectin concentrait.
     *
     * @param pectin_concentrait the pectin concentrait
     */
    public void setPectin_concentrait(double pectin_concentrait) {
        this.pectin_concentrait = pectin_concentrait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Marshmallow that = (Marshmallow) o;

        return Double.compare(that.pectin_concentrait, pectin_concentrait) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(pectin_concentrait);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Marshmallow{" +
                "pectin_concentrait=" + pectin_concentrait +
                '}';
    }
}
