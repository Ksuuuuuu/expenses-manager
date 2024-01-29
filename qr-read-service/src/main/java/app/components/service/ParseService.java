package app.components.service;

import app.components.entity.ReceiptContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

@Service
public class ParseService {
    Logger logger = LoggerFactory.getLogger(ParseService.class);
    public  ReceiptContent parseContent(String content){
        final String[] contentParts = (String[]) Arrays.stream(content.split("&")).map(str -> str.substring(2)).toArray();
        final String dateTimeString = contentParts[0];
        final BigDecimal amount = new BigDecimal(contentParts[1]);
        final String fn = contentParts[2];
        final String receiptNumber = contentParts[3];
        logger.info(Arrays.toString(contentParts));
        long checksum = getCRC32Checksum(dateTimeString + amount + fn + receiptNumber);
        return new ReceiptContent(getFormattedDateString(dateTimeString), amount, checksum);
    }

    private  String getFormattedDateString(String dateTimeString){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateTimeString);
        stringBuilder.insert(4, '-');
        stringBuilder.insert(7, '-');
        stringBuilder.insert(13, ':');
        stringBuilder.append(":00");
        return stringBuilder.toString().replace("T", " ");
    }

    public static long getCRC32Checksum(String data) {
        byte[] bytes = data.getBytes();
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }
}
