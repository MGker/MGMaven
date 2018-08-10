import java.util.Timer;
import java.util.TimerTask;

/**
 * @Auther: fujian
 * @Date: 2018/7/25 11:02
 * @Description:
 */
public class Test1 {
    static int n = 0;
    public static void main(String[] args) throws  Exception{
        Integer i = null;
        int j = i;
        System.out.println(j);

    }
    public static void m1(){
        System.out.println("任务运行");
    }
    public static void task()throws  Exception{
        Timer timer = new Timer(true);
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("运行TimerTask:"+(n++));
            }
        };
        timer.schedule(tt,0,1000);
        while (true){
            if(n==3){
                timer.cancel();
                System.out.println("取消定时任务");
                break;
            }
        }
        Thread.currentThread().sleep(10000);
    }
}
