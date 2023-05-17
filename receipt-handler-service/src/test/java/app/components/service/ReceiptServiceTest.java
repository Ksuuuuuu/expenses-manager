package app.components.service;

import app.components.Status;
import app.components.entity.Receipt;
import app.components.exception.NotFoundParameterException;
import app.components.repository.ReceiptsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptServiceTest {
    @Mock
    ReceiptsRepo receiptsRepo;
    @InjectMocks
    ReceiptService receiptService;

    Long idUser = 1L;

    @Test
    public void getAllTestSuccess() throws NotFoundParameterException {
        List<Receipt> list = new ArrayList<>();
        given(receiptsRepo.getReceipts(idUser))
                .willReturn(list);

        List<Receipt> actual = receiptService.getAll(idUser);

        assertEquals(actual, list);
    }

    @Test
    public void getAllTestFail() {
        assertThrows(NotFoundParameterException.class, () -> receiptService.getAll(null));
    }

    @Test
    public void getByDateSuccess() throws NotFoundParameterException {
        LocalDate date = LocalDate.now();
        List<Receipt> list = new ArrayList<>();
        given(receiptsRepo.findReceiptsByDate(idUser, date))
                .willReturn(list);

        List<Receipt> actual = receiptService.getByDate(idUser, date);

        assertEquals(actual, list);
    }

    @Test
    public void getByDateFail() {
        assertThrows(NotFoundParameterException.class, () -> receiptService.getByDate(idUser, null));
    }

    @Test
    public void deleteByIdTestSuccess() throws NotFoundParameterException {
        given(receiptsRepo.deleteById(anyLong()))
                .willReturn(1);

        assertEquals(Status.Success, receiptService.delete(anyLong()));
    }

    @Test
    public void deleteByIdTestThrow() {
        assertThrows(NotFoundParameterException.class, () -> receiptService.delete(null));
    }
}
