package org.artrev.workshop.junit4.stack.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

public class ExternalResourceRuleTest {
    private final ExternalService service = new ExternalService();

    @Rule
    public final ExternalResource externalService = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            // this does not have to be a thing related to web, internet
            // external resource might be DB, file, a special device
            service.connect();
        }

        @Override
        protected void after() {
            service.disconnect();
        }
    };

    @Test
    public void testFoo() {
        /*
        Before each test a connection to the service will be created
        After each test a connection to the service will be closed
        Super important for any resource that needs to be released
         */
    }
}

class ExternalService {
    public void connect() {
    }
    public void disconnect() {
    }
}
