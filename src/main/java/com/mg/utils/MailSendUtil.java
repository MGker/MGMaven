package com.mg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Auther: fujian
 * @Date: 2018/7/30 11:00
 * @Description:发送邮件的工具类
 */
public class MailSendUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private JavaMailSender javaMailSender;
    private String from;
    private String to;

    public MailSendUtil(JavaMailSender javaMailSender,String from,String to){
        this.to = to;
        this.from = from;
        this.javaMailSender = javaMailSender;
    }

    /**
     * 修改收件人邮箱
     * @param to
     */
    public void changeTo(String to){
        this.to = to;
//        mailMessage.setTo(to);
    }

    /**
     * 发送普通文本邮件
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String subject,String content){
        SimpleMailMessage simpleMess = new SimpleMailMessage();
        simpleMess.setFrom(from);
        simpleMess.setTo(to);
        simpleMess.setSubject(subject);
        simpleMess.setText(content);
        try {
            javaMailSender.send(simpleMess);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

    public void sendHtmlMail(String subject,String content){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            //true表示需要创建一个multipart message
            MimeMessageHelper mimeMessHelper = new MimeMessageHelper(message, true);
            mimeMessHelper.setFrom(from);
            mimeMessHelper.setTo(to);
            mimeMessHelper.setSubject(subject);
            mimeMessHelper.setText(content, true);
            javaMailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (Exception e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 发送带有附件的邮件(若filePath为空则相当于发送Html邮件)
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String subject,String content,String filePath){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            if(filePath!=null&&!filePath.trim().equals("")){
                File file = new File(filePath);
                FileSystemResource fileResource = new FileSystemResource(file);
                String fileName = file.getName();
                helper.addAttachment(fileName, fileResource);
            }
            javaMailSender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }
}
