import java.util.HashMap;
import java.util.HashSet;
class CinemaBooking {

    static class Seat {
        final char row;     // 'A' to 'E'
        final int number;   // 1 to 6

        Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        @Override
        public String toString() {
            return "" + row + number;
        }

        // Step 3: Compare seats by row and number.
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Seat)) {
                return false;
            }
            Seat seat = (Seat) other;
            return row == seat.row && number == seat.number;
        }

        @Override
        public int hashCode() {
            return 31 * row + number;
        }
    }

    public static void main(String[] args) {

        // Step 1: Complete the booking scenario in Parts A–G.
        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

        // Part A: Enter the four initial bookings.
        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");
        System.out.println("Bookings:           " + bookings);

        // Part B: Find the customer who booked C3.
        System.out.println("Who booked C3:      " + bookings.get(new Seat('C', 3)));

        // Part C: Check whether A2 and E6 are taken.
        System.out.println("Is A2 taken:        " + bookings.containsKey(new Seat('A', 2)));
        System.out.println("Is E6 taken:        " + bookings.containsKey(new Seat('E', 6)));

        // Part D: Try to book B5 for Youssef.
        String currentCustomer = bookings.putIfAbsent(new Seat('B', 5), "Youssef");
        if (currentCustomer == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by " + currentCustomer);
        }

        // Part E: Move Ali from A1 to D2.
        String movedCustomer = bookings.remove(new Seat('A', 1));
        if (movedCustomer != null) {
            bookings.put(new Seat('D', 2), movedCustomer);
        }
        System.out.println("After Ali moved:    " + bookings);

        // Part F: Mark E1 and E2 as broken and check Hana's request.
        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));
        if (broken.contains(new Seat('E', 1))) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(new Seat('E', 1), "Hana");
            System.out.println("Hana -> E1:         booked");
        }

        // Part G: Count the bookings.
        System.out.println("Seats booked:       " + bookings.size());

        // Step 4: Compare the output and chart with the sample above.
        System.out.println();
        printChart(bookings, broken);
    }

    // GIVEN - do not change.  Prints the hall:  X = booked   # = broken   . = free
    static void printChart(HashMap<Seat, String> bookings, HashSet<Seat> broken) {
        System.out.println("     1  2  3  4  5  6");
        for (char row = 'A'; row <= 'E'; row++) {
            System.out.print(row + "  ");
            for (int number = 1; number <= 6; number++) {
                Seat seat = new Seat(row, number);
                if (bookings.containsKey(seat)) {
                    System.out.print("  X");
                } else if (broken.contains(seat)) {
                    System.out.print("  #");
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
    }
}

/*
 Step 2 — First run, before adding equals() and hashCode()
 C3 returned null, and both A2 and E6 returned false. B5 was booked twice.
 Ali stayed in A1, and Hana got E1 even though it was marked broken.
 There were six bookings, but the chart showed every seat as free.

 Step 5 — Questions

 Q1. Why override equals() and hashCode()?
     Two separate Seat objects can represent the same chair. equals()
     compares their row and number, and hashCode() gives equal seats
     the same hash so the map and set can find them.

 Q2. What happens if hashCode() is removed?
     In my run, the lookup for C3 returned null, the count went back to
     six, and the chart showed only dots. The inherited hashCode() can
     give two seats with the same row and number different hashes.

 Q3. Why are row and number final?
     Changing either field after insertion could change the seat's hash.
     The map could then look in a different bucket and miss the entry.

 Q4. What happens if the parameter type is Seat?
     With @Override, the code fails to compile. Without it, equals(Seat)
     compiles as an overload. The map and set still call equals(Object),
     so lookups with new Seat objects failed again in my run.

 Bonus — Replace the Seat class with this line and run again.

     record Seat(char row, int number) {}

 The record generates equals() and hashCode() from row and number.
 The seat chart and booking count stay the same. Its generated toString()
 displays keys like Seat[row=B, number=5] in the map.
*/