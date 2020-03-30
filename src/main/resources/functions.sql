DROP function getColumns;

CREATE FUNCTION getColumns(text) RETURNS SETOF information_schema.sql_identifier AS $$
    SELECT column_name
	FROM INFORMATION_SCHEMA.COLUMNS
	WHERE TABLE_NAME = $1;
$$ LANGUAGE SQL;