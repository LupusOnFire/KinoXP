package dk.kinoxp.web.model.services.dto;

public class BookingDto {
    private int showingId;
    private String telephone;
    private boolean paid;
    private String bookedSeats;

    public BookingDto() {
    }

    public BookingDto(int showingId, String telephone, boolean paid, String bookedSeats) {
        this.showingId = showingId;
        this.telephone = telephone;
        this.paid = paid;
        this.bookedSeats = bookedSeats;
    }

    public int getShowingId() {
        return showingId;
    }

    public String getTelephone() {
        return telephone;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getBookedSeats() {
        return bookedSeats;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setBookedSeats(String bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
