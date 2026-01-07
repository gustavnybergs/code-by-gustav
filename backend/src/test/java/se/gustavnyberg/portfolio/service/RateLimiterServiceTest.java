package se.gustavnyberg.portfolio.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för RateLimiterService.
 * Verifierar att rate limiting fungerar korrekt för att skydda mot spam.
 */
class RateLimiterServiceTest {

    private RateLimiterService rateLimiterService;

    @BeforeEach
    void setUp() {
        rateLimiterService = new RateLimiterService();
    }

    @Test
    @DisplayName("Första request tillåts alltid")
    void isAllowed_FirstRequest_ReturnsTrue() {
        assertTrue(rateLimiterService.isAllowed("192.168.1.1"));
    }

    @Test
    @DisplayName("Fem requests inom tidsfönstret tillåts")
    void isAllowed_FiveRequests_AllAllowed() {
        String ip = "192.168.1.2";
        
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiterService.isAllowed(ip), 
                "Request " + (i + 1) + " borde tillåtas");
        }
    }

    @Test
    @DisplayName("Sjätte request inom tidsfönstret blockeras")
    void isAllowed_SixthRequest_Blocked() {
        String ip = "192.168.1.3";
        
        // Gör 5 tillåtna requests
        for (int i = 0; i < 5; i++) {
            rateLimiterService.isAllowed(ip);
        }
        
        // Sjätte ska blockeras
        assertFalse(rateLimiterService.isAllowed(ip));
    }

    @Test
    @DisplayName("Olika IP-adresser har separata gränser")
    void isAllowed_DifferentIPs_SeparateLimits() {
        String ip1 = "192.168.1.10";
        String ip2 = "192.168.1.20";
        
        // Förbruka alla requests för ip1
        for (int i = 0; i < 5; i++) {
            rateLimiterService.isAllowed(ip1);
        }
        
        // ip2 ska fortfarande kunna göra requests
        assertTrue(rateLimiterService.isAllowed(ip2));
        
        // ip1 ska vara blockerad
        assertFalse(rateLimiterService.isAllowed(ip1));
    }

    @Test
    @DisplayName("Blockerad IP förblir blockerad vid ytterligare försök")
    void isAllowed_BlockedIP_StaysBlocked() {
        String ip = "192.168.1.4";
        
        // Förbruka alla requests
        for (int i = 0; i < 5; i++) {
            rateLimiterService.isAllowed(ip);
        }
        
        // Flera försök ska alla blockeras
        assertFalse(rateLimiterService.isAllowed(ip));
        assertFalse(rateLimiterService.isAllowed(ip));
        assertFalse(rateLimiterService.isAllowed(ip));
    }
}
