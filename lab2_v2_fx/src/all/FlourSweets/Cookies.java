package all.FlourSweets;

import all.Sweet.Sweet;


/**
 * The type Cookies.
 * @author Andrew
 * @version 1
 */
public class Cookies extends Sweet {

    /**
     * The With grains.
     */
    boolean withGrains = false;

    /**
     * Instantiates a new Cookies.
     *
     * @param Weight the weight
     * @param Name   the name
     */
    public Cookies(double Weight, String Name){
        super(Weight, Name);
    }

    /**
     * Is with grains boolean.
     *
     * @return the boolean
     */
    public boolean isWithGrains() {
        return withGrains;
    }

    /**
     * Sets with grains.
     *
     * @param withGrains the with grains
     */
    public void setWithGrains(boolean withGrains) {
        this.withGrains = withGrains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cookies cookies = (Cookies) o;

        return withGrains == cookies.withGrains;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (withGrains ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Cookies{" +
                "withGrains=" + withGrains +
                '}';
    }
}
