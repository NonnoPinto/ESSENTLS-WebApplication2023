package com.essentls.dao;

import com.essentls.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserEmailConfirmationDAO extends AbstractDAO<User>{

    private static final String STATEMENT = "UPDATE public.\"Users\" SET emailConfirmed = true WHERE emailConfirmed = false AND emailHash = ?";

    private final User user;


    public UserEmailConfirmationDAO(Connection con, final User user) {
        super(con);
        this.user = user;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(STATEMENT);
            stmt.setBoolean(1, user.getEmailConfirmed());

            stmt.executeUpdate();   // the update

            LOGGER.info("%s's User Email Verified.", user.getEmail());

        } finally {
            if (stmt != null)
                stmt.close();
        }

        con.close();
    }
}
