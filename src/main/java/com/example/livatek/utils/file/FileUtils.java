package com.example.livatek.utils.file;

import com.example.livatek.domain.Amount;
import com.example.livatek.domain.Freight;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileUtils {

    public static void write(String path, Amount amount, String finalPrice, Freight freight){
        try(FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.append(String.format("%s amount %s freight %s price %s",
                    new Date(),
                    amount.getAmount().doubleValue(),
                    freight.getTotal().doubleValue(),
                    finalPrice
            ));
            fileWriter.flush();
        } catch (
                IOException e) {
            // TO DO Add logger
            throw new RuntimeException(e);
        }
    }

}
