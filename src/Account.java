import java.util.ArrayList;

public class Account {

    private final String name; // Account name
    private final String uuid; // Account ID number
    private final User holder; // User object that owns this account
    private final ArrayList<Transaction> transactions; // List of transactions in this account

    public Account(String name, User holder, Bank theBank) {
        // Set account name and holder
        this.name = name;
        this.holder = holder;
        this.uuid = theBank.getNewAccountUUID(); // get new account UUID
        this.transactions = new ArrayList<Transaction>(); //init transactions
    }
    // Get account ID
    public String getUUID() {
        return this.uuid;
    }

    // Get summary line for the account
    public String getSummaryLine() {

        // get the account's balance
        double balance = this.getBalance();

        // format the summary line depending on whether the balance is negative
        if (balance >= 0) {
            return String.format("%s : €%.02f : %s", this.uuid, balance,
                    this.name);
        } else {
            return String.format("%s : €(%.02f) : %s", this.uuid, balance,
                    this.name);
        }
    }

    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    // Print transaction history of the account
    public void printTransHistory() {

        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size()-1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    // Add a new transaction in this account
    public void addTransaction(double amount, String memo) {

        // Create new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }

    public User getHolder() {
        return holder;
    }
}
