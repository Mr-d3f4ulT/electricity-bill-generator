package friday;

public class Bill {
    private Customer customer;
    private int totalUnitsConsumed;
    private String billingMonth;
    private String generatedDate;
    private String dueDate;
    private double totalAmount;

    public Bill(Customer customer, int totalUnitsConsumed, String billingMonth,String generatedDate, String dueDate, double totalAmount) {
        this.billingMonth = billingMonth;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.generatedDate = generatedDate;
        this.dueDate = dueDate;
        this.totalUnitsConsumed = totalUnitsConsumed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getUnitsConsumed() {
        return totalUnitsConsumed;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }

    public String getDueDate() {
        return dueDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    public static void main(String[] args) {

    }
}
