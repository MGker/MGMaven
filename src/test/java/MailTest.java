import com.mg.Application;
import com.mg.utils.MailSendUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @Auther: fujian
 * @Date: 2018/7/30 09:54
 * @Description:
 */
@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class MailTest {
    @Resource
    private JavaMailSender javaMailSender;
    @Test
    public void sendMail(){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        //发送者
//        mailMessage.setFrom("1329605202@qq.com");
//        mailMessage.setTo("289416090@qq.com");
//        mailMessage.setSubject("Hello2");
//        mailMessage.setText("重复邮件可能会被屏蔽");
//        javaMailSender.send(mailMessage);
        MailSendUtil mailSendUtil = new MailSendUtil(javaMailSender,"1329605202@qq.com","289416090@qq.com");
        StringBuilder sb= new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>网页邮件</title></head>");
        sb.append("<body>");
        sb.append("<h1>网页邮件测试</h1>");
        sb.append("<a href='http://www.4455rr.com'>点击进入</a>");
        sb.append("<a href='http://www.baidu.com'>点击进入baidu</a>");
        sb.append("<img src='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532940538676&di=5b6aeafa239b1ca2d498fb034031e7f8&imgtype=0&src=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201306%2F08%2F20130608151247_SnNUT.jpeg'></img>");
        sb.append("</body>");
        sb.append("</html>");

       // mailSendUtil.sendHtmlMail("网页邮件",sb.toString());
        //mailSendUtil.sendHtmlMail("网页邮件",sb.toString());
        mailSendUtil.sendAttachmentsMail("带附件",sb.toString(),"f:\\read.txt");

        System.out.println("邮件发送完毕");
    }

}
