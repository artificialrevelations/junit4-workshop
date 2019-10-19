package org.artrev.workshop.junit4.stack.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;

public class RuleChainTest {
    /*
      If we have a case where one rule depends on another:
      - it needs to use it explicitly
      - it cannot be run before or after another
      We can use a RuleChain
     */
    private final TemporaryFolder folderRule = new TemporaryFolder();
    private final ServiceThatNeedsDirectory serviceRule =
            new ServiceThatNeedsDirectory(
                    folderRule,
                    "karieraIT.config"
            );

    @Rule
    public RuleChain chain =
            RuleChain.outerRule(folderRule)
                    .around(serviceRule);
                  //.around(otherRule) ...

    @Test
    public void testsTheConfiguredService() throws IOException {
        // ...
    }
}

class ServiceThatNeedsDirectory implements TestRule {
    public ServiceThatNeedsDirectory(final TemporaryFolder folderRule,
                                     final String fileName) {
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return base;
    }
}

