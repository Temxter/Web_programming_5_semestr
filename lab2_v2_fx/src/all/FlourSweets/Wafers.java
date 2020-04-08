package all.FlourSweets;

import all.Sweet.Sweet;

/**
 * The type Wafers.
 * @author Andrew
 * @version 1
 */
public class Wafers extends Sweet {

    private int wafer_amount;

    /**
     * Instantiates a new Wafers.
     *
     * @param Weight the weight
     * @param Name   the name
     */
    public Wafers(double Weight, String Name){
        super(Weight, Name);
        this.addItemToConsist("eggs");
    }

    /**
     * Gets wafer count.
     *
     * @return the wafer count
     */
    public int getWaferAmount() {
        return wafer_amount;
    }

    /**
     * Sets wafer count.
     *
     * @param wafer_count the wafer count
     */
    public void setWaferAmount(int wafer_count) {
        this.wafer_amount = wafer_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Wafers wafers = (Wafers) o;

        return wafer_amount == wafers.wafer_amount;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + wafer_amount;
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Wafers{" +
                "wafer_amount=" + wafer_amount +
                '}';
    }
}
