package org.rituraj;

public class InvoiceGenerator {
    public InvoiceGenerator(){}
    private static final double COST_PER_KM = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;

    private RideRepository rideRepository;
    public InvoiceGenerator(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }



    public InvoiceSummary getInvoiceByUser(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        return generateInvoice(rides);
    }


    public double calculateFare(double distance, int time) {
        double fare = distance * COST_PER_KM + time * COST_PER_MINUTE;
        return Math.max(fare, MINIMUM_FARE);
    }

    public double calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public InvoiceSummary generateInvoice(Ride[] rides) {
        double totalFare = calculateFare(rides);
        return new InvoiceSummary(rides.length, totalFare);
    }


}

