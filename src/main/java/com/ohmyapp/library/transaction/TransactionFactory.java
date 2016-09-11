package com.ohmyapp.library.transaction;

import com.ohmyapp.library.transaction.service.TransactionService;
import com.ohmyapp.library.transaction.service.TransactionServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Emerald on 8/28/2016.
 *
 */
public class TransactionFactory {
    public static final Boolean TRANSACTION_REQUIRED = Boolean.TRUE;
    public static final Boolean TRANSACTION_NOT_REQUIRED = Boolean.FALSE;

    private static final ThreadLocal<Session> sessionThread = new ThreadLocal<>();
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final TransactionService transactionService = new TransactionServiceImpl();
    /**
     * start or join transaction
     *
     * @return  session
     */
    public static Session getSession(boolean transactionRequired) {
        Session session = sessionThread.get();
        if (session == null) {
            session = sessionFactory.openSession();
            if (transactionRequired) {
                session.beginTransaction();
            }
            sessionThread.set(session);
        }
        return session;
    }

    /**
     * commit transaction
     */
    public static void commit() {
        Session session = sessionThread.get();
        if (session == null) {
            return;
        }
        if (session.getTransaction().isActive()) {
            session.getTransaction().commit();
        }
        sessionThread.remove();
    }

    public static TransactionService getTransactionService() {
        return transactionService;
    }
}
