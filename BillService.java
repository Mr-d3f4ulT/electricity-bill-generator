package friday;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class BillService {

    public double calculateAmount(int units, double sanctionedLoadKW) {
        double energyCharge = 0;

        if (units <= 100) {
            energyCharge = units * 5.50;
        } else if (units <= 150) {
            energyCharge = (100 * 5.50) + ((units - 100) * 5.50);
        } else if (units <= 300) {
            energyCharge = (100 * 5.50) + (50 * 5.50) + ((units - 150) * 6.00);
        } else {
            energyCharge = (100 * 5.50) + (50 * 5.50) + (150 * 6.00) + ((units - 300) * 6.50);
        }

        double fixedCharge = 110 * sanctionedLoadKW;
        double electricityDuty = 0.05 * energyCharge;
        double cgst = 0.09 * energyCharge;
        double sgst = 0.09 * energyCharge;
        double meterRent = 10.00;
        double subsidy = 20.00;

        double totalPayable = energyCharge + fixedCharge + electricityDuty + cgst + sgst + meterRent - subsidy;

        return totalPayable;
    }

    public void saveBillToFile(Bill bill) {
        DecimalFormat df = new DecimalFormat("0.00");
        double sanctionedLoadKW = Double.parseDouble(bill.getCustomer().getSanctionedLoad().replaceAll("[^0-9.]", ""));
        int units = bill.getUnitsConsumed();

        double energyCharge;
        if (units <= 100) {
            energyCharge = units * 5.50;
        } else if (units <= 150) {
            energyCharge = (100 * 5.50) + ((units - 100) * 5.50);
        } else if (units <= 300) {
            energyCharge = (100 * 5.50) + (50 * 5.50) + ((units - 150) * 6.00);
        } else {
            energyCharge = (100 * 5.50) + (50 * 5.50) + (150 * 6.00) + ((units - 300) * 6.50);
        }

        double fixedCharge = 110 * sanctionedLoadKW;
        double electricityDuty = 0.05 * energyCharge;
        double cgst = 0.09 * energyCharge;
        double sgst = 0.09 * energyCharge;
        double meterRent = 10.00;
        double subsidy = 20.00;
        double totalPayable = energyCharge + fixedCharge + electricityDuty + cgst + sgst + meterRent - subsidy;


        File dir = new File("bills");
        if (!dir.exists()) dir.mkdirs();

        String fileName = "bills/" + bill.getCustomer().getMeterNumber() + "_" + bill.getBillingMonth() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("==============================\n");
            writer.write(" UTTAR PRADESH POWER CORPORATION LTD\n");
            writer.write("    ELECTRICITY BILL (DOMESTIC)\n");
            writer.write("==============================\n\n");

            writer.write("Customer Name    : " + bill.getCustomer().getName() + "\n");
            writer.write("Account Number   : " + bill.getCustomer().getAccountNumber() + "\n");
            writer.write("Meter Number     : " + bill.getCustomer().getMeterNumber() + "\n");
            writer.write("Address          : " + bill.getCustomer().getAddress() + "\n");
            writer.write("Mobile No.       : " + bill.getCustomer().getMobileNumber() + "\n");
            writer.write("Email ID         : " + bill.getCustomer().getEmail() + "\n");
            writer.write("Sanctioned Load  : " + bill.getCustomer().getSanctionedLoad() + "\n");
            writer.write("Connection Date  : " + bill.getCustomer().getConnectionDate() + "\n");
            writer.write("Bill Number      : " + bill.getCustomer().getBillNumber() + "\n");
            writer.write("Latitude/Long.   : " + bill.getCustomer().getBillingLatitude() + ", " + bill.getCustomer().getBillingLongitude() + "\n\n");

            writer.write("Billing Month    : " + bill.getBillingMonth() + "\n");
            writer.write("Bill Date        : " + bill.getGeneratedDate() + "\n");
            writer.write("Due Date         : " + bill.getDueDate() + "\n");
            writer.write("Units Consumed   : " + bill.getUnitsConsumed() + "\n\n");

            writer.write("--- BILL BREAKDOWN ---\n");
            writer.write(String.format("Energy Charges    : ₹%s\n", df.format(energyCharge)));
            writer.write(String.format("Fixed Charges     : ₹%s\n", df.format(fixedCharge)));
            writer.write(String.format("Meter Rent        : ₹%s\n", df.format(meterRent)));
            writer.write(String.format("Electricity Duty  : ₹%s\n", df.format(electricityDuty)));
            writer.write(String.format("CGST (9%%)         : ₹%s\n", df.format(cgst)));
            writer.write(String.format("SGST (9%%)         : ₹%s\n", df.format(sgst)));
            writer.write(String.format("Subsidy           : -₹%s\n", df.format(subsidy)));
            writer.write("------------------------------\n");
            writer.write(String.format("TOTAL PAYABLE     : ₹%s\n", df.format(totalPayable)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
