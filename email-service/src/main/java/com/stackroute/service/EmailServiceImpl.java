package com.stackroute.service;

import com.stackroute.entity.CustomEmail;
import com.stackroute.entity.EmailResponse;
import com.stackroute.entity.PurchaseDetails;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import freemarker.template.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.springframework.ui.freemarker.FreeMarkerTemplateUtils.processTemplateIntoString;

@Service
public class EmailServiceImpl implements EmailService{

    List<String> allUsersEmailId=new ArrayList<>();

    @Value("${spring.mail.username}")
    private String adminEmailId;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Configuration config;

    Log log = LogFactory.getLog(EmailServiceImpl.class);


    /*
     * @Description : this method is used to sending product offers to all users
     * @Created by : Parshuram Sail
     */

    @Override
    public EmailResponse sendOfferEmail() {
        MimeMessage message = emailSender.createMimeMessage();
        EmailResponse emailMessage = new EmailResponse();
        try
        {
            List<String> allEmailId= allUsersEmailId;
            for (String s : allEmailId) {
                Map<String, Object> model = new HashMap<>();
                model.put("toAddr", s);
                // set mediaType
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
                Template t = config.getTemplate("offer-email.flth");
                String html = processTemplateIntoString(t, model);
                helper.setTo(s);
                helper.setText(html, true);
                helper.setSubject("Check out these offers only for you");
                helper.setFrom(adminEmailId);
                emailSender.send(message);
                emailMessage.setMessage("Email sent to : " + s);
                log.info("Email sent successfully");
                emailMessage.setStatus(Boolean.TRUE);
            }

        }
        catch(MessagingException | IOException | TemplateException e)
        {
            emailMessage.setMessage("Email sending failure : " + e.getMessage());
            emailMessage.setStatus(Boolean.FALSE);
        }
        return emailMessage;
    }

    /*
     * @Description : this method is used to send email after product added by the user
     * @Created by : Parshuram Sail
     */
    @Override
    public Boolean addedNewProductEmail(PurchaseDetails purchase) {
        MimeMessage message = emailSender.createMimeMessage();
        EmailResponse emailMessage = new EmailResponse();
        try {
            List<String> allEmailId= allUsersEmailId;
//            allEmailId.add("shuvadipmoulick109@gmail.com");
//            allEmailId.add("maheshwaribhat2298@gmail.com");
//            allEmailId.add("parshusail416@gmail.com");
//            allEmailId.add("gdevgude75@gmail.com");

            for (String s : allEmailId) {
                Map<String, Object> model = new HashMap<>();
//                model.put("username1", s);
                model.put("product",purchase.getProduct());
               // model.put("details",purchase.getPlans());
               // model.put("price",purchase.getPrice());
                // set mediaType
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
                Template t = config.getTemplate("new-product-added.flth");
                String html = processTemplateIntoString(t, model);
                helper.setTo(s);
                helper.setText(html, true);
                helper.setSubject("New product added");
                helper.setFrom(adminEmailId);
                emailSender.send(message);
                emailMessage.setMessage("Email sent to : " + s);
                log.info("Email sent successfully");
                emailMessage.setStatus(Boolean.TRUE);
            }
            return true;

        }
        catch(MessagingException | IOException | TemplateException e)
        {
            emailMessage.setMessage("Email sending failure : " + e.getMessage());
            emailMessage.setStatus(Boolean.FALSE);
        }
        return false;
    }

    /*
     * @Description : this method is used to send email after succesfull purchase of product
     * @Created by : Parshuram Sail
     */
    @Override
    public Boolean purchaseProductEmail(CustomEmail email, PurchaseDetails purchase) {
        Map<String, Object> model = new HashMap<>();
        model.put("username", email.getUserName());
        model.put("product",purchase.getProduct());
        model.put("details",purchase.getDetails());
        model.put("price",purchase.getPrice());
        String reciverEmail = email.getReceiver();
        MimeMessage message = emailSender.createMimeMessage();
        try
        {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Template t = config.getTemplate("product-purchase.flth");
            String html = processTemplateIntoString(t, model);
            helper.setTo(reciverEmail);
            helper.setText(html, true);
            helper.setSubject("Product purchased successfully");
            helper.setFrom(adminEmailId);
            emailSender.send(message);
            return  true;
        }
        catch(MessagingException | IOException | TemplateException e)
        {
            return false;
        }
    }


    /*
     * @Description : this method is used to sending registration complete emil to users
     * @Created by : Parshuram Sail
     */
    @Override
    public Boolean registrationCompleteEmail(CustomEmail email) {
        Map<String, Object> model = new HashMap<>();
        model.put("username", email.getUserName());
        String reciverEmail = email.getReceiver();
        allUsersEmailId.add(reciverEmail);

        MimeMessage message = emailSender.createMimeMessage();
        try
        {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Template t = config.getTemplate("registration-successfull.flth");
            String html = processTemplateIntoString(t, model);
            helper.setTo(reciverEmail);
            helper.setText(html, true);
            helper.setSubject("Welcome to Connect India");
            helper.setFrom(adminEmailId);
            emailSender.send(message);
            return  true;
        }
        catch(MessagingException | IOException | TemplateException e)
        {
            return false;
        }
    }
}

