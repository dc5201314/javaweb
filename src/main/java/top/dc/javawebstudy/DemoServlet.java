package top.dc.javawebstudy;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/demo")

public class DemoServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回一个网页

        //返回一段html
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.print("<h2>123</h2>");
//        out.flush();
//        out.close();

        //返回一段js
//        resp.setContentType("application/json;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        String jsonString = """
//       {
//       "name":"dc",
//       "age":20
//        }""";
//        out.print(jsonString);
//        out.flush();
//        out.close();

        //一个简单的验证码
        int len = 4;
        int fontSize = 28;
        int width = len * fontSize +10;
        int height = 50;
        String code = getCode();
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = bufferedImage.getGraphics();

        g.setColor(Color.white);

        for (int i = 0; i < 200 ; i++){
            Random random = new Random();
            int r = random.nextInt(256);
            int h = random.nextInt(256);
            int b = random.nextInt(256);
            g.setColor(new Color(r,h,b));
            int x1 = new Random().nextInt(width);
            int y1 = new Random().nextInt(width);
            int x2 = new Random().nextInt(width);
            int y2 = new Random().nextInt(width);
            g.drawLine(x1, y1, x2, y2);
        }

        g.setColor(Color.RED);
        g.setFont(new Font("宋体",Font.BOLD,fontSize));
        for (int i = 0 ; i < len; i++){
            char c = code.charAt(i);
            g.drawString(c+"",i*fontSize+5,fontSize);
        }
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());
    }


    private String getCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int r = (int)(Math.random()*9000)+1000;
            stringBuilder.append(r);


        }
        return stringBuilder.toString();
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
