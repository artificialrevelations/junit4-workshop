package org.artrev.workshop.junit4.stack.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class TemporaryFolderRuleTest {
    @Rule
    public final TemporaryFolder directory = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        final File file = directory.newFile("karieraIT.txt");
        final File nestedDirectory = directory.newFolder("JUGLodz");
        // files and folders created with this rule can be passed
        // to the tested code
    }
}
