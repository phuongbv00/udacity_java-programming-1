package services;

import models.Customer;
import models.IRoom;
import models.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class ReservationService {
    private static final Collection<IRoom> rooms = new ArrayList<>();
    private static final Collection<Reservation> reservations = new ArrayList<>();

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String id) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Reservation reserveARoom(Customer customer,
                                    IRoom room,
                                    Date checkInDate,
                                    Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return reservations.stream()
                .filter(r -> r.getCheckInDate().equals(checkInDate))
                .filter(r -> r.getCheckOutDate().equals(checkOutDate))
                .map(Reservation::getRoom)
                .collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomerReservations(Customer customer) {
        return reservations.stream()
                .filter(r -> r.getCustomer().getEmail().equals(customer.getEmail()))
                .collect(Collectors.toList());
    }

    public void printAllReservations() {
        reservations.forEach(System.out::println);
    }
}
