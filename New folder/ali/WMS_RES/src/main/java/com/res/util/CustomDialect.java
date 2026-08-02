package com.res.util;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class CustomDialect extends MySQL5Dialect{
	
	public CustomDialect(){
	
	 registerFunction("GROUP_CONCAT", new StandardSQLFunction("group_concat", StringType.INSTANCE)); 
     //registerFunction("concat_ws", new StandardSQLFunction("concat_ws", StringType.INSTANCE));
	}

}
