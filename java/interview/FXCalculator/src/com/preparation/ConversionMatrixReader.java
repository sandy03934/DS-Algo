package com.preparation;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sandip Singh.s
 */
public class ConversionMatrixReader {

    public void load() throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream("D:\\software_projects\\practize\\interview\\FXCalculator\\src\\com\\preparation\\conversionmatrix.txt"));
        Properties properties = new Properties();
        properties.load(is);

        properties.entrySet().forEach(prop -> {
            ConversionMatrix.getInstance().addConversion((String)prop.getKey(), (String)prop.getValue());
        });
    }
}
