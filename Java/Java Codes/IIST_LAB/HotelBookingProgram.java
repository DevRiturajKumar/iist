/* 
NAME : RITURAJ KUMAR

BRANCH : COMPUTER SCIENCE AND ENGINEERING
SEMESTER: 3RD
YEAR: SECOND
GROUP: CS-3

HTTPS://WWW.RITURAJKUMAR.COM

THIS FILE IS FOR HOTEL BOOKING SYSTEM (EXAMPLE)

GIVEN BY:- AMIT GOUD SIR (LAB CLASS)
 */

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelBookingProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display the welcome greeting
        System.out.println("************************************");
        System.out.println("    Welcome to IIST Hotel");
        System.out.println("************************************");

        // Get number of rooms to book
        System.out.print("\nEnter the number of rooms to book: ");
        int numRooms = scanner.nextInt();

        // Initialize variables for total bill
        double totalRoomBill = 0;
        double totalServiceBill = 0;

        // Process each room booking
        for (int i = 1; i <= numRooms; i++) {
            System.out.println("\nRoom " + i + ":");

            // Get check-in date and time
            System.out.print("Enter check-in date and time (yyyy-MM-dd HH:mm): ");
            String checkInStr = scanner.next() + " " + scanner.nextLine().trim();
            LocalDateTime checkInDateTime = LocalDateTime.parse(checkInStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            // Check if the check-in time is within hotel opening hours
            if (!isValidCheckInTime(checkInDateTime)) {
                System.out.println("Invalid check-in time. The hotel is open from 6 am to 10 pm on weekdays.");
                return;
            }

            // Get check-out date and time
            System.out.print("Enter check-out date and time (yyyy-MM-dd HH:mm): ");
            String checkOutStr = scanner.next() + " " + scanner.nextLine().trim();
            LocalDateTime checkOutDateTime = LocalDateTime.parse(checkOutStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            // Check if the booking duration is within the allowed maximum (15 days)
            if (!isValidBookingDuration(checkInDateTime, checkOutDateTime)) {
                System.out.println("Invalid booking duration. Maximum booking allowed is for 15 days.");
                return;
            }

            // Display room types and prices
            displayRoomOptions();

            // Get room type
            System.out.print("Enter room type (1 for single/2 for double/3 for suite): ");
            int roomTypeChoice = scanner.nextInt();
            String roomType = getRoomTypeFromChoice(roomTypeChoice);

            // Get AC or non-AC
            System.out.print("Choose AC or non-AC (1 for AC/2 for non-AC): ");
            int acChoice = scanner.nextInt();
            String acType = getAcTypeFromChoice(acChoice);

            // Display selected room details
            displaySelectedRoomDetails(roomType, acType);

            // Display the list of additional amenities with prices
            displayAmenitiesWithOptions();

            // Get additional amenities
            System.out.print("Select additional amenities (comma-separated numbers): ");
            String amenitiesInput = scanner.next() + scanner.nextLine().trim();
            String[] selectedAmenities = amenitiesInput.split(",");

            // Get number of guests
            System.out.print("Enter number of guests: ");
            int numGuests = scanner.nextInt();

            // Calculate room cost based on type, AC type, and selected amenities
            double roomCost = calculateRoomCost(roomType, acType, selectedAmenities);

            // Calculate total cost for the room
            double roomTotal = roomCost * numGuests;

            // Display room details and cost in tabular format
            System.out.println("\nRoom Details:");
            System.out.println("+------------------------+");
            System.out.printf("| %-20s |%n", "Room Type: " + roomType);
            System.out.printf("| %-20s |%n", "AC Type: " + acType);
            System.out.printf("| %-20s |%n", "Selected Amenities: " + String.join(", ", selectedAmenities));
            System.out.printf("| %-20s |%n", "Number of Guests: " + numGuests);
            System.out.printf("| %-20s |%n", "Check-in Date and Time: " + checkInDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.printf("| %-20s |%n", "Check-out Date and Time: " + checkOutDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.printf("| %-20s |%n", "Room Cost per night: $" + roomCost);
            System.out.printf("| %-20s |%n", "Total Cost for the Room: $" + roomTotal);
            System.out.println("+------------------------+");

            // Add room total to the overall room bill
            totalRoomBill += roomTotal;
        }

        // Ask for additional services
        System.out.println("\nAdditional Services:");

        // Get breakfast cost
        System.out.print("Do you want breakfast? (yes/no): ");
        if (scanner.next().equalsIgnoreCase("yes")) {
            totalServiceBill += 20.0;
        }

        // Get lunch cost
        System.out.print("Do you want lunch? (yes/no): ");
        if (scanner.next().equalsIgnoreCase("yes")) {
            totalServiceBill += 25.0;
        }

        // Get dinner cost
        System.out.print("Do you want dinner? (yes/no): ");
        if (scanner.next().equalsIgnoreCase("yes")) {
            totalServiceBill += 30.0;
        }

        // Get laundry cost
        System.out.print("Do you want laundry service? (yes/no): ");
        if (scanner.next().equalsIgnoreCase("yes")) {
            totalServiceBill += 15.0;
        }

        // Get room service cost
        System.out.print("Do you want room service? (yes/no): ");
        if (scanner.next().equalsIgnoreCase("yes")) {
            totalServiceBill += 10.0;
        }

        // Close the scanner
        scanner.close();

        // Use system default time for final bill generation
        LocalDateTime systemDefaultTime = LocalDateTime.now();

        // Calculate GST (28% of the total bill)
        double gst = 0.28 * (totalRoomBill + totalServiceBill);

        // Format GST to 5 digits
        String formattedGST = String.format("%.5f", gst);

        // Display the final bill with GST in tabular format
        System.out.println("\nFinal Bill (including 28% GST) - System Default Time:");
        System.out.println("+------------------------+");
        System.out.printf("| %-20s |%n", "Total Room Bill: $" + totalRoomBill);
        System.out.printf("| %-20s |%n", "Total Service Bill: $" + totalServiceBill);
        System.out.printf("| %-20s |%n", "GST (28%): $" + formattedGST);
        System.out.printf("| %-20s |%n", "Total Bill: $" + (totalRoomBill + totalServiceBill + gst));
        System.out.printf("| %-20s |%n", "System Default Time: " + systemDefaultTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("+------------------------+");

        // Thank you message
        System.out.println("\nThank you for using the Hotel Booking System!");
    }

    // Method to display room types and prices
    private static void displayRoomOptions() {
        System.out.println("Room Types and Prices:");
        System.out.println("1. Single Room (AC) - $150 per night");
        System.out.println("2. Double Room (AC) - $200 per night");
        System.out.println("3. Suite (AC) - $250 per night");
        System.out.println("4. Single Room (Non-AC) - $100 per night");
        System.out.println("5. Double Room (Non-AC) - $150 per night");
        System.out.println("6. Suite (Non-AC) - $200 per night");
    }

    // Method to get room type from user's choice
    private static String getRoomTypeFromChoice(int choice) {
        switch (choice) {
            case 1:
                return "Single Room";
            case 2:
                return "Double Room";
            case 3:
                return "Suite";
            case 4:
                return "Single Room";
            case 5:
                return "Double Room";
            case 6:
                return "Suite";
            default:
                return "Invalid Room Type";
        }
    }

    // Method to get AC type from user's choice
    private static String getAcTypeFromChoice(int choice) {
        switch (choice) {
            case 1:
            case 2:
            case 3:
                return "AC";
            case 4:
            case 5:
            case 6:
                return "Non-AC";
            default:
                return "Invalid AC Type";
        }
    }

    // Method to display selected room details
    private static void displaySelectedRoomDetails(String roomType, String acType) {
        System.out.println("\nSelected Room Details:");
        System.out.println("+------------------------+");
        System.out.printf("| %-20s |%n", "Room Type: " + roomType);
        System.out.printf("| %-20s |%n", "AC Type: " + acType);
        System.out.println("+------------------------+");
    }

    // Method to display amenities with options
    private static void displayAmenitiesWithOptions() {
        System.out.println("\nAdditional Amenities with Prices:");
        Map<Integer, String> amenitiesMap = new HashMap<>();
        amenitiesMap.put(1, "Balcony - $20 per night");
        amenitiesMap.put(2, "Kitchenette - $30 per night");
        amenitiesMap.put(3, "Living room - $40 per night");
        amenitiesMap.put(4, "Hot tub - $50 per night");
        amenitiesMap.put(5, "Fireplace - $30 per night");
        amenitiesMap.put(6, "Pet-friendly amenities - $20 per night");

        for (Map.Entry<Integer, String> entry : amenitiesMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    // Method to calculate room cost based on type, AC type, and selected amenities
    private static double calculateRoomCost(String roomType, String acType, String[] selectedAmenities) {
        double baseCost;
        switch (roomType.toLowerCase()) {
            case "single room":
                baseCost = acType.equalsIgnoreCase("ac") ? 150.0 : 100.0;
                break;
            case "double room":
                baseCost = acType.equalsIgnoreCase("ac") ? 200.0 : 150.0;
                break;
            case "suite":
                baseCost = acType.equalsIgnoreCase("ac") ? 250.0 : 200.0;
                break;
            default:
                baseCost = 0.0; // Default to 0 if the room type is invalid
        }

        // Adjust the cost based on selected amenities
        for (String amenity : selectedAmenities) {
            int amenityChoice = Integer.parseInt(amenity.trim());
            switch (amenityChoice) {
                case 1:
                    baseCost += 20.0; // Balcony
                    break;
                case 2:
                    baseCost += 30.0; // Kitchenette
                    break;
                case 3:
                    baseCost += 40.0; // Living room
                    break;
                case 4:
                    baseCost += 50.0; // Hot tub
                    break;
                case 5:
                    baseCost += 30.0; // Fireplace
                    break;
                case 6:
                    baseCost += 20.0; // Pet-friendly amenities
                    break;
                // Add more amenities as needed
            }
        }

        return baseCost;
    }

    // Method to check if the check-in time is within hotel opening hours
    private static boolean isValidCheckInTime(LocalDateTime checkInDateTime) {
        DayOfWeek dayOfWeek = checkInDateTime.getDayOfWeek();
        int hour = checkInDateTime.getHour();

        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && hour >= 6 && hour < 22;
    }

    // Method to check if the booking duration is within the allowed maximum (15 days)
    private static boolean isValidBookingDuration(LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime) {
        return checkInDateTime.plusDays(15).isAfter(checkOutDateTime);
    }
}
