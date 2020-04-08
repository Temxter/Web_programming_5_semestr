import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The type Hospital.
 */
public class Hospital implements Runnable {
    private LinkedBlockingQueue<Doctor> freeDoctors;
    private PriorityBlockingQueue<Patient> patientQueue;
    private int timeLeft = 10;
    private final int timeForCloseHospital = 10;
    private String name;

    /**
     * Instantiates a new Hospital.
     *
     * @param name the name
     */
    public Hospital(String name) {
        this.name = name;
        freeDoctors = new LinkedBlockingQueue<>();
        patientQueue = new PriorityBlockingQueue<>();
    }


    @Override
    public void run() {
        try {
            while(timeLeft > 0){
                if (freeDoctors.size() == 0 || patientQueue.size() == 0){
                    Thread.sleep(1000);
                    timeLeft--;
                }
                else
                {
                    timeLeft = timeForCloseHospital;
                    Doctor doctor = freeDoctors.remove();
                    Patient patient = patientQueue.remove();
                    if (patient.isWithoutQueue())
                        System.out.println(patient.getName() + " go to doctor without queue.");
                    patient.setDoctor(doctor);
                    patient.patientNotify();
                }
            }

            System.out.println(this.name + " is closed.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Register patient.
     *
     * @param patient the patient
     */
    public void registerPatient(Patient patient){
        patientQueue.add(patient);
        System.out.println("Patient " + patient.getName() + " come to Hospital " + this.name);
        patient.patientWait();
    }

    /**
     * Register doctor.
     *
     * @param doctor the doctor
     */
    public void registerDoctor(Doctor doctor){
        freeDoctors.add(doctor);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
