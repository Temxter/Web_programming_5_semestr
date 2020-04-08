/**
 * The type Doctor.
 */
public class Doctor {

    private String name;
    private String speciality;
    private Hospital hospital;

    /**
     * Instantiates a new Doctor.
     *
     * @param name       the name
     * @param speciality the speciality
     * @param hospital   the hospital
     */
    public Doctor(String name, String speciality, Hospital hospital) {
        this.name = name;
        this.speciality = speciality;
        this.hospital = hospital;
    }


    /**
     * Accept patient.
     *
     * @param patient the patient
     */
    synchronized public void acceptPatient(Patient patient){
        System.out.println("Dr." + name + " treats patient " + patient.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hospital.registerDoctor(this);
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
     * Gets speciality.
     *
     * @return the speciality
     */
    public String getSpeciality() {
        return speciality;
    }
}
