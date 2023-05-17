package app.components.service;

import app.components.Status;
import app.components.entity.ReceiptContent;
import app.components.entity.ReceiptEntity;
import app.components.entity.ReceiptLoad;
import app.components.repository.AddReceiptRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AddReceiptServiceTest {
    @Mock
    private AddReceiptRepository receiptRepository;
    @Mock
    private QrReadService qrReadService;
    @Mock
    private ParseService parseService;
    @InjectMocks
    private AddReceiptService addReceiptService;

    @Test
    public void addReceiptSuccess() throws IOException {
        String content = "content";
        File file = new File("file");
        ReceiptContent receiptContent = new ReceiptContent("123", BigDecimal.TEN);

        given(qrReadService.readQRCode(file))
                .willReturn(content);
        given(parseService.parseContent(content))
                .willReturn(receiptContent);
        given(receiptRepository.create(new ReceiptEntity()))
                .willReturn(1L);

        Status status = addReceiptService.save(new ReceiptLoad(1L, "file"));

        assertEquals(status, Status.Success);
    }

//    @Test
//    public void addReceiptFail() throws IOException {
//        String content = "content";
//        File file = new File("file");
//        ReceiptContent receiptContent = new ReceiptContent("123", BigDecimal.TEN);
//
//        given(qrReadService.readQRCode(file))
//                .willReturn(content);
//        given(parseService.parseContent(content))
//                .willReturn(receiptContent);
//        given(receiptRepository.create(null))
//                .willReturn(null);
//
//        Status status = addReceiptService.save(new ReceiptLoad(1L, "file"));
//
//        assertEquals(status, Status.ServerError);
//    }

}
