package gr.uoi.cse.countrygraph.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;

@FunctionalInterface
public interface PreparedStatementProcessorStrategy
{
	void processPreparedStatement(PreparedStatement preparedStatement, MeasureRequest measureRequest) throws SQLException ;
}