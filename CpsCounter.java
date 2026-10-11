
package com.pojavxdrayx.client;

import java.util.ArrayDeque;
import java.util.Deque;

public final class CpsCounter {

    private static final long WINDOW_MS = 1000L;
    private static final Deque<Long> CLICKS = new ArrayDeque<>();

    private CpsCounter() {}

    public static synchronized void registerClick() {
        long now = System.currentTimeMillis();
        CLICKS.addLast(now);
        removeOldClicks(now);
    }

    public static synchronized int getCps() {
        long now = System.currentTimeMillis();
        removeOldClicks(now);
        return CLICKS.size();
    }

    private static void removeOldClicks(long now) {
        while (!CLICKS.isEmpty()
                && now - CLICKS.peekFirst() >= WINDOW_MS) {
            CLICKS.removeFirst();
        }
    }
}
