package exercise;

public class PaymentMetricsCount extends PaymentMetrics {
    public PaymentMetricsCount(long window) {
        this.window = window;
    }

    @Override
    public synchronized long post(long timestamp, long amount) {
        removeExpiredTxs(timestamp);
        transactions.add(new Transaction(timestamp, amount));
        return transactions.size();
    }
}
