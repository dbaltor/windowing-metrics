package exercise;

import java.util.PriorityQueue;
import java.util.Queue;

public abstract class PaymentMetrics {
    public enum MetricType {
        TOTAL,
        COUNT,
        AVERAGE
    }

    protected record Transaction(long timestamp, long amount) {
    }

    public abstract long post(long timestamp, long amount);


    // Keeping the list ordered makes add complexity O(log n) but allows for faster removal
    protected Queue<Transaction> transactions = new PriorityQueue<>(
            (Transaction tx1, Transaction tx2) -> Long.compare(tx1.timestamp(), tx2.timestamp()));
    protected long window;

    protected void removeExpiredTxs(long timestamp) {
        var iterator = transactions.iterator();
        while (iterator.hasNext()) {
            if (timestamp - iterator.next().timestamp() < window) break; // all items from here on are not expired
            iterator.remove();
        }
    }

    public static PaymentMetrics createMetrics(MetricType type) {
        return createMetrics(type, 10_000);
    }

    public static PaymentMetrics createMetrics(MetricType type, long window) {
        return switch (type) {
            case TOTAL -> new PaymentMetricsTotal(window);
            case COUNT -> new PaymentMetricsCount(window);
            case AVERAGE -> new PaymentMetricsAverage(window);
        };
    }
}
