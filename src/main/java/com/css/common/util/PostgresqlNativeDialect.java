package com.css.common.util;

import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.dialect.PostgreSQLDialect;

public class PostgresqlNativeDialect extends PostgreSQLDialect {
	public PostgresqlNativeDialect() {
        super();
        //registerColumnType(Types.VARCHAR, "varchar($l)");
        registerColumnType(Types.CLOB, "varchar(max)");
        
        
        registerColumnType(Types.INTEGER, "int2");
        registerColumnType(Types.INTEGER, "int4");
        registerColumnType(Types.BIGINT, "int8");
        registerColumnType(Types.INTEGER,"bool");
    }

   public String getTypeName(int code, int length, int precision, int scale) throws HibernateException {
       if(code != 2005) {
           return super.getTypeName(code, length, precision, scale);
       } else {
           return "text";
       }
   }
}
