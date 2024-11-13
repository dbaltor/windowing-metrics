package exercise;

public class PaymentMetricsTotal extends PaymentMetrics {
    public PaymentMetricsTotal(long window) {
        this.window = window;
    }

    @Override
    public synchronized long post(long timestamp, long amount) {
        removeExpiredTxs(timestamp);
        transactions.add(new Transaction(timestamp, amount));
        return transactions.stream()
                .map(Transaction::amount)
                .mapToLong(Long::longValue)
                .sum();
    }

}
