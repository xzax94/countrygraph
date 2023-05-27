package gr.uoi.cse.countrygraph.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import gr.uoi.cse.countrygraph.measure.MeasureRequest;

public final class ProcessYearSexStrategy implements PreparedStatementProcessorStrategy
{
	@Override
	public void processPreparedStatement(PreparedStatement preparedStatement, MeasureRequest measureRequest) throws SQLException
	{
		preparedStatement.setInt(1, measureRequest.getCountryId());
		preparedStatement.setInt(2, measureRequest.getMinYear());
		preparedStatement.setInt(3, measureRequest.getMaxYear());
		preparedStatement.setString(4, measureRequest.getSex().toString());
	}
}