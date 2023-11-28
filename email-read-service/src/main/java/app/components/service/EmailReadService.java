package app.components.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import java.io.FileInputStream;
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
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("sessionEmail.properties")) {
                sessionProperties.load(is);
            }


            Session session = Session.getDefaultInstance(sessionProperties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            (String) authProperties.get("email"),
                            (String) authProperties.get("pwd")
                    );
                }
            });

           // Session session = Session.getDefaultInstance(sessionProperties);

            Store store = session.getStore();

            store.connect(authProperties.getProperty("imap.host"), authProperties.getProperty("email"),  authProperties.getProperty("pwd"));

            Folder inbox = store.getFolder("INBOX");

            inbox.open(Folder.READ_ONLY);
            System.out.println(inbox.getMessageCount());

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




