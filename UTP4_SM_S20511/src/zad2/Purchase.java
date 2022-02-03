/**
 *
 *  @author Smilianets Mykhailo S20511
 *
 */

package zad2;


public class Purchase {
    String idClient;
    String secondName;
    String name;
    String productName;
    double cost;
    double amount;
    double expense;

    public Purchase(String idClient, String secondName, String name, String productName, double cost, double amount, double expense) {
        this.idClient = idClient;
        this.secondName = secondName;
        this.name = name;
        this.productName = productName;
        this.cost = cost;
        this.amount = amount;
        this.expense = expense;
    }

    @Override
    public String toString() {
        return idClient + ";" + secondName + " " + name + ";" + productName + ";" + cost + ";" + amount;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getName() {
        return name;
    }

    public String getProductName() {
        return productName;
    }

    public double getCost() {
        return cost;
    }

    public double getAmount() {
        return amount;
    }

    public double getExpense() {
        return expense;
    }
}
