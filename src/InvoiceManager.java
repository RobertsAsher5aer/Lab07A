// InvoiceManager.java
import java.util.ArrayList;
import java.util.List;

public class InvoiceManager {
    private List<Invoice> invoices;

    public InvoiceManager() {
        invoices = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Invoice getInvoice(int index) {
        if (index >= 0 && index < invoices.size()) {
            return invoices.get(index);
        }
        return null;
    }
}
