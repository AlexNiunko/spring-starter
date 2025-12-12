package by.epam.repository;

import by.epam.bpp.InjectBean;
import by.epam.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;

}
