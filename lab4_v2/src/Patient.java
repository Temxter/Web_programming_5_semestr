/**
 * The type Patient.
 */
public class Patient implements Comparable {
    private String name;
    private String reason;
    private int illRate;
    private boolean withoutQueue;

    /**
     * Instantiates a new Patient.
     *
     * @param name         the name
     * @param reason       the reason
     * @param illRate      the ill rate
     * @param withoutQueue the without queue
     */
    public Patient(String name, String reason, int illRate, boolean withoutQueue) {
        this.name = name;
        this.reason = reason;
        this.illRate = illRate;
        this.withoutQueue = withoutQueue;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets reason.
     *
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets reason.
     *
     * @param reason the reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Gets ill rate.
     *
     * @return the ill rate
     */
    public int getIllRate() {
        return illRate;
    }

    /**
     * Sets ill rate.
     *
     * @param illRate the ill rate
     */
    public void setIllRate(int illRate) {
        this.illRate = illRate;
    }

    /**
     * Is without queue boolean.
     *
     * @return the boolean
     */
    public boolean isWithoutQueue() {
        return withoutQueue;
    }

    /**
     * Sets without queue.
     *
     * @param withoutQueue the without queue
     */
    public void setWithoutQueue(boolean withoutQueue) {
        this.withoutQueue = withoutQueue;
    }

    @Override
    public int compareTo(Object o) {
        if(this.isWithoutQueue() && ! ((Patient)o).isWithoutQueue())
            return 0;
        if(this.isWithoutQueue())
            return 1;
        return 0;
    }

}
