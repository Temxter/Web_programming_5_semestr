package all.SugarSweets;

import all.Sweet.Sweet;


/**
 * The type Candy.
 * @author Andrew
 * @version 1
 */
public class Candy extends Sweet {

    /**
     * The enum Candy form.
     */
    public enum CandyForm{
        /**
         * Chocolate candy form.
         */
        chocolate,
        /**
         * Jelly candy form.
         */
        jelly,
        /**
         * Sucker candy form.
         */
        sucker,
        /**
         * Caramel candy form.
         */
        caramel,
        /**
         * Other candy form.
         */
        other,
    }

    private CandyForm candyForm;

    /**
     * Instantiates a new Candy.
     *
     * @param Weight the weight
     * @param Name   the name
     */
    public Candy(double Weight, String Name){
        super(Weight, Name);
    }

    /**
     * Gets candy form.
     *
     * @return the candy form
     */
    public CandyForm getCandyForm() {
        return candyForm;
    }

    /**
     * Sets candy form.
     *
     * @param candyForm the candy form
     */
    public void setCandyForm(CandyForm candyForm) {
        this.candyForm = candyForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Candy candy = (Candy) o;

        return candyForm == candy.candyForm;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (candyForm != null ? candyForm.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Candy{" +
                "candyForm=" + candyForm +
                '}';
    }
}
