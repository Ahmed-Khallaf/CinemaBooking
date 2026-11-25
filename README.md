# CinemaBooking

CinemaBooking is a Java console program that simulates seat reservations in a cinema with five rows (A–E) and six seats in each row. It runs a preset booking scenario automatically.

The program uses a `HashMap` to match each reserved seat to a customer and a `HashSet` to track broken seats. It checks seat availability, moves a reservation, and rejects requests for seats that are already booked or broken.

## Example output

Omar keeps B5, Ali moves from A1 to D2, and Hana's request for E1 is refused because the seat is broken. The program prints the booking count followed by the seating chart.

```text
Seats booked:       4

     1  2  3  4  5  6
A    .  X  .  .  .  .
B    .  .  .  .  X  .
C    .  .  X  .  .  .
D    .  X  .  .  .  .
E    #  #  .  .  .  .
```

`X` means booked, `#` means broken, and `.` means available.

## Run in IntelliJ IDEA

1. Create a Java project named `CinemaBooking` and select a JDK in the project setup.
2. Work at the Java source file into the project's `src` folder.
3. Open the file, click the green arrow next to `main()`, and select **Run**.
4. View the booking results and seating chart in the **Run** window.
