package de.jensd.addon.registry;

import de.jensd.addon.AddOnRegistryServiceLoader;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import de.jensd.addon.decoder.PayloadDecoder;

/**
 *
 * @author Jens Deters
 */
public class PayloadConverterRegistryTest {

    public PayloadConverterRegistryTest() {
    }

    @Test
    public void testLoadExtensions() {
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> converters = extensionRegistry.getAddOns(PayloadDecoder.class);
        assertTrue("Expected to find 4 payload decoders", converters.size() == 4);
    }

    @Test
    public void testLoadExtensionsFromJar() {
        String lookupPath = "build/libs/";
        System.setProperty(AddOnRegistryServiceLoader.ADDON_LOOKUP_PATH_PROPERTY_NAME, lookupPath);
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> converter = extensionRegistry.getAddOns(PayloadDecoder.class);

        converter.forEach(c -> {
            System.out.println(String.format("%-30s %-30s %-10s %s", c.id(), c.name(), c.version(), c.description()));
        });
    }
}
