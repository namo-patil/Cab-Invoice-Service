package com.bridgelabz.invoiceservice;

public enum RideCategory {
    NORMAL_RIDE(10, 1, 5), PREMIUM_RIDE(15, 2, 20);

    public double costPerKm;
    public int costPerMinute;
    public double minimumFarePerRide;

    RideCategory(double costPerKm, int costPerMinute, double minimumFarePerRide) {
        this.costPerKm = costPerKm;
        this.costPerMinute = costPerMinute;
        this.minimumFarePerRide = minimumFarePerRide;
    }

    public double calculateFare(RideCategory rideCategory, double distance, int time) {
        double totalFare = distance * rideCategory.costPerKm + time * rideCategory.costPerMinute;
        return Math.max(totalFare, rideCategory.minimumFarePerRide);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.rideCategory, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}
