package exercise;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class PaymentMetrics {
    public enum MetricType {
        TOTAL,
        COUNT,
        AVERAGE
    }
    public abstract long post(long timestamp, long amount);

    record Transaction(long time, long amount){};
    List<Transaction> txs = new ArrayList<>();
    long window;

    List<Transaction> cleanUpOldTxs(long timestamp) {
        return txs.stream()
                .filter(tx -> timestamp - tx.time < window)
                .collect(toList());
    }

    public static PaymentMetrics createMetrics(MetricType type) {
       return createMetrics(type, 10000);
    }

    public static PaymentMetrics createMetrics(MetricType type, long window) {
        return switch(type) {
            case TOTAL -> new PaymentMetricsTotal(window);
            case COUNT -> new PaymentMetricsCount(window);
            case AVERAGE -> new PaymentMetricsAverage(window);
        };
    }
}
