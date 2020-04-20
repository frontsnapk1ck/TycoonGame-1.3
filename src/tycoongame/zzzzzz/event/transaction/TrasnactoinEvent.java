package tycoongame.zzzzzz.event.transaction;

public class TrasnactoinEvent {

    private double transferAmt;
    private String message;

    public TrasnactoinEvent(double transferAmt, String message)
    {
        this.transferAmt = transferAmt;
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the transferAmt
     */
    public double getTransferAmt() {
        return transferAmt;
    }

}