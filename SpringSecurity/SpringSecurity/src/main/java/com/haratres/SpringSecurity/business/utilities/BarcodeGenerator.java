package com.haratres.SpringSecurity.business.utilities;

import java.util.UUID;

public class BarcodeGenerator {

    public static String barcodeGenerator() {

        UUID uuid = UUID.randomUUID();

        String uuidString = uuid.toString();

        return   uuidString.substring(0, 6);



    }

}
