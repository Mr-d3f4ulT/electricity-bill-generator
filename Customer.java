package friday;
import java.util.*;
public class Customer {
    private String name;
    private String meterNumber;
    private String address;
    private String accountNumber;
    private String connectionDate;
    private String sanctionedLoad;

    private String mobileNumber;
    private String email;
    private String billNumber;
    private String billingLatitude;
    private String billingLongitude;

    public Customer(String name, String meterNumber, String address, String accountNumber,
                    String connectionDate, String sanctionedLoad, String mobileNumber, String email) {
        this.name = name;
        this.meterNumber = meterNumber;
        this.address = address;
        this.accountNumber = accountNumber;
        this.connectionDate = connectionDate;
        this.sanctionedLoad = sanctionedLoad;
        this.mobileNumber = mobileNumber;
        this.email = email;

        // Generate a random 6+ digit bill number
        this.billNumber = String.valueOf(100000 + new Random().nextInt(900000));

        // Generate random lat-long coordinates within plausible India ranges
        double lat = 24 + (new Random().nextDouble() * 9);   // 24 to 33
        double lon = 77 + (new Random().nextDouble() * 9);   // 77 to 86
        this.billingLatitude = String.format("%.6f", lat);
        this.billingLongitude = String.format("%.6f", lon);
    }

    // Getters
    public String getName() { return name; }
    public String getMeterNumber() { return meterNumber; }
    public String getAddress() { return address; }
    public String getAccountNumber() { return accountNumber; }
    public String getConnectionDate() { return connectionDate; }
    public String getSanctionedLoad() { return sanctionedLoad; }
    public String getMobileNumber() { return mobileNumber; }
    public String getEmail() { return email; }
    public String getBillNumber() { return billNumber; }
    public String getBillingLatitude() { return billingLatitude; }
    public String getBillingLongitude() { return billingLongitude; }
}

