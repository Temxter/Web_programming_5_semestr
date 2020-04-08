/**
 * The type Patient.
 */
public class Patient implements Runnable, Comparable {
    private String name;
    private String reason;
    private int illRate;
    private Hospital hospital;
    private Doctor doctor;
    private boolean withoutQueue;

    /**
     * Instantiates a new Patient.
     *
     * @param name     the name
     * @param reason   the reason
     * @param illRate  the ill rate
     * @param hospital the hospital
     */
    public Patient(String name, String reason, int illRate, Hospital hospital) {
        this.name = name;
        this.reason = reason;
        this.illRate = illRate;
        this.hospital = hospital;
    }

    /**
     * Patient wait.
     */
    synchronized public void patientWait(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Patient notify.
     */
    synchronized public void patientNotify(){
        this.notify();
    }

    /**
     * Go to hospital.
     */
    void goToHospital(){
        hospital.registerPatient(this);
        doctor.acceptPatient(this);
        illRate--;
    }

    /**
     * Go for a walks.
     */
    void goForAWalks(){
        System.out.println("I'm " + name + ". I'm go for a walk.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (illRate > 0)
        {
            goToHospital();
            goForAWalks();
        }
        System.out.println("I'm " + name + " and I'm healthy! :)");
    }

    /**
     * Gets hospital.
     *
     * @return the hospital
     */
    public Hospital getHospital() {
        return hospital;
    }

    /**
     * Sets hospital.
     *
     * @param hospital the hospital
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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
     * Gets doctor.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
