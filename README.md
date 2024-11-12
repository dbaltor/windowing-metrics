# Playing with Windows

At its core, windowing is a technique associated with stream processing (especially stateful stream processing). It’s used to divide a continuous data stream into smaller, finite chunks called streaming windows.

## Requirements
- all metrics are represented by `long`
- the method signature must be `long post(long timestamp, long amount)` where `timestamp` indicates when the transaciton happened and `amount` is the transaction amount; the method returns the calculated metric
- new metrics might be added in the future without requiring changes in the method signature

## Solution
This library is able to calculate different metrics over a set of transactions through an abstract class.  
Different metrics are calculate by different children.

The size of the window in millis is passed to the factory method.  

The calculation is thread-safe.

