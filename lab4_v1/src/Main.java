import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("Florida Cleveland Clinic");

        ArrayList<Patient> patients = new ArrayList<>();

        Patient patientNick = new Patient("Nick", "Сheck health", 3, hospital);
        Patient patientAlex = new Patient("Alex", "Teeth", 5, hospital);
        Patient patientBob = new Patient("Bob", "Prevention", 3, hospital);
        Patient patientAlice = new Patient("Alice", "Eyes", 7, hospital);

        patientAlice.setWithoutQueue(true);

        patients.add(patientAlice);
        patients.add(patientAlex);
        patients.add(patientBob);
        patients.add(patientNick);

        Doctor doctorGeorge = new Doctor("George", "Family Medicine", hospital);
        Doctor doctorBradley = new Doctor("Bradley", "Therapy", hospital);

        hospital.registerDoctor(doctorBradley);
        hospital.registerDoctor(doctorGeorge);

        Thread tHospital = new Thread(hospital);
        tHospital.start();


        ArrayList<Thread> threadsPatients = new ArrayList<>();

        for(Patient patient : patients){
            Thread thread = new Thread(patient);
            threadsPatients.add(thread);
            thread.start();
        }

        try {
            tHospital.join();
            for (Thread tPatient : threadsPatients){
                tPatient.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
