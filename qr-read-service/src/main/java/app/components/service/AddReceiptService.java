package app.components.service;

import app.components.entity.ReceiptContent;
import app.components.entity.ReceiptEntity;
import app.components.entity.ReceiptLoad;
import app.components.repository.AddReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class AddReceiptService {
    final AddReceiptRepository receiptRepository;
    final QrReadService qrReadService;

    @Autowired
    public AddReceiptService(AddReceiptRepository receiptRepository, QrReadService qrReadService) {
        this.receiptRepository = receiptRepository;
        this.qrReadService = qrReadService;
    }

    public void save(ReceiptLoad receiptLoad) throws FileNotFoundException {
        try {
            String path = receiptLoad.getFilePath();
            ReceiptContent content = ParseService.parseContent(qrReadService.readQRCode(new File(path)));
            receiptRepository.create(new ReceiptEntity(content.getDateTime(), content.getAmount(), path, receiptLoad.getIdUser()));
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
