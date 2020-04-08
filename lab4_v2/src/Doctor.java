/**
 * @author Andrew
 * @version 1.0
 */
public class Doctor implements Runnable{
    /**
     * name of Doctor
     */
    private String name;
    /**
     * speciality of Doctor
     */
    private String speciality;
    /**
     * the hospital where doctor is worked.
     */
    private Hospital hospital;

    /**
     *
     * @param name - name of Doctor
     * @param speciality - speciality of Doctor
     * @param hospital - the hospital where doctor is worked.
     */
    public Doctor(String name, String speciality, Hospital hospital) {
        this.name = name;
        this.speciality = speciality;
        this.hospital = hospital;
    }

    /**
     * Doctor only working. This method recall @see #acceptPatient(Patient).
     *
     */
    @Override
    public void run() {
        while (hospital.isEnter()) {
            Patient patient = hospital.getPatient();
            acceptPatient(patient);
        }
        System.out.println("Dr." + name + " go home.");
    }

    /**
     * Treat patient.
     * @param patient - to treat by Doctor
     */
    public void acceptPatient(Patient patient){
        if (patient.isWithoutQueue())
            System.out.println("✔" +patient.getName() + " enter without queue.");
        System.out.println("✚Dr." + name + " treats patient " + patient.getName());
        try {
            Thread.sleep(1000*patient.getIllRate());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function get value of field {@link #name}
     * @return name of Doctor
     */
    public String getName() {
        return name;
    }

    /**
     * Function return value of field {@link #speciality}
     * @return
     */
    public String getSpeciality() {
        return speciality;
    }
}
