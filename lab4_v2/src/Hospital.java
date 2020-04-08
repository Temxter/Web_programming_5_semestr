import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The type Hospital.
 */
public class Hospital implements Runnable{
    private PriorityBlockingQueue<Patient> patientQueue;
    private String name;
    private int timeLeft = 10;
    private final int timeForCloseHospital = 10;
    private int patientNumber = 10;

    /**
     * Instantiates a new Hospital.
     *
     * @param name          the name
     * @param patientNumber the patient number
     */
    public Hospital(String name, int patientNumber) {
        this.name = name;
        this.patientNumber = patientNumber;
        patientQueue = new PriorityBlockingQueue<>();
    }

    /**
     * Register patient.
     *
     * @param patient the patient
     */
    public void registerPatient(Patient patient){
        System.out.println("➔Patient " + patient.getName() + " come to Hospital " + this.name);
        patientQueue.add(patient);
    }

    /**
     * Waiting.
     */
    synchronized void waiting(){
        Random random = new Random();
        int waitingTime = random.nextInt(10) + 1;
        try {
            this.wait(200*waitingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        PatientGenerator patientGenerator = new PatientGenerator();
        while(patientNumber > 0) {
            patientNumber--;
            Patient patient = patientGenerator.generatePatient();
            registerPatient(patient);
            waiting();
            }
        while (patientQueue.size() > 0)
            waiting();
        System.out.println(this.name + " is closed.");

    }

    /**
     * Get patient patient.
     *
     * @return the patient
     */
    public Patient getPatient(){
        try {
            return patientQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Is enter boolean.
     *
     * @return the boolean
     */
    public boolean isEnter(){
        return patientNumber > 0 || patientQueue.size() > 0;
    }
}
