package com.iblog.util;

public class IdWorker {
    private static final long TWEPOCH = 1361753741828L;
    private static final long WORKER_ID_BITS = 4L;
    public static final long MAX_WORKER_ID = 15L;
    private static final long SEQUENCE_BITS = 10L;
    private static final long WORKER_ID_SHIFT = 10L;
    private static final long TIMESTAMP_LEFT_SHIFT = 14L;
    public static final long SEQUENCE_MASK = 1023L;
    private final long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private static IdWorker instance = new IdWorker();

    private IdWorker() {
        this.workerId = 15L;
    }

    private IdWorker(long workerId) {
        if (workerId <= 15L && workerId >= 0L) {
            this.workerId = workerId;
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 15L));
        }
    }
    public static IdWorker getInstance(){
       return instance;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & 1023L;
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = timestamp - 1361753741828L << 14 | this.workerId << 10 | this.sequence;
        return nextId;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
            ;
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
