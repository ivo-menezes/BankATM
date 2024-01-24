import java.util.Date;

public class Transaction {

    private final double amount;
    private final Date timestamp;
    private String memo;
    private final Account inAccount; //Account in which transaction was performed

    public Transaction(double amount, Account inAccount) {

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }
    public Transaction(double amount, String memo, Account inAccount) {

        // Call the two-arg constructor first
        this(amount, inAccount);
        this.memo = memo;
    }

    // Get the amount of the transaction
    public double getAmount() {
        return this.amount;
    }

    // Get a String summarizing the transaction
    public String getSummaryLine() {

        if (this.amount>= 0) {
            return String.format("%s : €%.02f : %s", this.timestamp.toString(),
                    this.amount, this.memo);
        } else {
            return String.format("%s : €(%.02f) : %s", this.timestamp.toString(),
                    -this.amount, this.memo);
        }
    }
}
