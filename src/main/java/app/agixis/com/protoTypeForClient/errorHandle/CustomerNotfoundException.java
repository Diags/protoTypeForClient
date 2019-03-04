package app.agixis.com.protoTypeForClient.errorHandle;

import app.agixis.com.protoTypeForClient.model.Customer;

public class CustomerNotfoundException extends RuntimeException {
    public CustomerNotfoundException(Long id) {
        super("Could not find customer id =  " + id);
    }

    public CustomerNotfoundException(Customer customer) {
        super("Could not find customer " + customer);
    }
}
