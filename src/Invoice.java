// Invoice.java
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<LineItem> lineItems;
    private Customer customer;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public double calculateTotalAmountDue() {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }

    public String getFormattedInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("INVOICE\n\n");
        sb.append(customer.getName()).append("\n");
        sb.append(customer.getAddress()).append("\n\n");

        sb.append(String.format("%-15s %-5s %-10s %-10s%n", "Item", "Qty", "Price", "Total"));
        sb.append("-----------------------------------------\n");

        for (LineItem item : lineItems) {
            sb.append(String.format("%-15s %-5d $%-10.2f $%-10.2f%n",
                    item.getName(), item.getQuantity(), item.getUnitPrice(), item.getTotal()));
        }

        sb.append("-----------------------------------------\n");
        sb.append(String.format("AMOUNT DUE: $%.2f%n", calculateTotalAmountDue()));
        return sb.toString();
    }
}
