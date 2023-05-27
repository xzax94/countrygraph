package gr.uoi.cse.countrygraph.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.statistic.Statistic;

@FunctionalInterface
public interface ResultSetMapper
{
	Statistic mapResultSetToStatistic(ResultSet resultSet) throws SQLException;
}