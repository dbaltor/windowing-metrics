package exercise;

public class PaymentMetricsCount extends PaymentMetrics {
    public PaymentMetricsCount(long window) {
        this.window = window;
    }

    @Override
    public synchronized long post(long timestamp, long amount) {
        txs = cleanUpOldTxs(timestamp);
        txs.add(new Transaction(timestamp, amount));
        return txs.size();
    }
}
