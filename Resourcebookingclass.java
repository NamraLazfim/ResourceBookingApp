/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.resourcebookingclass;

/**
 *
 * @author Mecha
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Resourcebookingclass {


    private String bookingId;
    private Student student;
    private Resource resource;

    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private BookingStatus status;

    private String purpose;
    private LocalDateTime createdAt;

    // ==========================
    // Constructor
    // ==========================

    public Booking(String bookingId,
    Student student, Resource resource, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, String purpose) 
    {

        this.bookingId = bookingId;
        this.student = student;
        this.resource = resource;

        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;

        this.purpose = purpose;

        this.status = BookingStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // ==========================
    // Getter
    // ==========================

    public String getBookingId() {
        return bookingId;
    }

    public Student getStudent() {
        return student;
    }

    public Resource getResource() {
        return resource;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public String getPurpose() {
        return purpose;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    
    public void confirmBooking() {
        this.status = BookingStatus.CONFIRMED;
    }

    // Cancel booking
    public void cancelBooking() {
        this.status = BookingStatus.CANCELLED;
    }

    // Complete booking
    public void completeBooking() {
        this.status = BookingStatus.COMPLETED;
    }

    // Update booking
    public void updateBooking(LocalDate date,
                              LocalTime start,
                              LocalTime end,
                              String purpose) {

        this.bookingDate = date;
        this.startTime = start;
        this.endTime = end;
        this.purpose = purpose;
    }

    /**
     * Check whether this booking overlaps another booking.
     */
    public boolean overlaps(Booking other) {

        if (!bookingDate.equals(other.bookingDate))
            return false;

        if (!resource.getResourceId()
                .equals(other.getResource().getResourceId()))
            return false;

        return startTime.isBefore(other.endTime)
                && endTime.isAfter(other.startTime);
    }

    /**
     * Duration in hours.
     */
    public long getDurationHours() {

        return java.time.Duration
                .between(startTime, endTime)
                .toHours();
    }

    /**
     * Convert booking to CSV format.
     */
    public String toCSV() {

        return bookingId + ","
                + student.getStudentId() + ","
                + resource.getResourceId() + ","
                + bookingDate + ","
                + startTime + ","
                + endTime + ","
                + status + ","
                + purpose;
    }

    /**
     * Display booking information.
     */
    @Override
    public String toString() {

        return "Booking ID : " + bookingId
                + "\nStudent : " + student.getName()
                + "\nResource : " + resource.getResourceName()
                + "\nDate : " + bookingDate
                + "\nTime : " + startTime + " - " + endTime
                + "\nPurpose : " + purpose
                + "\nStatus : " + status;
    }

    /**
     * equals() based on Booking ID.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Booking))
            return false;

        Booking other = (Booking) obj;

        return Objects.equals(bookingId, other.bookingId);
    }

    /**
     * hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

}





   