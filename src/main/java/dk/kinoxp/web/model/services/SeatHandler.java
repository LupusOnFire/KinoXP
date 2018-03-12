package dk.kinoxp.web.model.services;

public class SeatHandler {
    private boolean isReserved;
    private boolean available;


    public SeatHandler(boolean isReserved){
        this.isReserved = isReserved;
    }

    public void bookSeat(){
        if(isReserved){
            available = false;
        }
        else if(!isReserved){
            available = true;
        }
    }
}
