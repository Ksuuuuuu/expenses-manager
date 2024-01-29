package app.components.service;

import app.components.Status;
import app.components.entity.ReceiptContent;
import app.components.entity.ReceiptEntity;
import app.components.entity.ReceiptLoad;
import app.components.exception.RecordAlreadyExistException;
import app.components.repository.AddReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class AddReceiptService {

    private final AddReceiptRepository receiptRepository;
    private final QrReadService qrReadService;
    private final ParseService parseService;

    @Autowired
    public AddReceiptService(AddReceiptRepository receiptRepository, QrReadService qrReadService, ParseService parseService) {
        this.receiptRepository = receiptRepository;
        this.qrReadService = qrReadService;
        this.parseService = parseService;
    }

    public Status save(ReceiptLoad receiptLoad) throws FileNotFoundException, RecordAlreadyExistException {
        try {
            String path = receiptLoad.getFilePath();
            System.out.println("Path in save " + path);
            ReceiptContent content = parseService.parseContent(qrReadService.readQRCode(new File(path)));
            Long id = receiptRepository.create(new ReceiptEntity(content.getDateTime(),
                    content.getAmount(), path, receiptLoad.getIdUser(), content.getChecksum()));
            if (id != null){
                return Status.Success;
            }
            else{
                return Status.ServerError;
            }

        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
