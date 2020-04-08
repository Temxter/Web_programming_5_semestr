public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("Florida Cleveland Clinic", 10);

        Doctor doctorGeorge = new Doctor("George", "Family Medicine", hospital);
        Doctor doctorBradley = new Doctor("Bradley", "Therapy", hospital);

        Thread tHospital = new Thread(hospital);
        Thread tDoctorGeorge = new Thread(doctorGeorge);
        Thread tDoctorBradley = new Thread(doctorBradley);

        tHospital.start();
        tDoctorBradley.start();
        tDoctorGeorge.start();

        try {
            tHospital.join();
            tDoctorGeorge.join();
            tDoctorBradley.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
