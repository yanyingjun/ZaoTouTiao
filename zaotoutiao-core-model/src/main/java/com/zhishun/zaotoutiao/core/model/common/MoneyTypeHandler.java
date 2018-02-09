package com.zhishun.zaotoutiao.core.model.common;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Money类的mybatis自定义TypeHandler，
 * 用于mybatis中javaType和jdbcType类型之间的互相转换。
 * 这里要注意的是javaType为Money类型的，jdbcType必须为
 * DECIMAL或NUMERIC类型，否则该TypeHandler无法处理。
 *
 * @author 曹柏青
 * @version $Id: MoneyTypeHandler, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年12月03日 20:09 曹柏青 Exp $d
 */
public class MoneyTypeHandler extends BaseTypeHandler<Money> {

    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getMoney(rs.getBigDecimal(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getMoney(rs.getBigDecimal(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getMoney(cs.getBigDecimal(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {

        //由于BaseTypeHandler中已经把parameter为null的情况做了处理，
        //所以这里我们就不用再判断parameter是否为空了，直接用就可以了
        ps.setBigDecimal(i, parameter.getAmount());
    }

    /**
     * 将数据库中的字段值转换为Money对象
     *
     * @param columnValue
     * @return
     */
    private Money getMoney(BigDecimal columnValue) {

        if (columnValue == null) {

            return null;
        }

        return new Money(columnValue);

    }
}
