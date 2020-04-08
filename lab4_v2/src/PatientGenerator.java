import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Patient generator.
 */
public class PatientGenerator {
    private ArrayList<String> names = new ArrayList<>();
    private int namesNumber;

    /**
     * Instantiates a new Patient generator.
     */
    public PatientGenerator(){
        init();
    }

    /**
     * Generate patient patient.
     *
     * @return the patient
     */
    public Patient generatePatient(){
        Random random = new Random();
        int illRate = random.nextInt(10);
        int nameNumber = random.nextInt(namesNumber);
        boolean withoutQueue =  random.nextBoolean();
        Patient patient =  new Patient(names.get(nameNumber), null, illRate, withoutQueue);
        return patient;
    }

    private void init(){
        try(InputStream is = new FileInputStream("names.txt")){
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            while(line != null){
                names.add(line);
                line = buf.readLine();
            }
            namesNumber = names.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
