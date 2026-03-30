/**
 * Class Patient: Represents an individual under medical care.
 */
class Patient {
    private String patientId;
    private String name;
    private int age;
    private String diagnosis;
    private boolean admitted;

    // Constructor to initialize all attributes
    public Patient(String patientId, String name, int age, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.admitted = false; // Patients are not admitted by default
    }

    // Accessors
    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public boolean isAdmitted() { return admitted; }

    public void admit() {
        this.admitted = true;
        System.out.println("Patient " + name + " has been admitted.");
    }

    public void discharge() {
        this.admitted = false;
        System.out.println("Patient " + name + " has been discharged.");
    }

    public void updateDiagnosis(String newDiagnosis) {
        this.diagnosis = newDiagnosis;
        System.out.println("Diagnosis updated for " + name + ": " + newDiagnosis);
    }

    public void displayPatientInfo() {
        String status = admitted ? "Admitted" : "Discharged";
        System.out.printf("ID: %-6s | Name: %-12s | Age: %-3d | Diagnosis: %-15s | Status: %s%n", 
                          patientId, name, age, diagnosis, status);
    }
}

/**
 * Class HospitalWard: Manages a collection of Patient objects.
 */
class HospitalWard {
    private Patient[] patients;
    private int count;

    public HospitalWard() {
        this.patients = new Patient[20]; // Maximum 20 patients
        this.count = 0;
    }

    public void admitPatient(Patient p) {
        if (count < 20) {
            patients[count] = p;
            p.admit(); // Set the patient's internal status
            count++;
        } else {
            System.out.println("Error: Ward is at full capacity.");
        }
    }

    public void dischargePatient(String patientId) {
        for (int i = 0; i < count; i++) {
            if (patients[i].getPatientId().equals(patientId)) {
                patients[i].discharge();
                return;
            }
        }
        System.out.println("Patient with ID " + patientId + " not found in this ward.");
    }

    public Patient findPatient(String patientId) {
        for (int i = 0; i < count; i++) {
            if (patients[i].getPatientId().equals(patientId)) {
                return patients[i];
            }
        }
        return null;
    }

    public void listAdmittedPatients() {
        System.out.println("\n--- CURRENTLY ADMITTED PATIENTS ---");
        boolean anyAdmitted = false;
        for (int i = 0; i < count; i++) {
            if (patients[i].isAdmitted()) {
                patients[i].displayPatientInfo();
                anyAdmitted = true;
            }
        }
        if (!anyAdmitted) System.out.println("No patients are currently admitted.");
        System.out.println("-----------------------------------------------\n");
    }
}

/**
 * Main Class: Executes hospital operations.
 */
public class HospitalManagementSystem {
    public static void main(String[] args) {
        // 1. Create 4 Patient objects
        Patient p1 = new Patient("P01", "John Doe", 45, "Fever");
        Patient p2 = new Patient("P02", "Jane Smith", 30, "Fracture");
        Patient p3 = new Patient("P03", "Sam Wilson", 65, "Diabetes");
        Patient p4 = new Patient("P04", "Lucy Brown", 22, "Migraine");

        HospitalWard ward = new HospitalWard();

        // 2. Admit 3 patients to a HospitalWard
        System.out.println("--- Admission Records ---");
        ward.admitPatient(p1);
        ward.admitPatient(p2);
        ward.admitPatient(p3);

        // 3. Discharge one patient and update another's diagnosis
        System.out.println("\n--- Updates & Discharges ---");
        ward.dischargePatient("P02"); // Discharging Jane Smith

        Patient patientToUpdate = ward.findPatient("P01");
        if (patientToUpdate != null) {
            patientToUpdate.updateDiagnosis("Pneumonia");
        }

        // 4. Display all currently admitted patients
        ward.listAdmittedPatients();
    }
}