package friday;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Customer Input
        System.out.println("===== Electricity Bill Generator =====\n");
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Meter Number: ");
        String meterNumber = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter Connection Date (DD-MM-YYYY): ");
        String connectionDate = sc.nextLine();

        System.out.print("Enter Sanctioned Load : ");
        String sanctionedLoad = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobileNumber = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        // Billing Info
        System.out.print("Enter Billing Month : ");
        String billingMonth = sc.nextLine();

        String billDate = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.print("Enter Due Date (DD-MM-YYYY): ");
        String dueDate = sc.nextLine();

        System.out.print("Enter Units Consumed: ");
        int units = sc.nextInt();
        sc.nextLine(); // clear buffer

        // Process
        Customer customer = new Customer(name, meterNumber, address, accountNumber, connectionDate, sanctionedLoad, mobileNumber, email);

        BillService service = new BillService();
        double sanctionedLoadKW = Double.parseDouble(sanctionedLoad.replaceAll("[^0-9.]", ""));
        double amount = service.calculateAmount(units, sanctionedLoadKW);

        Bill bill = new Bill(customer, units, billingMonth, billDate, dueDate, amount);

        // Generate Bill Files
        service.saveBillToFile(bill);
        PDFExporter exporter = new PDFExporter();
        exporter.generateBillPDF(bill);

        System.out.println("\n Bill successfully generated in 'bills/' folder!");
        sc.close();
    }
}