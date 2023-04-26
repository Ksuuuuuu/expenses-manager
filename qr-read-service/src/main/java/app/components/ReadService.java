package app.components;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ReadService {

    public String readQRCode(File qrCodeFile) throws IOException {
        try {
            BufferedImage bufferedImage = ImageIO.read(qrCodeFile);
            return readQRCode(bufferedImage);
        } catch (Exception e) {
            throw new IOException("Ошибка чтения файла");
        }
    }

    public String readQRCode(BufferedImage bufferedImage) throws Exception {
        try {
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
            BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
            MultiFormatReader multiFormatReader = new MultiFormatReader();

            Result result = multiFormatReader.decode(binaryBitmap);
            return result.getText();
        } catch (NotFoundException e) {
            throw new Exception("Файл не найден");
        }
    }
}
