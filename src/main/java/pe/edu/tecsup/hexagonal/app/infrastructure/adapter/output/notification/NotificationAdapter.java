package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.notification;

import org.springframework.stereotype.Component;
import pe.edu.tecsup.hexagonal.app.application.port.output.NotificationPort;

@Component
public class NotificationAdapter implements NotificationPort {
    @Override
    public void notifyTransfer(String message) {
        System.out.println("=================================");
        System.out.println("TRANSFER NOTIFICATION");
        System.out.println(message);
        System.out.println("=================================");
    }
}
