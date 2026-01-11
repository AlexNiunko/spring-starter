package by.epam.spring.listener;

import by.epam.spring.listener.entity.EntityEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @Order
    @EventListener(condition = "#root.args[0].accessType.name()=='READ'")
    public void acceptEntity(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }
}
