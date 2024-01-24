import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private final String name;
    private final ArrayList<User> users;
    private final ArrayList<Account> accounts;

    // Create a new Bank object with empty lists of users and accounts
    public Bank(String name) {

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    // generate a new UUID for a user
    public String getNewUserUUID() {

    String uuid;
    Random rng = new Random();
    int len = 6;
    boolean nonUnique;

    // continue looping until we get a unique ID
    do {
    //generate the number
        uuid = "";
        for (int c = 0; c < len; c++) {
            uuid += ((Integer)rng.nextInt(10)).toString();
        }
        // check to sure is unique
        nonUnique = false;
        for (User u : this.users) {
            if (uuid.compareTo(u.getUUID()) == 0) {
                nonUnique = true;
                break;
            }
        }
    } while (nonUnique);

    return uuid;

    }
    public String getNewAccountUUID() {

        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            //generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            // check to sure is unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    public User addUser(String firstName, String lastName, String pin) {
        // Create a new user object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account for the user and add to User and BAnk accounts lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }
    public User userLogin(String userID, String pin) {

        // search through list of users
        for (User u : this.users) {

            // Check user ID  is correct

            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}
