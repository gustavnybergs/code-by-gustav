package se.gustavnyberg.portfolio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Enkel rate limiter för att skydda mot spam och missbruk.
 * Begränsar antal requests per IP-adress under ett tidsintervall.
 */
@Service
public class RateLimiterService {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterService.class);
    
    // Max 5 requests per minut per IP
    private static final int MAX_REQUESTS = 5;
    private static final long TIME_WINDOW_MS = 60_000;
    
    // Lagrar tidsstämplar för varje IP
    private final Map<String, Queue<Long>> requestLog = new ConcurrentHashMap<>();

    /**
     * Kollar om en IP-adress får göra fler requests.
     * Rensar gamla tidsstämplar och räknar aktiva.
     */
    public boolean isAllowed(String ipAddress) {
        long now = System.currentTimeMillis();
        
        // Hämta eller skapa kö för denna IP
        Queue<Long> timestamps = requestLog.computeIfAbsent(ipAddress, k -> new ConcurrentLinkedQueue<>());
        
        // Rensa tidsstämplar äldre än tidsfönstret
        while (!timestamps.isEmpty() && timestamps.peek() < now - TIME_WINDOW_MS) {
            timestamps.poll();
        }
        
        // Kolla om under gränsen
        if (timestamps.size() < MAX_REQUESTS) {
            timestamps.add(now);
            return true;
        }
        
        log.warn("Rate limit överskriden för IP: {}", ipAddress);
        return false;
    }
}
