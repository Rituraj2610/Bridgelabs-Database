package org.rituraj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class InvoiceGeneratorTest {

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double fare = invoiceGenerator.calculateFare(2.0, 5);
        assertEquals(25.0, fare); // 2*10 + 5*1 = 25
    }

    @Test
    public void givenMultipleRides_shouldReturnAggregateFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1) // Should trigger minimum fare
        };
        double fare = invoiceGenerator.calculateFare(rides);
        assertEquals(30.0, fare); // 25 + 5 = 30
    }
}

