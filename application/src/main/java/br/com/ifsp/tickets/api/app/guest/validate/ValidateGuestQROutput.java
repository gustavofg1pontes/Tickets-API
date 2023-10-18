package br.com.ifsp.tickets.api.app.guest.validate;

public record ValidateGuestQROutput (
    byte[] qrImage,
    String name,
    String email
) {
    public static ValidateGuestQROutput from(byte[] qrImage, String name, String email){
        return new ValidateGuestQROutput(
                qrImage,
                name,
                email
        );
    }

}
