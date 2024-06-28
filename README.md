# Playing with Windows

At its core, windowing is a technique associated with stream processing (especially stateful stream processing). It’s used to divide a continuous data stream into smaller, finite chunks called streaming windows. 

This library is able to calculate different metrics through a abstract class.  
Different metrics are calculate by different children always using the same method signature:  
`long post(long timestamp, long amount)`

The length of the window in millis is passed to the factory method.  
Only posts within the defined window are kept in memory.

The calculation is thread-safe.
