package FlourSweets;

import Sweet.Sweet;

/**
 * The type Wafers.
 */
public class Wafers extends Sweet {

    private int wafer_count;

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
    public int getWafer_count() {
        return wafer_count;
    }

    /**
     * Sets wafer count.
     *
     * @param wafer_count the wafer count
     */
    public void setWafer_count(int wafer_count) {
        this.wafer_count = wafer_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Wafers wafers = (Wafers) o;

        return wafer_count == wafers.wafer_count;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + wafer_count;
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Wafers{" +
                "wafer_count=" + wafer_count +
                '}';
    }
}
