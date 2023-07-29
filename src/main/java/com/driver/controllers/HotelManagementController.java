package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.services.BookingService;
import com.driver.services.HotelService;
import com.driver.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotel")
public class HotelManagementController {

    private final HotelService hotelService;
    private final BookingService bookingService;
    private final UserService userService;

    public HotelManagementController() {
        this.hotelService = new HotelService();
        this.bookingService = new BookingService();
        this.userService = new UserService();
    }

    @PostMapping("/add-hotel")
    public String addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @PostMapping("/add-user")
    public Integer addUser(@RequestBody User user) {
        userService.addUser(user);
        return user.getaadharCardNo();
    }

    @GetMapping("/get-hotel-with-most-facilities")
    public String getHotelWithMostFacilities() {
        return hotelService.getHotelWithMostFacilities();
    }

    @PostMapping("/book-a-room")
    public int bookARoom(@RequestBody Booking booking) {
        Hotel hotel = hotelService.getHotelByName(booking.getHotelName());
        return bookingService.bookARoom(booking, hotel);
    }

    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public int getBookings(@PathVariable("aadharCard") int aadharCard) {
        return bookingService.getBookingsByPerson(aadharCard);
    }

    @PutMapping("/update-facilities")
    public Hotel updateFacilities(@RequestBody List<Facility> newFacilities, String hotelName) {
        return hotelService.updateFacilities(newFacilities, hotelName);
    }
}