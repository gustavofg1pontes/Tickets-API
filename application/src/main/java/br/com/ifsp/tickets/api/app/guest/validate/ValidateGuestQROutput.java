package br.com.ifsp.tickets.api.app.guest.validate;

public record ValidateGuestQROutput (
    byte[] qrImage,
    String email
) {
    public static ValidateGuestQROutput from(byte[] qrImage, String email){
        return new ValidateGuestQROutput(
                qrImage,
                email
        );
    }

}
