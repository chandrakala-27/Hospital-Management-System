import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Patient {
    int id;
    String name;
    int age;
    String gender;

    Patient(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String toString() {
        return "Patient[ID=" + id + ", Name=" + name + ", Age=" + age + ", Gender=" + gender + "]";
    }
}

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public String toString() {
        return "Doctor[ID=" + id + ", Name=" + name + ", Specialization=" + specialization + "]";
    }
}

class Appointment {
    int id;
    Patient patient;
    Doctor doctor;
    String date;

    Appointment(int id, Patient patient, Doctor doctor, String date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public String toString() {
        return "Appointment[ID=" + id + ", Patient=" + patient.name + ", Doctor=" + doctor.name + ", Date=" + date + "]";
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Patient[] patients = new Patient[10];
    static Doctor[] doctors = new Doctor[10];
    static Appointment[] appointments = new Appointment[20];

    static int patientCount = 0, doctorCount = 0, appointmentCount = 0;

    // Add patient
    static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter age: ");
        int age = readInt();

        System.out.print("Enter gender: ");
        String gender = sc.nextLine().trim();
        if (gender.isEmpty()) {
            System.out.println("Gender cannot be empty!");
            return;
        }

        patients[patientCount] = new Patient(patientCount + 1, name, age, gender);
        System.out.println("Patient added: " + patients[patientCount]);
        patientCount++;
    }

    // Add doctor
    static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter specialization: ");
        String spec = sc.nextLine().trim();
        if (spec.isEmpty()) {
            System.out.println("Specialization cannot be empty!");
            return;
        }

        doctors[doctorCount] = new Doctor(doctorCount + 1, name, spec);
        System.out.println("Doctor added: " + doctors[doctorCount]);
        doctorCount++;
    }

    // Book appointment
    static void bookAppointment() {
        if (patientCount == 0 || doctorCount == 0) {
            System.out.println("Add at least one patient and one doctor first!");
            return;
        }

        System.out.print("Enter patient ID: ");
        int pid = readInt();
        if (pid < 1 || pid > patientCount) {
            System.out.println("Invalid patient ID!");
            return;
        }

        System.out.print("Enter doctor ID: ");
        int did = readInt();
        if (did < 1 || did > doctorCount) {
            System.out.println("Invalid doctor ID!");
            return;
        }

        System.out.print("Enter appointment date (dd-MM-yyyy): ");
        String date = sc.nextLine().trim();
        if (!isValidDate(date)) {
            System.out.println("Invalid date format! Use dd-MM-yyyy.");
            return;
        }

        appointments[appointmentCount] = new Appointment(appointmentCount + 1, patients[pid - 1], doctors[did - 1], date);
        System.out.println("Appointment booked: " + appointments[appointmentCount]);
        appointmentCount++;
    }

    // Date validation
    static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Safe integer input
    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return val;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hospital Management ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. View Patients");
            System.out.println("5. View Doctors");
            System.out.println("6. View Appointments");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1: addPatient(); break;
                case 2: addDoctor(); break;
                case 3: bookAppointment(); break;
                case 4:
                    for (int i = 0; i < patientCount; i++) System.out.println(patients[i]);
                    break;
                case 5:
                    for (int i = 0; i < doctorCount; i++) System.out.println(doctors[i]);
                    break;
                case 6:
                    for (int i = 0; i < appointmentCount; i++) System.out.println(appointments[i]);
                    break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
}
