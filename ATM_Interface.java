import java.text.NumberFormat;
import java.util.Scanner;

public class ATM_Interface {

    // Array to hold multiple accounts (up to 100)
    private static Account[] accounts = new Account[100];
    private static int accountCount = 0;

    public static void main(String[] args) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Scanner sc = new Scanner(System.in);
        boolean session = true;

        // Initialize 100 accounts
        for (int i = 0; i < 100; i++) {
            accounts[i] = new Account();
            accounts[i].setType("Account " + (i + 1));
            accounts[i].setBalance(0.00);
            accounts[i].setRate(0.00);
        }

        while (session) {
            System.out.print("\nATM Menu: \n \n"
                    + "1. Deposit Money \n"
                    + "2. Withdraw Money \n"
                    + "3. Transfer Funds \n"
                    + "4. Check Account Balance\n"
                    + "5. End Session\n \n"
                    + "Enter selection: ");

            int selection = sc.nextInt();
            int accountIndex = selectAccount(sc);

            // Check if account index is valid
            if (accountIndex == -1) {
                System.out.println("Invalid account selection. Please try again.");
                continue;
            }

            switch (selection) {
                case 1: // Deposit Money
                    handleDeposit(sc, accountIndex, formatter);
                    break;

                case 2: // Withdraw Money
                    handleWithdraw(sc, accountIndex, formatter);
                    break;

                case 3: // Transfer Funds
                    handleTransfer(sc, accountIndex, formatter);
                    break;

                case 4: // Check Account Balance
                    System.out.println("\n" + accounts[accountIndex].getType() + " Balance: " + formatter.format(accounts[accountIndex].getBalance()) + "\n");
                    break;

                case 5: // End Session
                    session = false;
                    break;

                default:
                    System.out.println("Invalid selection. Please choose a valid option.");
                    break;
            }
        }

        System.out.println("\nThank you for banking with us!\n");
        sc.close();
    }

    private static int selectAccount(Scanner sc) {
        System.out.print("Enter account number (1-100): ");
        int accountNumber = sc.nextInt();
        if (accountNumber < 1 || accountNumber > 100) {
            return -1; // Invalid account number
        }
        return accountNumber - 1; // Adjusting for 0-based index
    }

    private static void handleDeposit(Scanner sc, int accountIndex, NumberFormat formatter) {
        System.out.println("\nYour current balance is: " + formatter.format(accounts[accountIndex].getBalance()));
        System.out.print("How much money would you like to deposit? ");
        double deposit = sc.nextDouble();
        if (deposit > 0) {
            accounts[accountIndex].deposit(deposit);
            System.out.println("\nYour new balance is: " + formatter.format(accounts[accountIndex].getBalance()));
        } else {
            System.out.println("Invalid deposit amount. Please try again.");
        }
    }

    private static void handleWithdraw(Scanner sc, int accountIndex, NumberFormat formatter) {
        System.out.println("\nYour current balance is: " + formatter.format(accounts[accountIndex].getBalance()));
        System.out.print("How much money would you like to withdraw? ");
        double withdraw = sc.nextDouble();
        if (withdraw > 0 && withdraw <= accounts[accountIndex].getBalance()) {
            accounts[accountIndex].withdraw(withdraw);
            System.out.println("\nYour new balance is: " + formatter.format(accounts[accountIndex].getBalance()));
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds. Please try again.");
        }
    }

    private static void handleTransfer(Scanner sc, int accountIndex, NumberFormat formatter) {
        System.out.print("\nTransfer to account number (1-100): ");
        int transferAccountIndex = sc.nextInt() - 1;

        if (transferAccountIndex < 0 || transferAccountIndex >= 100 || transferAccountIndex == accountIndex) {
            System.out.println("Invalid account selection for transfer. Please try again.");
            return;
        }

        System.out.print("How much money would you like to transfer? ");
        double transferAmount = sc.nextDouble();
        if (transferAmount > 0 && transferAmount <= accounts[accountIndex].getBalance()) {
            accounts[accountIndex].withdraw(transferAmount);
            accounts[transferAccountIndex].deposit(transferAmount);
            System.out.println("\nYou successfully transferred " + formatter.format(transferAmount) + " to " + accounts[transferAccountIndex].getType());
            System.out.println("\nYour new balance is: " + formatter.format(accounts[accountIndex].getBalance()));
        } else {
            System.out.println("Invalid transfer amount or insufficient funds. Please try again.");
        }
    }
}

class Account {

    private String type;
    private double balance;
    private double rate;

    void setType(String accType) {
        type = accType;
    }

    void setBalance(double accBal) {
        balance = accBal;
    }

    void setRate(double accRate) {
        rate = accRate;
    }

    void deposit(double dep) {
        balance += dep;
    }

    void withdraw(double wit) {
        balance -= wit;
    }

    String getType() {
        return type;
    }

    double getBalance() {
        return balance;
    }

    double getRate() {
        return rate;
    }
}
