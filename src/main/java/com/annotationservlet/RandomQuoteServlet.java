package com.annotationservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/randomQuote")
public class RandomQuoteServlet extends HttpServlet{
    private static final String RU = "ru";
    private static final String UA = "ua";

    private List<String> ruQuote = new ArrayList<String>();
    private List<String> uaQuote = new ArrayList<String>();


    public RandomQuoteServlet() {
        ruQuote.add("Привет, мир");
        ruQuote.add("поехали");
        ruQuote.add("Мне нужен мир, желательно весь");
        uaQuote.add("Вітаю, світ");
        uaQuote.add("Поїхали");
        uaQuote.add("мені потрібен світ, бажано весь");

    }


    private static String randomQuote(List<String> qoutes) {
        Random random = new Random();
        return qoutes.get(random.nextInt(qoutes.size()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        String result;
        if (lang == null || lang.equals(RU)){
            result = randomQuote(ruQuote);
        }else if (lang.equals(UA)){
            result = randomQuote(uaQuote);
        }else {
            result = "Error";
        }
        req.setAttribute("result", result);
        req.getRequestDispatcher("jsp/quote.jsp").forward(req,resp);




     /*   resp.setContentType("text/html; charset=UTF-8");
        Writer writer = resp.getWriter();
        writer.append("<h3>")
                .append(result)
                .append("</h3>");
*/
    }
}
