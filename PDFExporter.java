package friday;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class PDFExporter {

    public void generateBillPDF(Bill bill) {
        try {
            File directory = new File("bills");
            if (!directory.exists()) directory.mkdirs();

            String fileName = "bills/" + bill.getCustomer().getMeterNumber() + "_" + bill.getBillingMonth() + ".pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font labelFont = FontFactory.getFont(FontFactory.HELVETICA, 11);
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);

            Paragraph header = new Paragraph("UTTAR PRADESH POWER CORPORATION LTD", titleFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            Paragraph subHeader = new Paragraph("ELECTRICITY BILL (DOMESTIC)", labelFont);
            subHeader.setAlignment(Element.ALIGN_CENTER);
            subHeader.setSpacingAfter(20);
            document.add(subHeader);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);

            addCell(table, "Customer Name:", bill.getCustomer().getName());
            addCell(table, "Account Number:", bill.getCustomer().getAccountNumber());
            addCell(table, "Meter Number:", bill.getCustomer().getMeterNumber());
            addCell(table, "Address:", bill.getCustomer().getAddress());
            addCell(table, "Mobile Number:", bill.getCustomer().getMobileNumber());
            addCell(table, "Email:", bill.getCustomer().getEmail());
            addCell(table, "Sanctioned Load:", bill.getCustomer().getSanctionedLoad());
            addCell(table, "Connection Date:", bill.getCustomer().getConnectionDate());
            addCell(table, "Bill Number:", bill.getCustomer().getBillNumber());
            addCell(table, "Location (Lat, Long):", bill.getCustomer().getBillingLatitude() + ", " + bill.getCustomer().getBillingLongitude());
            document.add(table);

            PdfPTable billTable = new PdfPTable(2);
            billTable.setWidthPercentage(100);
            billTable.setSpacingBefore(10);
            billTable.setSpacingAfter(10);

            addCell(billTable, "Billing Month:", bill.getBillingMonth());
            addCell(billTable, "Bill Date:", bill.getGeneratedDate());
            addCell(billTable, "Due Date:", bill.getDueDate());
            addCell(billTable, "Units Consumed:", bill.getUnitsConsumed() + " Units");
            document.add(billTable);

            double sanctionedLoadKW = Double.parseDouble(bill.getCustomer().getSanctionedLoad().replaceAll("[^0-9.]", ""));
            int units = bill.getUnitsConsumed();
            DecimalFormat df = new DecimalFormat("0.00");

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

            Paragraph breakdownHeader = new Paragraph("BILL BREAKDOWN", boldFont);
            breakdownHeader.setAlignment(Element.ALIGN_CENTER);
            breakdownHeader.setSpacingBefore(10);
            document.add(breakdownHeader);

            PdfPTable breakdown = new PdfPTable(2);
            breakdown.setWidthPercentage(100);
            breakdown.setSpacingBefore(10);

            addCell(breakdown, "Energy Charges:", "₹" + df.format(energyCharge));
            addCell(breakdown, "Fixed Charges:", "₹" + df.format(fixedCharge));
            addCell(breakdown, "Meter Rent:", "₹" + df.format(meterRent));
            addCell(breakdown, "Electricity Duty (5%):", "₹" + df.format(electricityDuty));
            addCell(breakdown, "CGST (9%):", "₹" + df.format(cgst));
            addCell(breakdown, "SGST (9%):", "₹" + df.format(sgst));
            addCell(breakdown, "Subsidy:", "-₹" + df.format(subsidy));
            addCell(breakdown, "TOTAL PAYABLE:", "₹" + df.format(totalPayable));
            document.add(breakdown);


            Paragraph footer = new Paragraph("Pay via www.uppclonline.com or UPPCL App", labelFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            document.add(footer);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCell(PdfPTable table, String key, String value) {
        PdfPCell cell1 = new PdfPCell(new Phrase(key));
        PdfPCell cell2 = new PdfPCell(new Phrase(value));
        cell1.setBorder(Rectangle.NO_BORDER);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell1.setPadding(5);
        cell2.setPadding(5);
        table.addCell(cell1);
        table.addCell(cell2);
    }

}
