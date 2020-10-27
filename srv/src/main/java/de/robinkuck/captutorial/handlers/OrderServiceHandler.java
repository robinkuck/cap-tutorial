package de.robinkuck.captutorial.handlers;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cds.gen.orderservice.CountOpenOrdersContext;
import cds.gen.orderservice.OrderService_;
import cds.gen.orderservice.Order_;

@Component
@ServiceName(OrderService_.CDS_NAME)
public class OrderServiceHandler implements EventHandler {

    @Autowired
    private PersistenceService db;

    @On(event = "countOpenOrders")
    public void onCountOpenOrders(CountOpenOrdersContext context) {
        CqnSelect select = Select.from(Order_.class).where(order -> order.isOpen().eq(true));

        Long result = db.run(select).rowCount();
        context.setResult(result.intValue());
    }
    
}