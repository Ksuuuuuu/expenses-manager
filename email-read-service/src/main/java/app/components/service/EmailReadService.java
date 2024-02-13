package app.components.service;

import org.springframework.stereotype.Service;


import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class EmailReadService {
    public void readEmail() {

        try {
            final Properties authProperties = new Properties();
            final Properties sessionProperties = new Properties();
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("configEmail.properties")) {
                authProperties.load(is);
            }

            sessionProperties.put("mail.store.protocol", "imap");
            sessionProperties.put("mail.imap.ssl.enable", "true");
            sessionProperties.put("mail.imaps.port", 993);
            sessionProperties.put("mail.imaps.sasl.mechanisms", "XOAUTH2");

            //sessionProperties.put("mail.smtp.ssl.enable", "true");
            Store store = Session.getInstance(sessionProperties).getStore();
            store.connect(authProperties.getProperty("email.host"), authProperties.getProperty("email"),  authProperties.getProperty("email.pwd"));
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            System.out.println("Count Message -- "+ inbox.getMessageCount());

//            Message message = inbox.getMessage(inbox.getMessageCount());
//
//            Multipart mp = (Multipart) message.getContent();
//
//            for (int i = 0; i < mp.getCount(); i++) {
//
//                 BodyPart bp = mp.getBodyPart(i);
//
//                 if (bp.getFileName() == null) {
//
//                     System.out.println(" " + i + ". сообщение : '" +
//
//                             bp.getContent() + "'");
//
//                 } else {
//
//                     System.out.println(" " + i + ". файл : '" +
//
//                             bp.getFileName() + "'");
//                 }

 //           }

        } catch (IOException | MessagingException e) {

            e.printStackTrace();

        }

    }

}




