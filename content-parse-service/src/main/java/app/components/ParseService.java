package app.components;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class ParseService {
    public static ReceiptContent parseContent(String content){
        final String[] contentParts =  content.split("&");
        final String dateTimeString = contentParts[0].substring(2);

        final BigDecimal amount = new BigDecimal(contentParts[1].substring(2));
        return new ReceiptContent(getFormattedDateString(dateTimeString), amount);
    }

    private static String getFormattedDateString(String dateTimeString){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateTimeString);
        stringBuilder.insert(4, '-');
        stringBuilder.insert(7, '-');
        stringBuilder.insert(13, ':');
        return stringBuilder.toString();
    }
}
