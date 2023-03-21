package com.jaehong.data.util

import com.jaehong.domain.local.model.result.DbResult
import java.sql.SQLException

fun <T : Any?> safeDataBaseCall(call: T?): DbResult<T> {

    try {
        when (call) {
            is Boolean -> {
                return if (call) DbResult.Success(call)
                else DbResult.Error(SQLException("sql error runs"))
            }
            is List<*>? -> {
                return if(call != null) DbResult.Success(call)
                else DbResult.Error(SQLException("sql error runs"))
            }
        }
    } catch (e: SQLException) {
        return DbResult.Error(SQLException(e.message ?: "sql error runs"))
    }

    return DbResult.Error(SQLException("sql error runs"))
}