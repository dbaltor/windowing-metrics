package exercise;

public class PaymentMetricsAverage extends PaymentMetrics {
    public PaymentMetricsAverage(long window) {
        this.window = window;
    }

    @Override
    public synchronized long post(long timestamp, long amount) {
        removeExpiredTxs(timestamp);
        transactions.add(new Transaction(timestamp, amount));
        return (long) transactions.stream()
                .map(Transaction::amount)
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }
}
