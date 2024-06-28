package exercise;

public class PaymentMetricsTotal extends PaymentMetrics {
    public PaymentMetricsTotal(long window) {
        this.window = window;
    }

    @Override
    public synchronized long post(long timestamp, long amount) {
        txs = cleanUpOldTxs(timestamp);
        txs.add(new Transaction(timestamp, amount));
        return txs.stream()
                .map(Transaction::amount)
                .mapToLong(Long::longValue)
                .sum();
    }

}
