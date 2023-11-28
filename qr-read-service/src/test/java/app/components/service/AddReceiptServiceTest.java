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
    public static final String FILE_NAME_TEST = "file";
    public static final String DATE_TIME_TEST = "123";
    public static final BigDecimal AMOUNT = BigDecimal.TEN;
    public static final String CONTENT_TEST = "content";
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
        String content = CONTENT_TEST;
        File file = new File(FILE_NAME_TEST);
        ReceiptContent receiptContent = new ReceiptContent(DATE_TIME_TEST, AMOUNT);

        given(qrReadService.readQRCode(file))
                .willReturn(content);
        given(parseService.parseContent(content))
                .willReturn(receiptContent);
        given(receiptRepository
                .create(new ReceiptEntity(DATE_TIME_TEST, AMOUNT, FILE_NAME_TEST, 1L)))
                .willReturn(1L);

        Status status = addReceiptService
                .save(new ReceiptLoad(1L, FILE_NAME_TEST));

        assertEquals(status, Status.Success);
    }

    @Test
    public void addReceiptFail() throws IOException {
        String content = CONTENT_TEST;
        File file = new File(FILE_NAME_TEST);
        ReceiptContent receiptContent = new ReceiptContent(DATE_TIME_TEST, AMOUNT);

        given(qrReadService.readQRCode(file))
                .willReturn(content);
        given(parseService.parseContent(content))
                .willReturn(receiptContent);
        given(receiptRepository
                .create(new ReceiptEntity(DATE_TIME_TEST, AMOUNT, FILE_NAME_TEST, 1L)))
                .willReturn(null);

        Status status = addReceiptService
                .save(new ReceiptLoad(1L, FILE_NAME_TEST));

        assertEquals(Status.ServerError, status);
    }

}
