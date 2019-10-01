package com.gmail.askvorchevski.controller;

import com.gmail.askvorchevski.service.AccountService;
import com.gmail.askvorchevski.service.DataBaseService;
import com.gmail.askvorchevski.service.UserService;
import com.gmail.askvorchevski.service.impl.AccountServiceImpl;
import com.gmail.askvorchevski.service.impl.DataBaseServiceImpl;
import com.gmail.askvorchevski.service.impl.UserServiceImpl;
import com.gmail.askvorchevski.service.model.AccountDTO;
import com.gmail.askvorchevski.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UserServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserServlet.class);
    private UserService userService = UserServiceImpl.getInstance();
    private AccountService accountService = AccountServiceImpl.getInstance();
    private DataBaseService dataBaseService = DataBaseServiceImpl.getInstance();

    @Override
    public void init() throws ServletException {
        logger.info("Init servlet start");
        dataBaseService.createDataBase();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AccountDTO> accounts = accountService.findAllAccounts();
        req.setAttribute("accounts", accounts);
        AccountDTO richUser = accounts.stream()
                .max((o1, o2) -> o1.getAccount().compareTo(o2.getAccount()))
                .get();
        req.setAttribute("rich_user", richUser);
        int sum = accounts.stream()
                .mapToInt(AccountDTO::getAccount)
                .sum();
        req.setAttribute("sum", sum);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/user.jsp").forward(req, resp);
        logger.info("DoGet method was comlpeted");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (!id.equals("") & Integer.parseInt(id) != 0) {
            UserDTO user = userService.getUserDTObyId(Integer.parseInt(id));
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/pages/user.jsp").forward(req, resp);
            logger.info("DoPost method was comlpeted");
        } else resp.sendRedirect("http://localhost:8080/user");
    }
}
