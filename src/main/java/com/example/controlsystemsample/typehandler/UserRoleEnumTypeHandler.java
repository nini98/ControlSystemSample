package com.example.controlsystemsample.typehandler;

import com.example.controlsystemsample.enums.UserRole;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleEnumTypeHandler extends BaseTypeHandler<UserRole> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserRole parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public UserRole getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return UserRole.fromCode(code);
    }

    @Override
    public UserRole getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        return UserRole.fromCode(code);
    }

    @Override
    public UserRole getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        return UserRole.fromCode(code);
    }
}
